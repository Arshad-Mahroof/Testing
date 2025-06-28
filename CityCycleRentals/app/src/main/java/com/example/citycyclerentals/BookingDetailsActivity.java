package com.example.citycyclerentals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookingDetailsActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // ✅ Get data from Intent
        String bikeName = getIntent().getStringExtra("bike_name");
        int bikeImage = getIntent().getIntExtra("bike_image", 0);
        String bikePrice = getIntent().getStringExtra("bike_price");
        String bikeLocation = getIntent().getStringExtra("bike_location");

        // ✅ Bind views
        ImageView bikeImageView = findViewById(R.id.bikeImage);
        TextView bikeNameView = findViewById(R.id.bikeName);
        TextView bikePriceView = findViewById(R.id.bikePrice);
        TextView bikeLocationView = findViewById(R.id.bikeLocation);
        Button payNowButton = findViewById(R.id.payNowButton);

        // ✅ Set data
        bikeNameView.setText(bikeName);
        bikeImageView.setImageResource(bikeImage);
        bikePriceView.setText("Price: " + bikePrice);
        bikeLocationView.setText("Location: " + bikeLocation);

        // ✅ Handle "Pay Now" button click (Save to Firestore)
        payNowButton.setOnClickListener(v -> {
            Toast.makeText(BookingDetailsActivity.this, bikeName + " Booking saved successfully!", Toast.LENGTH_SHORT).show();
            saveBookingToFirestore(bikeName, bikePrice, bikeLocation, bikeImage);
        });
    }

    private void saveBookingToFirestore(String bikeName, String bikePrice, String bikeLocation, int bikeImage) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not logged in!", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = user.getUid();

        // ✅ Store multiple bookings with a unique ID
        Map<String, Object> booking = new HashMap<>();
        booking.put("bikeName", bikeName);
        booking.put("bikePrice", bikePrice);
        booking.put("bikeLocation", bikeLocation);
        booking.put("bikeImage", bikeImage);
        booking.put("userId", userId);

        db.collection("bookings") // Collection for all bookings
                .add(booking) // ✅ Creates a new document instead of overwriting
                .addOnSuccessListener(documentReference -> {
                    Intent intent = new Intent(BookingDetailsActivity.this, ShowBookingActivity.class);
                    startActivity(intent); // ✅ Navigate to show bookings page after successful booking
                })
                .addOnFailureListener(e ->
                        Toast.makeText(BookingDetailsActivity.this, "Booking failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
