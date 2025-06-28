package com.example.citycyclerentals;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ScootySelectionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ScootyAdapter adapter;
    private List<Scooty> scootyList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scooty_selection);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ScootyAdapter adapter = new ScootyAdapter(this, scootyList);
        recyclerView.setAdapter(adapter);

        ArrayList<Scooty> scootyList = new ArrayList<>();
        scootyList.add(new Scooty("Honda Dio", R.drawable.honda_dio, "1000", "Colombo"));
        scootyList.add(new Scooty("TVS Ntorq", R.drawable.tvs_ntroq, "1250", "Kandy"));
        scootyList.add(new Scooty("Suzuki Burgman", R.drawable.suzuki_burgman, "1200", "Colombo"));
        scootyList.add(new Scooty("Yamaha Ray ZR", R.drawable.yamaha_ray_zr, "900", "Kandy"));
        scootyList.add(new Scooty("Honda PCX", R.drawable.honda_pcx, "1000", "Colombo"));
        scootyList.add(new Scooty("TVS Jupiter", R.drawable.tvs_jupiter, "1250", "Kandy"));
        scootyList.add(new Scooty("TVS iQube", R.drawable.tvs_iqube, "1200", "Colombo"));
        scootyList.add(new Scooty("Hero Pleasure", R.drawable.hero_pleasure, "900", "Kandy"));
        scootyList.add(new Scooty("Hero Xoom", R.drawable.hero_xoom, "1000", "Colombo"));
        scootyList.add(new Scooty("Yamaha Fascino 125", R.drawable.yamaha_fascino_125, "1250", "Kandy"));
        scootyList.add(new Scooty("Activa HSmart", R.drawable.activa_hsmart, "1200", "Colombo"));
        scootyList.add(new Scooty("Yamaha Motoes Aurex 150", R.drawable.yamaha_motoes_aurex_150, "900", "Kandy"));
        scootyList.add(new Scooty("Honda Grazia", R.drawable.honda_grazia, "1000", "Colombo"));
        scootyList.add(new Scooty("Honda Vario 160", R.drawable.honda_vario_160_, "1250", "Kandy"));
        scootyList.add(new Scooty("Honda RX125 Elite Airblade", R.drawable.honda_rx125_elite_airblade, "1200", "Colombo"));

        adapter = new ScootyAdapter(this, scootyList);
        recyclerView.setAdapter(adapter);
    }
}
