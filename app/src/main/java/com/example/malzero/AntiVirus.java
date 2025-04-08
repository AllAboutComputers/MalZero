package com.example.malzero;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class AntiVirus extends AppCompatActivity {

    private Button nextButton;
    private Animation clickAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antivirus);

        // Load the button click animation
        clickAnim = AnimationUtils.loadAnimation(this, R.anim.button_click);

        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(v -> {
            // Play click animation
            v.startAnimation(clickAnim);

            // Delay transition until animation ends
            v.postDelayed(() -> {
                // ✅ Save onboarding completion flag
                SharedPreferences prefs = getSharedPreferences("malzero_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isOnboardingCompleted", true);
                editor.apply();

                // ✅ Navigate to MainActivity and clear back stack
                Intent intent = new Intent(AntiVirus.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }, 100); // Same as animation duration
        });
    }
}
