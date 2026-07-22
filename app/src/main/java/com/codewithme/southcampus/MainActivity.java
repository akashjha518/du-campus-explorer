package com.codewithme.southcampus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTheme(R.style.Theme_SouthCampus);
        setContentView(R.layout.activity_main);

        Button academicBtn = findViewById(R.id.btn_academic);
        Button navBtn = findViewById(R.id.btn_navigation);
        Button transportBtn = findViewById(R.id.btn_transport);
        Button hostelBtn = findViewById(R.id.btn_hostel);
        Button cafeteriaBtn = findViewById(R.id.btn_cafeteria);
        Button eventsBtn = findViewById(R.id.btn_events);
        Button facilityBtn = findViewById(R.id.btn_facility);

        academicBtn.setOnClickListener(v -> startActivity(new Intent(this, AcademicActivity.class)));
        navBtn.setOnClickListener(v -> startActivity(new Intent(this, NavigationActivity.class)));
        transportBtn.setOnClickListener(v -> startActivity(new Intent(this, TransportActivity.class)));
        hostelBtn.setOnClickListener(v -> startActivity(new Intent(this, HostelActivity.class)));
        cafeteriaBtn.setOnClickListener(v -> startActivity(new Intent(this, CafeteriaActivity.class)));
        eventsBtn.setOnClickListener(v -> startActivity(new Intent(this, EventsActivity.class)));
        facilityBtn.setOnClickListener(v -> startActivity(new Intent(this, FacilityActivity.class)));

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                // Stay on current activity
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            } else if (itemId == R.id.nav_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            }
            return false;
        });
    }
}
