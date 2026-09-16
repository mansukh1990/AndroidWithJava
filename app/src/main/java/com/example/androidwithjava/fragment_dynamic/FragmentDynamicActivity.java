package com.example.androidwithjava.fragment_dynamic;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidwithjava.R;
import com.example.androidwithjava.databinding.ActivityFragmentDynamicBinding;

public class FragmentDynamicActivity extends AppCompatActivity {

    private static final String ROOT_FRAGMENT_TAG = "root_fragment";
    private ActivityFragmentDynamicBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityFragmentDynamicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        loadFragment(FragmentAFragment.getInstance("Mansukh", 30), 0);

        binding.btnFragA.setOnClickListener(view -> {
            loadFragment(FragmentAFragment.getInstance("Mansukh", 30), 0);
        });

        binding.btnFragB.setOnClickListener(view -> {
            loadFragment(new FragmentBFragment(), 1);
        });

        binding.btnFragC.setOnClickListener(view -> {
            loadFragment(new FragmentCFragment(), 1);

        });
    }

    public void loadFragment(Fragment fragment, int flag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        Bundle bundle = new Bundle();
//
//        bundle.putString("Arg1", "Raman");
//        bundle.putInt("Arg2", 7);

        //   fragment.setArguments(bundle);


        if (flag == 0) {
            fragmentTransaction.add(R.id.container, fragment);
            fragmentManager.popBackStack(ROOT_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.addToBackStack(ROOT_FRAGMENT_TAG);
        } else {
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    public void CallFromFragment() {
        Log.d("inActivity", "CallFromFragment: ");
    }
}