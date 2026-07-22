package com.codewithme.southcampus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.content.SharedPreferences;
import androidx.appcompat.widget.Toolbar;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btnLogout = findViewById(R.id.btn_logout);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Check login status
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
        String email = prefs.getString("userEmail", "guest");
        if (!isLoggedIn) {
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
            return;
        }





//        Button loginBtn = findViewById(R.id.btn\_login);
//        loginBtn.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setTitle("Profile");
//        }

        // Logout action
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.putString("userEmail", "guest");
            editor.apply();

            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        });
    }
}
