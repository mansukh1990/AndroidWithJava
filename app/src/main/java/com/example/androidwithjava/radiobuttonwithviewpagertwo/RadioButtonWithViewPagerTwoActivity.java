package com.example.androidwithjava.radiobuttonwithviewpagertwo;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidwithjava.R;
import com.example.androidwithjava.tablayoutwithviewpagertwo.ViewPagerTwoAdapter;

public class RadioButtonWithViewPagerTwoActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    RadioGroup radioGroup;
    RadioButton radioButtonChat;
    RadioButton radioButtonStatus;
    RadioButton radioButtonCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_radio_button_with_view_pager_two);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager = findViewById(R.id.viewPagerRadio);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonChat = findViewById(R.id.radioChat);
        radioButtonStatus = findViewById(R.id.radioStatus);
        radioButtonCalls = findViewById(R.id.radioCall);

        ViewPagerTwoAdapter viewPagerTwoAdapter = new ViewPagerTwoAdapter(this);
        viewPager.setAdapter(viewPagerTwoAdapter);

        viewPager.setUserInputEnabled(false);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radioChat) {
                    viewPager.setCurrentItem(0);
                } else if (checkedId == R.id.radioStatus) {
                    viewPager.setCurrentItem(1);

                } else {
                    viewPager.setCurrentItem(2);
                }
            }
        });


    }
}