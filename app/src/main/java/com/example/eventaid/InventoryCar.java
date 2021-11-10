package com.example.eventaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InventoryCar extends AppCompatActivity {

    private RecyclerView rvInventoryCar;
    private InventoryCarAdapter carAdapter;
    private ArrayList<InventoryCarModels> carList;
    private FloatingActionButton addCar;

    private Query rootCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_car);

        addCar = findViewById(R.id.carFloatingBtn);
        rvInventoryCar = findViewById(R.id.rvInventoryCar);
        rvInventoryCar.setHasFixedSize(true);
        rvInventoryCar.setLayoutManager(new LinearLayoutManager(this));
        carList = new ArrayList<>();

        //get the car from database
        rootCar = FirebaseDatabase.getInstance().getReference("Inventory Car").child(getIntent().getStringExtra("bName")).orderByChild("status").equalTo("Available");
        rootCar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    InventoryCarModels mdl = dataSnapshot.getValue(InventoryCarModels.class);
                    carList.add(mdl);
                }
                carAdapter = new InventoryCarAdapter(InventoryCar.this, carList);
                rvInventoryCar.setAdapter(carAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //floating button
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InventoryCar.this, AddCar.class);
                i.putExtra("addBrandName", getIntent().getStringExtra("bName"));
                startActivity(i);
            }
        });
    }
}