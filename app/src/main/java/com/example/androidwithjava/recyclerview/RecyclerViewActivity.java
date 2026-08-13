package com.example.androidwithjava.recyclerview;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidwithjava.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    RecyclerViewContactAdapter recyclerViewContactAdapter;
    ArrayList<ContactModel> arrayContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.fbAddContact);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));
        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "98004654564"));

        recyclerViewContactAdapter = new RecyclerViewContactAdapter(this, arrayContact);
        recyclerView.setAdapter(recyclerViewContactAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(RecyclerViewActivity.this);
                dialog.setContentView(R.layout.dialog_add_update_contact);

                Window window = dialog.getWindow();

                if (window != null) {
                    window.setLayout(
                            WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT
                    );
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                EditText editTextName = dialog.findViewById(R.id.edtName);
                EditText editTextNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAdd = dialog.findViewById(R.id.btnAdd);
                TextView textViewContact = dialog.findViewById(R.id.tvAddContact);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name = editTextName.getText().toString().trim(), number = editTextNumber.getText().toString().trim();

                        if (name.isEmpty()) {
                            Toast.makeText(RecyclerViewActivity.this, "Please Enter Contact Name!", Toast.LENGTH_SHORT).show();
                            return;

                        }
                        if (number.isEmpty()) {
                            Toast.makeText(RecyclerViewActivity.this, "Please Enter Mobile Numer!", Toast.LENGTH_SHORT).show();

                        }

                        arrayContact.add(new ContactModel(R.drawable.ic_launcher_foreground, name, number));
                        recyclerViewContactAdapter.notifyItemInserted(arrayContact.size() - 1);
                        recyclerView.scrollToPosition(arrayContact.size() - 1);

                        Toast.makeText(RecyclerViewActivity.this, "Add Contact Successfully!", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();

            }
        });
    }
}