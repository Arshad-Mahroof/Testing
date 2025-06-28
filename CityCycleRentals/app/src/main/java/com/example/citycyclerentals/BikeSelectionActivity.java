package com.example.citycyclerentals;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BikeSelectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private com.example.citycyclerentals.BikeAdapter adapter;
    private List<com.example.citycyclerentals.Bike> bikeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_selection);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

// Initialize Adapter
        com.example.citycyclerentals.BikeAdapter adapter = new com.example.citycyclerentals.BikeAdapter(this, bikeList);
        recyclerView.setAdapter(adapter);

        // Sample Data
        ArrayList<com.example.citycyclerentals.Bike> bikeList = new ArrayList<>();
        bikeList.add(new Bike("Royal Enfield 5000", R.drawable.royal_enfield, "Rs. 2000/day", "Colombo"));
        bikeList.add(new Bike("KTM 200 Duke", R.drawable.ktm_duke, "Rs. 1750/day", "Kandy"));
        bikeList.add(new Bike("Yamaha R15", R.drawable.yamaha_r15, "Rs. 1500/day", "Colombo"));
        bikeList.add(new Bike("Honda CBR", R.drawable.honda_cbr, "Rs. 1300/day", "Kandy"));
        bikeList.add(new Bike("Yamaha MT-15", R.drawable.yamaha_mt15, "Rs. 2000/day", "Colombo"));
        bikeList.add(new Bike("Kawasaki Ninja ZX-10R", R.drawable.kawasaki_ninja_zx_10r, "Rs. 1750/day", "Kandy"));
        bikeList.add(new Bike("BMW G 310 R", R.drawable.bmw_g_310_r, "Rs. 1500/day", "Colombo"));
        bikeList.add(new Bike("Dominar 400", R.drawable.dominar_400, "Rs. 1300/day", "Kandy"));
        bikeList.add(new Bike("FZ-S FI Ver 4.0 DLX", R.drawable.fzs_ver_4, "Rs. 2000/day", "Colombo"));
        bikeList.add(new Bike("Bajaj Avenger Street 160", R.drawable.bajaj_avenger_street_160, "Rs. 1750/day", "Kandy"));
        bikeList.add(new Bike("Kawasaki Z 800", R.drawable.kawasaki_z800, "Rs. 1500/day", "Colombo"));
        bikeList.add(new Bike("Honda CB Honet", R.drawable.honda_cb_honet, "Rs. 1300/day", "Kandy"));
        bikeList.add(new Bike("Ducati Monster 1200", R.drawable.ducati_monster_1200, "Rs. 1300/day", "Kandy"));
        bikeList.add(new Bike("Aprilia Tuono v4 1100 rr", R.drawable.aprilia_tuono, "Rs. 1300/day", "Kandy"));
        bikeList.add(new Bike("TVS Apache RR 310", R.drawable.tvs_apache_rr_310, "Rs. 1300/day", "Kandy"));


        adapter = new com.example.citycyclerentals.BikeAdapter(this, bikeList);
        recyclerView.setAdapter(adapter);
    }
}
