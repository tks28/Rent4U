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
//Damaged Car Fragment

public class DamagedCar extends Fragment {
    private RecyclerView rvDamagedCar;
    private DamagedCarAdapter damageCarAdapter;
    private ArrayList<DamagedCarModels> damageCarList;

    private DatabaseReference root;

    public DamagedCar() {
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
        View view = inflater.inflate(R.layout.damaged_car_fragment, container, false);
        InitializeDamagedCarCardView(view);
        return view;
    }

    //initialize the repairing car in the damaged car page
    private void InitializeDamagedCarCardView(View v) {
        rvDamagedCar = v.findViewById(R.id.rvDamagedCar);
        rvDamagedCar.setLayoutManager(new LinearLayoutManager(v.getContext()));
        damageCarList = new ArrayList<>();
        root = FirebaseDatabase.getInstance().getReference("Damaged Car");

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DamagedCarModels mdl = dataSnapshot.getValue(DamagedCarModels.class);
                    damageCarList.add(mdl);
                }
                damageCarAdapter = new DamagedCarAdapter(v.getContext(), damageCarList);
                rvDamagedCar.setAdapter(damageCarAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}