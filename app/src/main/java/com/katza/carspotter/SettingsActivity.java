package com.katza.carspotter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchNotifications;
    private Button resetButton;
    private Button logoutButton;
    private ImageButton backButton;

    // SharedPreferences to save user settings
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CarAppPrefs";
    private static final String KEY_NOTIFICATIONS = "notifications_enabled";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //init
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        backButton = findViewById(R.id.backButton);
        switchNotifications = findViewById(R.id.switchNotifications);
        resetButton = findViewById(R.id.resetButton);
        logoutButton = findViewById(R.id.logoutButton);

        // Load saved preference for the switch
        boolean isNotificationsEnabled = sharedPreferences.getBoolean(KEY_NOTIFICATIONS, true);
        switchNotifications.setChecked(isNotificationsEnabled);

        //Switch toggle
        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_NOTIFICATIONS, isChecked);
            editor.apply();

            String status = isChecked ? "enabled" : "disabled";
            Toast.makeText(SettingsActivity.this, "Notifications " + status, Toast.LENGTH_SHORT).show();
        });

        //Back button
        backButton.setOnClickListener(v -> finish());

        //Reset Progress button
        resetButton.setOnClickListener(v -> showResetConfirmationDialog());

        //Log Out button
        logoutButton.setOnClickListener(v -> {
            Toast.makeText(SettingsActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();
            // TODO: Add logout logic here
        });
    }

    private void showResetConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Reset Progress")
                .setMessage("Are you sure you want to reset your car collection progress? This cannot be undone.")
                .setPositiveButton("Reset", (dialog, which) -> {
                    // TODO: Add logic to clear user collection database
                    Toast.makeText(SettingsActivity.this, "Progress reset successfully", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}