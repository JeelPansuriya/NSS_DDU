package com.example.nss_ddu.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nss_ddu.R;
import com.example.nss_ddu.databinding.ActivityMainBinding;


public class eventFragment extends Fragment {

    ActivityMainBinding Binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Binding = ActivityMainBinding.inflate(inflater,container,false);
        View view = Binding.getRoot();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Binding = null;
    }
}