package com.example.citycyclerentals;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScootyAdapter extends RecyclerView.Adapter<ScootyAdapter.ScootyViewHolder> {
    private final Context context;
    private final List<Scooty> scooterList;

    public ScootyAdapter(Context context, List<Scooty> scooterList) {
        this.context = context;
        this.scooterList = scooterList;
    }

    @NonNull
    @Override
    public ScootyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_scooty, parent, false);
        return new ScootyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScootyViewHolder holder, int position) {
        Scooty scooter = scooterList.get(position);
        holder.scooterName.setText(scooter.getName());
        holder.scooterImage.setImageResource(scooter.getImageResId());

        // Handle click event
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ScootyBookingDetailsActivity.class);
            intent.putExtra("scooter_name", scooter.getName());
            intent.putExtra("scooter_image", scooter.getImageResId());
            intent.putExtra("scooter_price", scooter.getPrice());
            intent.putExtra("scooter_location", scooter.getLocation());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return scooterList.size();
    }

    public static class ScootyViewHolder extends RecyclerView.ViewHolder {
        TextView scooterName, scooterPrice;
        ImageView scooterImage;

        public ScootyViewHolder(@NonNull View itemView) {
            super(itemView);
            scooterName = itemView.findViewById(R.id.scooterName);
            scooterImage = itemView.findViewById(R.id.scooterImage);
        }
    }
}
