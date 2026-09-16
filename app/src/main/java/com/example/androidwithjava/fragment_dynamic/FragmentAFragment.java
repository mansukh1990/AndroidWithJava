package com.example.androidwithjava.fragment_dynamic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.androidwithjava.databinding.FragmentABinding;

public class FragmentAFragment extends Fragment {
    private FragmentABinding binding;

    private static final String ARG1 = "argument1";
    private static final String ARG2 = "argument2";

    public FragmentAFragment() {

    }

    public static FragmentAFragment getInstance(String value1, int value2) {
        FragmentAFragment fragmentAFragment = new FragmentAFragment();

        Bundle bundle = new Bundle();

        bundle.putString(ARG1, value1);
        bundle.putInt(ARG2, value2);

        fragmentAFragment.setArguments(bundle);

        return fragmentAFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentABinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            String name = getArguments().getString(ARG1);
            int rollNo = getArguments().getInt(ARG2);

            Log.d("Value From Activity", "Name is : " + name);
            Log.d("Value From Activity", "RollNo is : " + rollNo);

            ((FragmentDynamicActivity) requireActivity()).CallFromFragment();
        }
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        binding = null;
    }
}