package com.example.androidwithjava.sharepreference;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidwithjava.R;

public class SharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_preference);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPrefManager prefManager = new SharedPrefManager(SharedPreferenceActivity.this);
                boolean isLogin = prefManager.isLoggedIn();

                Intent intent;

                if (isLogin) {
                    intent = new Intent(SharedPreferenceActivity.this, HomeActivity.class);
                } else {
                    intent = new Intent(SharedPreferenceActivity.this, LoginActivity.class);
                }
                startActivity(intent);

            }
        }, 4000);
    }
}