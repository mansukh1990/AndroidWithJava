package com.example.androidwithjava.alertdialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidwithjava.R;
import com.example.androidwithjava.utils.AlertDialogUtils;

public class AlertDialogActivity extends AppCompatActivity {

    Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alert_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button1 = findViewById(R.id.btnOneAlert);
        button2 = findViewById(R.id.btnTwoAlert);
        button3 = findViewById(R.id.btnThreeAlert);
        button4 = findViewById(R.id.btnFourAlert);

        button1.setOnClickListener(view ->
                AlertDialogUtils.showAlertDialogOneButton(
                        AlertDialogActivity.this,
                        "Terms & Condition",
                        R.drawable.ic_baseline_info_24,
                        "Have You Read All Terms & Conditions",
                        "YES, Proceed And Continue",
                        (dialogInterface, i) -> {
                            Toast.makeText(AlertDialogActivity.this, "Yes, you can proceed to next", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }
                ));

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialogUtils.showAlertDialogTwoButton(
                        AlertDialogActivity.this,
                        "Terms & Condition",
                        R.drawable.ic_baseline_info_24,
                        "Have You Read All Terms & Conditions?",
                        "YES, Proceed And Continue",
                        "NO",
                        (dialogInterface, i) -> {
                            Toast.makeText(AlertDialogActivity.this, "Yes, you can proceed to next", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        },
                        (dialogInterface, i) -> {
                            Toast.makeText(AlertDialogActivity.this, "No", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }
                );
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialogUtils.showAlertDialogThreeButton(
                        AlertDialogActivity.this,
                        "Terms & Condition",
                        R.drawable.ic_baseline_info_24,
                        "Have You Read All Terms & Conditions?",
                        "YES, Proceed And Continue",
                        "NO",
                        "Cancel",
                        (dialogInterface, i) -> {
                            Toast.makeText(AlertDialogActivity.this, "Yes, you can proceed to next", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();

                        },
                        (dialogInterface, i) -> {
                            Toast.makeText(AlertDialogActivity.this, "No", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();

                        },
                        (dialogInterface, i) -> {
                            Toast.makeText(AlertDialogActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();

                        }


                );
            }
        });

        button4.setOnClickListener(view -> AlertDialogUtils.showCustomDialog(AlertDialogActivity.this,
                R.drawable.ic_baseline_delete_forever_24,
                "Failed",
                "Deleted successfully "));

        getOnBackPressedDispatcher().addCallback(
                this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        AlertDialogUtils.showAlertDialogTwoButton(
                                AlertDialogActivity.this,
                                "Exit",
                                R.drawable.ic_baseline_info_24,
                                "Are you sure you want to exit?",
                                "YES",
                                "NO",
                                (dialogInterface, i) -> finish(),
                                (dialogInterface, i) -> dialogInterface.dismiss()
                        );
                    }
                }
        );

    }
}