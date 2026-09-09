package com.example.androidwithjava.tablayoutwithviewpagertwo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidwithjava.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabLayoutWithViewpagerTwoActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tab_layout_with_viewpager_two);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPagerTwo);

        ViewPagerTwoAdapter viewPagerTwoAdapter = new ViewPagerTwoAdapter(this);
        viewPager2.setAdapter(viewPagerTwoAdapter);

        viewPager2.setUserInputEnabled(true);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Chat");
                    } else if (position == 1) {
                        tab.setText("Status");

                    } else {
                        tab.setText("Calls");
                    }
                }).attach();

    }
}