package com.example.androidwithjava.listview_spinner_autocomplete_textview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidwithjava.R;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    ListView listView;
    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;
    //array
    int[] arrNo = new int[]{1, 2, 3, 4};
    //arrayList
    ArrayList<String> arrNames = new ArrayList<>();
    ArrayList<String> arrayIds = new ArrayList<>();
    ArrayList<String> arrayLanguages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextview);

        arrNames.add("Ram");
        arrNames.add("Bam");
        arrNames.add("Cam");
        arrNames.add("Dam");
        arrNames.add("Eam");
        arrNames.add("Fam");
        arrNames.add("Gam");
        arrNames.add("Ham");
        arrNames.add("Iam");
        arrNames.add("Jam");
        arrNames.add("Kam");
        arrNames.add("Lam");
        arrNames.add("Mam");
        arrNames.add("Nam");
        arrNames.add("Oam");
        arrNames.add("Pam");
        arrNames.add("Qam");
        arrNames.add("Ram");
        arrNames.add("Sam");
        arrNames.add("Tam");
        arrNames.add("Uam");
        arrNames.add("Vam");
        arrNames.add("Wam");
        arrNames.add("Xam");
        arrNames.add("Yam");
        arrNames.add("Zam");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, arrNames);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Toast.makeText(ListViewActivity.this, "First Item Clicked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListViewActivity.this, "Other Item Clicked", Toast.LENGTH_SHORT).show();

                }
            }
        });

        //Spinner

        arrayIds.add("Aadhaar Card");
        arrayIds.add("PAN Card");
        arrayIds.add("Voter Card");
        arrayIds.add("Driving Licence Card");
        arrayIds.add("Ration Card");
        arrayIds.add("10th Certificate");
        arrayIds.add("12th Certificate");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(ListViewActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayIds);
        spinner.setAdapter(spinnerAdapter);

        //AutoCompleteTextview

        arrayLanguages.add("C");
        arrayLanguages.add("C++");
        arrayLanguages.add("Java");
        arrayLanguages.add("Kotlin");
        arrayLanguages.add("PHP");
        arrayLanguages.add("Laravel");
        arrayLanguages.add("React");
        arrayLanguages.add(".Net");
        arrayLanguages.add("Dart");

        ArrayAdapter<String> autoCompleteTextviewAdapter = new ArrayAdapter<>(ListViewActivity.this, android.R.layout.simple_list_item_1, arrayLanguages);
        autoCompleteTextView.setAdapter(autoCompleteTextviewAdapter);
        autoCompleteTextView.setThreshold(0);
    }
}