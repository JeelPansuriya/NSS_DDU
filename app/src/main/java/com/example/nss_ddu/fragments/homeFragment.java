package com.example.nss_ddu.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.nss_ddu.R;
import com.example.nss_ddu.adapters.EventAdapter;
import com.example.nss_ddu.databinding.FragmentHomeBinding;
import com.example.nss_ddu.models.Event;
import com.example.nss_ddu.network.DynamoDBHelper;

import java.util.List;

public class homeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private EventAdapter eventAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);  // Enable options menu in the fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });

        // Setup the toolbar
        Toolbar toolbar = binding.toolbar;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        // Set Title
        toolbar.setTitle("Events");

        // Set RecyclerView layout manager and adapter
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize event adapter
        eventAdapter = new EventAdapter(event -> {
            // Handle event card click and navigate to EventDetailFragment
            Bundle bundle = new Bundle();
            bundle.putString("eventTitle", event.getTitle());
            bundle.putString("eventDate", event.getDate());
            bundle.putString("eventVenue", event.getVenue());
            bundle.putString("eventTime", event.getTime());
            bundle.putString("eventDescription", event.getDescription());
            bundle.putString("registrationLink", event.getLink());

            // Navigate to the event detail fragment
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_eventDetailFragment, bundle);
        });

        binding.recyclerView.setAdapter(eventAdapter);

        // Fetch events from DynamoDB
        DynamoDBHelper dynamoDBHelper = new DynamoDBHelper(getContext());
        dynamoDBHelper.getEvents(new DynamoDBHelper.Callback() {
            @Override
            public void onSuccess(List<Event> events) {
                // Update the RecyclerView on the main thread
                getActivity().runOnUiThread(() -> eventAdapter.setEvents(events));
            }

            @Override
            public void onFailure(Exception exception) {
                // Show Toast message on the main thread
                new Handler(Looper.getMainLooper()).post(() ->
                        Toast.makeText(getContext(), "Failed to fetch events", Toast.LENGTH_SHORT).show()
                );
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home_fragment, menu);  // Inflate your menu resource
        super.onCreateOptionsMenu(menu, inflater);
    }

    // Handle menu item clicks
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_help) {
            Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_helpFragment);
            return true;
        } else if (itemId == R.id.action_logout) {
            AWSMobileClient.getInstance().initialize(requireContext(), new com.amazonaws.mobile.client.Callback<com.amazonaws.mobile.client.UserStateDetails>() {
                @Override
                public void onResult(com.amazonaws.mobile.client.UserStateDetails userStateDetails) {
                    AWSMobileClient.getInstance().signOut();
                    Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_loginFragment);
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
