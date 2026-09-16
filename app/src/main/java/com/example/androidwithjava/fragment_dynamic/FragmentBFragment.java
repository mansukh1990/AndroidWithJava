package com.example.androidwithjava.fragment_dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidwithjava.databinding.FragmentBBinding;

public class FragmentBFragment extends Fragment {

    private FragmentBBinding binding;

    public FragmentBFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentBBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}