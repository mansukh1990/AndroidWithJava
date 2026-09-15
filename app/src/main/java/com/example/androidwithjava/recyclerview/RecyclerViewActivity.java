package com.example.androidwithjava.recyclerview;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidwithjava.R;
import com.example.androidwithjava.databinding.ActivityRecyclerViewBinding;
import com.example.androidwithjava.databinding.DialogAddUpdateContactBinding;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private ActivityRecyclerViewBinding binding;
    RecyclerViewContactAdapter recyclerViewContactAdapter;
    ArrayList<ContactModel> arrayContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        binding.recyclerView.setAdapter(recyclerViewContactAdapter);

        binding.fbAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(RecyclerViewActivity.this);
                DialogAddUpdateContactBinding contactBinding = DialogAddUpdateContactBinding.inflate(LayoutInflater.from(RecyclerViewActivity.this));
                dialog.setContentView(contactBinding.getRoot());

                Window window = dialog.getWindow();

                if (window != null) {
                    window.setLayout(
                            WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT
                    );
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }


                contactBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name = contactBinding.edtName.getText().toString().trim(), number = contactBinding.edtNumber.getText().toString().trim();

                        if (name.isEmpty()) {
                            Toast.makeText(RecyclerViewActivity.this, "Please Enter Contact Name!", Toast.LENGTH_SHORT).show();
                            return;

                        }
                        if (number.isEmpty()) {
                            Toast.makeText(RecyclerViewActivity.this, "Please Enter Mobile Numer!", Toast.LENGTH_SHORT).show();

                        }

                        arrayContact.add(new ContactModel(R.drawable.ic_contact_profile, name, number));
                        recyclerViewContactAdapter.notifyItemInserted(arrayContact.size() - 1);
                        binding.recyclerView.scrollToPosition(arrayContact.size() - 1);

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