package com.example.nss_ddu.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.nss_ddu.databinding.FragmentHelpBinding;

public class helpFragment extends Fragment {

    private FragmentHelpBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout using view binding
        binding = FragmentHelpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = binding.toolbar;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setTitle("Help");

        // Set faculty details in the TextViews
        binding.faculty1.setText("Jeel Pansuriya");
        binding.email1.setText("faculty1@gmail.com");
        binding.contact1.setText("+91 1234567890");
        binding.address1.setText("IT lab 1");

        binding.faculty2.setText("Sayam Parejiya");
        binding.email2.setText("faculty2@gmail.com");
        binding.contact2.setText("+91 1234567890");
        binding.address2.setText("IT lab 1");

        // Set click listeners for email and contact
        binding.email1.setOnClickListener(v -> sendEmail("faculty1@gmail.com"));
        binding.contact1.setOnClickListener(v -> callPhoneNumber("+911234567890"));

        binding.email2.setOnClickListener(v -> sendEmail("faculty2@gmail.com"));
        binding.contact2.setOnClickListener(v -> callPhoneNumber("+911234567890"));
    }

    private void sendEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + email));
        if (emailIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(emailIntent);
        }else {
            Toast.makeText(getContext(), "No email app installed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void callPhoneNumber(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
