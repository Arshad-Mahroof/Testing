package com.example.citycyclerentals;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class ShowBookingsActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private LinearLayout bookingsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bookings);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        bookingsContainer = findViewById(R.id.bookingsContainer); // Container to show bookings

        loadBookings();
    }

    private void loadBookings() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            return;
        }

        db.collection("booking")
                .whereEqualTo("userId", user.getUid()) // Fetch only current user's bookings
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    bookingsContainer.removeAllViews(); // Clear previous views
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String docId = document.getId(); // Firestore document ID
                        String scootyName = document.getString("scootyName");
                        String scootyPrice = document.getString("scootyPrice");
                        String scootyLocation = document.getString("scootyLocation");
                        int scootyImage = document.getLong("scootyImage").intValue();

                        View bookingView = createBookingView(docId, scootyName, scootyPrice, scootyLocation, scootyImage);
                        bookingsContainer.addView(bookingView);
                    }
                });
    }

    private View createBookingView(String docId, String scootyName, String scootyPrice, String scootyLocation, int scootyImage) {
        View view = getLayoutInflater().inflate(R.layout.item_bookings, null);

        ImageView imageView = view.findViewById(R.id.scootyImage);
        TextView nameView = view.findViewById(R.id.scootyName);
        TextView priceView = view.findViewById(R.id.scootyPrice);
        TextView locationView = view.findViewById(R.id.scootyLocation);
        Button deleteButton = view.findViewById(R.id.deleteBookingButton); // ✅ New Delete Button

        imageView.setImageResource(scootyImage);
        nameView.setText(scootyName);
        priceView.setText("Price: " + scootyPrice);
        locationView.setText("Location: " + scootyLocation);

        deleteButton.setOnClickListener(v -> {
            Toast.makeText(ShowBookingsActivity.this, scootyName + " Booking Deleted successfully!", Toast.LENGTH_SHORT).show();
            deleteBooking(docId, view);
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        view.setLayoutParams(params);

        return view;
    }

    private void deleteBooking(String docId, View view) {
        // ✅ Delete booking from Firestore
        db.collection("booking").document(docId)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    bookingsContainer.removeView(view); // ✅ Remove from UI after deletion
                })
                .addOnFailureListener(e -> {
                    // Handle error if needed
                });
    }
}
