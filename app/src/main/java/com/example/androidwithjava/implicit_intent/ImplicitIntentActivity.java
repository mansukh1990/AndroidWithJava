package com.example.androidwithjava.implicit_intent;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidwithjava.R;
import com.example.androidwithjava.utils.IntentUtils;

public class ImplicitIntentActivity extends AppCompatActivity {

    Button btnDial, btnMessage, btnEmail, btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_implicit_intent);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnDial = findViewById(R.id.btnDial);
        btnMessage = findViewById(R.id.btnMsg);
        btnEmail = findViewById(R.id.btnEMail);
        btnShare = findViewById(R.id.btnShare);

        String phoneNumber = "+919737582727";

        btnDial.setOnClickListener(view -> {
            IntentUtils.dialNumber(this, phoneNumber);

        });
        btnMessage.setOnClickListener(view -> {
            IntentUtils.sendMessage(this, phoneNumber, "Hello from AndroidWithJava!");
        });
        btnEmail.setOnClickListener(view -> {
            IntentUtils.sendEmail(
                    this,
                    new String[]{"mrm.ec08osec@gmail.com","mansukhmakwana030@gmail.com"},
                    "Subject line",
                    "Email body text"
            );
        });

        btnShare.setOnClickListener(v -> IntentUtils.shareText(this, "Check out this app!"));
    }
}