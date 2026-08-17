package com.example.androidwithjava.fragment_dynamic;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidwithjava.R;

public class FragmentDynamicActivity extends AppCompatActivity {

    Button btnFragA, btnFragB, btnFragC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fragment_dynamic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnFragA = findViewById(R.id.btnFragA);
        btnFragB = findViewById(R.id.btnFragB);
        btnFragC = findViewById(R.id.btnFragC);

        loadFragment(new FragmentAFragment(), 0);

        btnFragA.setOnClickListener(view -> {
            loadFragment(new FragmentAFragment(), 1);
        });

        btnFragB.setOnClickListener(view -> {
            loadFragment(new FragmentBFragment(), 1);
        });

        btnFragC.setOnClickListener(view -> {
            loadFragment(new FragmentCFragment(), 1);

        });
    }

    public void loadFragment(Fragment fragment, int flag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (flag == 0)
            fragmentTransaction.add(R.id.container, fragment);
        else
            fragmentTransaction.replace(R.id.container, fragment);

        fragmentTransaction.commit();
    }
}