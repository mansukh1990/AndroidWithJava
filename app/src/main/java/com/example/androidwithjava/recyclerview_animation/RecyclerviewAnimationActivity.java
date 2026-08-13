package com.example.androidwithjava.recyclerview_animation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidwithjava.R;
import com.example.androidwithjava.recyclerview.ContactModel;

import java.util.ArrayList;

public class RecyclerviewAnimationActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerViewContactAnimationAdapter recyclerViewContactAnimationAdapter;
    ArrayList<ContactModel> contactModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recyclerview_animation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Title");
        }
        //toolbar.setTitle("Title");
        toolbar.setSubtitle("Sub Title");

        recyclerView = findViewById(R.id.recyclerViewAnimation);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));
        contactModelArrayList.add(new ContactModel(R.drawable.ic_contact_profile, "Steven Smith", "9806000000"));

        recyclerViewContactAnimationAdapter = new RecyclerViewContactAnimationAdapter(RecyclerviewAnimationActivity.this, contactModelArrayList);

        recyclerView.setAdapter(recyclerViewContactAnimationAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.pop_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.option_new) {
            Toast.makeText(this, "Create New File", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.option_open) {
            Toast.makeText(this, "Open File", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.option_save) {
            Toast.makeText(this, "Save File", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.option_search) {
            Toast.makeText(this, "Clicked Search", Toast.LENGTH_SHORT).show();
        } else if (itemId == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}