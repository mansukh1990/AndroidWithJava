package com.example.androidwithjava.tablayoutwithviewpagertwo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.androidwithjava.tablayoutwithviewpager.CallsFragment;
import com.example.androidwithjava.tablayoutwithviewpager.ChatFragment;
import com.example.androidwithjava.tablayoutwithviewpager.StatusFragment;

public class ViewPagerTwoAdapter extends FragmentStateAdapter {
    public ViewPagerTwoAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ChatFragment();
        } else if (position == 1) {
            return new StatusFragment();

        } else {
            return new CallsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
