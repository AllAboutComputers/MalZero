package com.example.malzero;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button nextButton;
    private Animation clickAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ Check if onboarding is already completed
        SharedPreferences prefs = getSharedPreferences("malzero_prefs", MODE_PRIVATE);
        boolean isOnboardingCompleted = prefs.getBoolean("isOnboardingCompleted", false);

        if (isOnboardingCompleted) {
            // Skip onboarding and go to MainActivity directly
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Prevent user from coming back here
            return;
        }

        // Load onboarding screen
        setContentView(R.layout.activity_welcome);

        // Load the button click animation
        clickAnim = AnimationUtils.loadAnimation(this, R.anim.button_click);

        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(v -> {
            // Play click animation
            v.startAnimation(clickAnim);

            // Delay transition until animation ends
            v.postDelayed(() -> {
                Intent intent = new Intent(WelcomeActivity.this, VPNActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }, 100); // Same duration as the button_click.xml
        });
    }
}
