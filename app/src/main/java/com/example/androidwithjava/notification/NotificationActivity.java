package com.example.androidwithjava.notification;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidwithjava.MainActivity;
import com.example.androidwithjava.R;
import com.example.androidwithjava.utils.NotificationHelper;

import java.util.Arrays;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    Button buttonBigText, buttonBigPicture, btnInbox;
    List<String> lines = Arrays.asList(
            "Steven: Are you free today?",
            "Anna: Meeting moved to 3 PM",
            "Team: Don't forget standup",
            "Team: Don't forget standup",
            "Team: Don't forget standup",
            "Team: Don't forget standup",
            "Team: Don't forget standup",
            "Team: Don't forget standup",
            "Team: Don't forget standup",
            "Team: Don't forget standup",
            "Team: Don't forget standup",
            "Team: Don't forget standup",
            "Team: Don't forget standup",
            "Team: Don't forget standup"
    );
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    showNotification();
                } else {
                    Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonBigText = findViewById(R.id.showNotificationBigText);
        buttonBigPicture = findViewById(R.id.showNotificationBigPicture);
        btnInbox = findViewById(R.id.showInbox);

        Intent destination = new Intent(this, MainActivity.class);
        destination.putExtra("navigate_to", "main_activity");
        destination.putExtra("sender", "Steven Smith");


        buttonBigText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                NotificationHelper.showNotificationBigText(
                        NotificationActivity.this,
                        "New Message",
                        "Steven: Hey, are you free today?",
                        "Steven: Hey, are you free today? I wanted to discuss the project timeline " +
                                "and see if we can meet sometime this week to go over the details.",
                        R.drawable.ic_notification,
                        destination,
                        true
                );
            }
        });

        buttonBigPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationHelper.showNotificationBigPicture(
                        NotificationActivity.this,
                        "New Photo",
                        "Steven sent a photo",
                        R.drawable.ic_light,
                        R.drawable.ic_notification, destination,
                        true);

            }
        });

        btnInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationHelper.showInbox(NotificationActivity.this,
                        "3 new messages",
                        "You have 3 unread messages",
                        lines,
                        R.drawable.ic_notification,
                        destination,
                        true);
            }
        });

        NotificationHelper.createChannel(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                showNotification();
            }
        } else {
            showNotification();
        }


    }

    public void showNotification() {
        Intent destination = new Intent(this, MainActivity.class);
        destination.putExtra("navigate_to", "main_activity");
        destination.putExtra("sender", "Steven Smith");

        NotificationHelper.showNotificationSimple(
                this,
                "New Message",
                "Hi there!",
                R.drawable.ic_notification,
                destination,
                true
        );
    }

}
