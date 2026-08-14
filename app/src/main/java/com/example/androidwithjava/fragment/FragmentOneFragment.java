package com.example.androidwithjava.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.androidwithjava.R;

public class FragmentOneFragment extends Fragment {


    public FragmentOneFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        TextView txtFragOne = view.findViewById(R.id.txtFragOne);
        return view;
    }
}