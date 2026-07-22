package com.codewithme.southcampus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "settings";
    private static final String THEME_PREF_KEY = "theme_pref";
    private static final long RECREATE_DELAY_MS = 300;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySavedTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupToolbar();
        setupThemeToggle();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Settings");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupThemeToggle() {
        MaterialButtonToggleGroup themeToggle = findViewById(R.id.theme_toggle_group);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String currentTheme = prefs.getString(THEME_PREF_KEY, "system");

        themeToggle.check(getButtonIdForTheme(currentTheme));

        themeToggle.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                String newTheme = getThemeFromButtonId(checkedId);
                if (!newTheme.equals(currentTheme)) {
                    handleThemeChange(newTheme, prefs);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void handleThemeChange(String newTheme, SharedPreferences prefs) {
        prefs.edit().putString(THEME_PREF_KEY, newTheme).apply();
        applyTheme(newTheme);
        getDelegate().applyDayNight();
        updateSystemBars();

        new Handler().postDelayed(() -> {
            if (!isFinishing()) {
                recreateWithTransition();
            }
        }, RECREATE_DELAY_MS);
    }

    private void recreateWithTransition() {
        recreate();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateSystemBars() {
        Window window = getWindow();
        if (window != null) {
            window.setStatusBarColor(resolveStatusBarColor());
            window.setNavigationBarColor(resolveNavBarColor());
            updateSystemUiAppearance(window);
        }
    }

    private int resolveStatusBarColor() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.statusBarColor, typedValue, true);
        return typedValue.data;
    }

    private int resolveNavBarColor() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.navigationBarColor, typedValue, true);
        return typedValue.data;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateSystemUiAppearance(@NonNull Window window) {
        View decorView = window.getDecorView();
        int flags = decorView.getSystemUiVisibility();

        boolean lightBars = !isDarkTheme();
        flags = lightBars
                ? flags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                : flags & ~(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        decorView.setSystemUiVisibility(flags);
    }

    private boolean isDarkTheme() {
        int nightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        return nightMode == Configuration.UI_MODE_NIGHT_YES;
    }

    private void applySavedTheme() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        applyTheme(prefs.getString(THEME_PREF_KEY, "system"));
    }

    private void applyTheme(String themeValue) {
        switch (themeValue) {
            case "light":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case "dark":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }

    private int getButtonIdForTheme(String themeValue) {
        if ("light".equals(themeValue)) return R.id.btn_theme_light;
        if ("dark".equals(themeValue)) return R.id.btn_theme_dark;
        return R.id.btn_theme_system;
    }

    private String getThemeFromButtonId(int buttonId) {
        if (buttonId == R.id.btn_theme_light) return "light";
        if (buttonId == R.id.btn_theme_dark) return "dark";
        return "system";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}