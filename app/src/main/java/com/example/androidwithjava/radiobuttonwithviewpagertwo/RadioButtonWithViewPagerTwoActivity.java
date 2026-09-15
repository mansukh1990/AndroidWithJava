package com.example.androidwithjava.radiobuttonwithviewpagertwo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidwithjava.R;
import com.example.androidwithjava.databinding.ActivityRadioButtonWithViewPagerTwoBinding;
import com.example.androidwithjava.tablayoutwithviewpagertwo.ViewPagerTwoAdapter;

public class RadioButtonWithViewPagerTwoActivity extends AppCompatActivity {

    private ActivityRadioButtonWithViewPagerTwoBinding binding;
    private boolean isRadiobutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityRadioButtonWithViewPagerTwoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ViewPagerTwoAdapter viewPagerTwoAdapter = new ViewPagerTwoAdapter(this);
        binding.viewPagerRadio.setAdapter(viewPagerTwoAdapter);

        binding.viewPagerRadio.setUserInputEnabled(true);

        binding.radioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            if (isRadiobutton) return;
            if (checkedId == binding.radioCall.getId()) {
                binding.viewPagerRadio.setCurrentItem(0, true);
            } else if (checkedId == binding.radioStatus.getId()) {
                binding.viewPagerRadio.setCurrentItem(1, true);

            } else if (checkedId == binding.radioCall.getId()) {
                binding.viewPagerRadio.setCurrentItem(2, true);
            }
        });

        binding.viewPagerRadio.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                isRadiobutton = true;

                switch (position) {
                    case 0:
                        binding.radioChat.setChecked(true);
                        break;
                    case 1:
                        binding.radioStatus.setChecked(true);
                        break;
                    case 2:
                        binding.radioCall.setChecked(true);
                        break;
                }
                isRadiobutton = false;
            }
        });


    }
}