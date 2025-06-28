package com.example.citycyclerentals;

import android.content.Context;
import android.content.Intent;  // Added Intent import
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.BikeViewHolder> {
    private final Context context;
    private final List<com.example.citycyclerentals.Bike> bikeList;

    public BikeAdapter(Context context, List<com.example.citycyclerentals.Bike> bikeList) {
        this.context = context;
        this.bikeList = bikeList;
    }

    @NonNull
    @Override
    public BikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bike, parent, false);
        return new BikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeViewHolder holder, int position) {
        com.example.citycyclerentals.Bike bike = bikeList.get(position);
        holder.bikeName.setText(bike.getName());
        holder.bikeImage.setImageResource(bike.getImageResId());

        // Handle click event to open BookingDetailsActivity with selected bike details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookingDetailsActivity.class);
            intent.putExtra("bike_name", bike.getName());
            intent.putExtra("bike_image", bike.getImageResId());
            intent.putExtra("bike_price", bike.getPrice());
            intent.putExtra("bike_location", bike.getLocation());
            context.startActivity(intent);

            // Toast for confirmation (optional)
            Toast.makeText(context, "Selected: " + bike.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return bikeList.size();
    }

    // ViewHolder Class
    public static class BikeViewHolder extends RecyclerView.ViewHolder {
        TextView bikeName;
        ImageView bikeImage;

        public BikeViewHolder(@NonNull View itemView) {
            super(itemView);
            bikeName = itemView.findViewById(R.id.bikeName);
            bikeImage = itemView.findViewById(R.id.bikeImage);
        }
    }
}
