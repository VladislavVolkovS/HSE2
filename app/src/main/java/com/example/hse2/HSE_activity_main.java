package com.example.hse2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hse2.databinding.ActivityHseMainBinding;
import com.example.hse2.databinding.ActivityMainBinding;

public class HSE_activity_main extends AppCompatActivity {

    ActivityHseMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHseMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new ExpensesFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.wastes){
                replaceFragment(new ExpensesFragment());
            }

            if (item.getItemId() == R.id.settings){
                replaceFragment(new SettingsFragment());
            }

            if (item.getItemId() == R.id.statistic){
                replaceFragment(new StatisticFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}