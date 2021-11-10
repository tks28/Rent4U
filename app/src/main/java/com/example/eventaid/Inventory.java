package com.example.eventaid;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
//Inventory Fragment

public class Inventory extends Fragment {

    private RecyclerView rvInventory;
    private InventoryAdapter inventoryAdapter;
    private ArrayList<InventoryModels> inventoryList;
    private FloatingActionButton addLogo;

    private DatabaseReference root;

    public Inventory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.inventory_fragment, container, false);
        addLogo = view.findViewById(R.id.logoFloatingBtn);
        addLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), AddBrand.class);
                startActivity(i);
            }
        });

        InitializeInventoryCardView(view);
        return view;
    }

    //initialize all the brand that is in the database
    private void InitializeInventoryCardView(View v) {
        rvInventory = v.findViewById(R.id.rvInventory);
        rvInventory.setLayoutManager(new GridLayoutManager(v.getContext(), 2));
        inventoryList = new ArrayList<>();
        root = FirebaseDatabase.getInstance().getReference("Brand");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    InventoryModels mdl = dataSnapshot.getValue(InventoryModels.class);
                    inventoryList.add(mdl);
                }
                inventoryAdapter = new InventoryAdapter(v.getContext(), inventoryList);
                rvInventory.setAdapter(inventoryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}