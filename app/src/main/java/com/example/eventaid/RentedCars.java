package com.example.eventaid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
//Rented Car Fragment

public class RentedCars extends Fragment {

    private RecyclerView rvRented;
    private RentedAdapter rentedAdapter;
    private ArrayList<RentedModels> rentedList;

    private DatabaseReference root;

    public RentedCars() {
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
        View view = inflater.inflate(R.layout.rented_fragment, container, false);
        InitializeRentedCardView(view);
        return view;
    }

    //initialize the car that are currently renting
    private void InitializeRentedCardView(View v) {
        rvRented = v.findViewById(R.id.rvRented);
        rvRented.setLayoutManager(new LinearLayoutManager(v.getContext()));
        rentedList = new ArrayList<>();
        root = FirebaseDatabase.getInstance().getReference("Rented Car");

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    RentedModels mdl = dataSnapshot.getValue(RentedModels.class);
                    rentedList.add(mdl);
                }
                rentedAdapter = new RentedAdapter(v.getContext(), rentedList);
                rvRented.setAdapter(rentedAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}