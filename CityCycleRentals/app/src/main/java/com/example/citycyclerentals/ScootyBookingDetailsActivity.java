package com.example.citycyclerentals;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ScootyBookingDetailsActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scooty_booking_details);

        // ✅ Initialize Firestore & Firebase Auth
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // ✅ Get data from Intent
        String scooterName = getIntent().getStringExtra("scooter_name");
        int scooterImage = getIntent().getIntExtra("scooter_image", 0);
        String scooterPrice = getIntent().getStringExtra("scooter_price");
        String scooterLocation = getIntent().getStringExtra("scooter_location");

        // ✅ Log values for debugging
        Log.d("ScootyBooking", "Scooty Name: " + scooterName);
        Log.d("ScootyBooking", "Scooty Image ID: " + scooterImage);
        Log.d("ScootyBooking", "Scooty Price: " + scooterPrice);
        Log.d("ScootyBooking", "Scooty Location: " + scooterLocation);

        // ✅ Bind views
        ImageView scooterImageView = findViewById(R.id.scooterImage);
        TextView scooterNameView = findViewById(R.id.scooterName);
        TextView scooterPriceView = findViewById(R.id.scooterPrice);
        TextView scooterLocationView = findViewById(R.id.scooterLocation);
        Button payNowButton = findViewById(R.id.payNowButton);

        // ✅ Set data
        scooterNameView.setText(scooterName);
        scooterImageView.setImageResource(scooterImage);
        scooterPriceView.setText("Price: " + scooterPrice);
        scooterLocationView.setText("Location: " + scooterLocation);

        // ✅ Handle "Pay Now" button click (Saves booking to Firestore)
        payNowButton.setOnClickListener(v -> {
            Toast.makeText(ScootyBookingDetailsActivity.this, scooterName + " Booking saved successfully!", Toast.LENGTH_SHORT).show();
            saveBookingDetails(scooterName, scooterImage, scooterPrice, scooterLocation);
        });
    }

    // ✅ Save booking details to Firestore
    private void saveBookingDetails(String scootyName, int scootyImage, String scootyPrice, String scootyLocation) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not logged in!", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = user.getUid(); // Get the logged-in user's ID

        Map<String, Object> booking = new HashMap<>();
        booking.put("scootyName", scootyName);
        booking.put("scootyImage", scootyImage);
        booking.put("scootyPrice", scootyPrice);
        booking.put("scootyLocation", scootyLocation);
        booking.put("userId", userId); // Store the user ID

        db.collection("booking")
                .add(booking) // Creates a new document for each booking
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(ScootyBookingDetailsActivity.this, "Booking saved successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ScootyBookingDetailsActivity.this, ShowBookingsActivity.class));
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ScootyBookingDetailsActivity.this, "Error saving booking: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
