package com.example.citycyclerentals;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShowItemActivity extends AppCompatActivity {

    Button bikeButton, scootyButton, showBookingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);

        // Initializing buttons
        bikeButton = findViewById(R.id.btn_Bike);
        scootyButton = findViewById(R.id.btn_scooty);

        // Navigate to LoginActivity when the "Bike" button is clicked
        bikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowItemActivity.this, ShowBookingActivity.class);
                startActivity(intent);
            }
        });

        // Navigate to RegisterActivity when the "Scooty" button is clicked
        scootyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowItemActivity.this, ShowBookingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
