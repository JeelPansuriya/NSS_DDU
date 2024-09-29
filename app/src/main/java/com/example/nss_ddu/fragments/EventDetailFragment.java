package com.example.nss_ddu.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nss_ddu.databinding.FragmentEventDetailBinding;

public class EventDetailFragment extends Fragment {

    private FragmentEventDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEventDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Get event details from arguments
        Bundle args = getArguments();
        if (args != null) {
            binding.titleTextView.setText(args.getString("eventTitle"));
            binding.dateTimeTextView.setText(args.getString("eventDate") + ", " + args.getString("eventTime"));
            binding.venueTextView.setText(args.getString("eventVenue"));
            binding.descriptionTextView.setText(args.getString("eventDescription"));

            String registrationLink = args.getString("registrationLink");
            binding.registerButton.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(registrationLink));
                startActivity(browserIntent);
            });
        }

        return view;
    }
}
