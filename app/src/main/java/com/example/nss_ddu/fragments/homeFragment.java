package com.example.nss_ddu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.nss_ddu.adapters.EventAdapter;
import com.example.nss_ddu.databinding.FragmentHomeBinding;
import com.example.nss_ddu.models.Event;
import com.example.nss_ddu.network.DynamoDBHelper;

import java.util.List;

public class homeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private EventAdapter eventAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize view binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up RecyclerView using view binding
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventAdapter = new EventAdapter();
        binding.recyclerView.setAdapter(eventAdapter);

        Toast.makeText(getContext(), "Fetching events...", Toast.LENGTH_SHORT).show();

        // Fetch events asynchronously
        DynamoDBHelper dynamoDBHelper = new DynamoDBHelper(getContext());
        dynamoDBHelper.getEvents(new DynamoDBHelper.Callback() {
            @Override
            public void onSuccess(List<Event> events) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        eventAdapter.setEvents(events);
                        Toast.makeText(getContext(), "Events fetched successfully!", Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onFailure(Exception exception) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Failed to fetch events: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Release view binding reference
        binding = null;
    }
}
