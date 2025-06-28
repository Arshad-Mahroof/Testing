package com.example.citycyclerentals;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class ShowBookingActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private LinearLayout bookingsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_booking);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        bookingsContainer = findViewById(R.id.bookingsContainer); // Container for displaying bookings

        loadBookings();
    }

    private void loadBookings() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            return;
        }

        db.collection("bookings")
                .whereEqualTo("userId", user.getUid()) // Fetch only current user's bookings
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    bookingsContainer.removeAllViews(); // Clear previous views
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String docId = document.getId(); // Store Firestore document ID
                        String bikeName = document.getString("bikeName");
                        String bikePrice = document.getString("bikePrice");
                        String bikeLocation = document.getString("bikeLocation");
                        int bikeImage = document.getLong("bikeImage").intValue();

                        View bookingView = createBookingView(docId, bikeName, bikePrice, bikeLocation, bikeImage);
                        bookingsContainer.addView(bookingView);
                    }
                });
    }

    private View createBookingView(String docId, String bikeName, String bikePrice, String bikeLocation, int bikeImage) {
        View view = getLayoutInflater().inflate(R.layout.item_booking, null);

        ImageView imageView = view.findViewById(R.id.bikeImage);
        TextView nameView = view.findViewById(R.id.bikeName);
        TextView priceView = view.findViewById(R.id.bikePrice);
        TextView locationView = view.findViewById(R.id.bikeLocation);
        Button deleteButton = view.findViewById(R.id.deleteBookingButton); // ✅ New Delete Button

        imageView.setImageResource(bikeImage);
        nameView.setText(bikeName);
        priceView.setText("Price: " + bikePrice);
        locationView.setText("Location: " + bikeLocation);

        deleteButton.setOnClickListener(v -> {
            Toast.makeText(ShowBookingActivity.this, bikeName + " Booking Deleted successfully!", Toast.LENGTH_SHORT).show();
            deleteBooking(docId, view);
        }); // Delete booking

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        view.setLayoutParams(params);

        return view;
    }

    private void deleteBooking(String docId, View view) {
        db.collection("bookings").document(docId)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    bookingsContainer.removeView(view); // ✅ Remove item from UI after deletion
                })
                .addOnFailureListener(e -> {
                    // Handle error if needed
                });
    }
}
