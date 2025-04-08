package com.example.malzero;
import android.content.Intent;
import android.os.Bundle;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class VPNActivity extends AppCompatActivity{

    private Button nextButton;
    private Animation clickAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpn);

        // Load the button click animation
        clickAnim = AnimationUtils.loadAnimation(this, R.anim.button_click);

        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(v -> {
            // Play click animation
            v.startAnimation(clickAnim);

            // Delay transition until animation ends
            v.postDelayed(() -> {
                Intent intent = new Intent(VPNActivity.this, MalwareDetect.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }, 100); // Same duration as the button_click.xml
        });
    }
}
