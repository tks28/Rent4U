package com.example.eventaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DamagedCarDetails extends AppCompatActivity {
    private ImageView damagedCarImg;
    private CollapsingToolbarLayout dcModelName;
    private TextView mTechnicianName, mTechnicianPhoneNumber, mSentDate, mRepairDays, mDCCarPlate, mDCCarTransmission, mDCCarDriven, mDCCarBody, mDCPricePerDay;
    private Button mBtnRepaired;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Damaged Car");
    private DatabaseReference rootInv = FirebaseDatabase.getInstance().getReference("Inventory Car");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.damaged_car_details);

        damagedCarImg = findViewById(R.id.imageViewDC);
        dcModelName = findViewById(R.id.dcCTLayout);
        mTechnicianName = findViewById(R.id.tvDCTechnicianName);
        mTechnicianPhoneNumber = findViewById(R.id.tvDCTechnicianPhoneNumber);
        mSentDate = findViewById(R.id.tvDCRepairDate);
        mRepairDays = findViewById(R.id.tvDCRepairDays);
        mDCCarPlate = findViewById(R.id.tvDCCarPlate);
        mDCCarTransmission = findViewById(R.id.tvDCCarTransmission);
        mDCCarDriven = findViewById(R.id.tvDCCarDriven);
        mDCCarBody = findViewById(R.id.tvDCCarBody);
        mDCPricePerDay = findViewById(R.id.tvDCPricePerDay);
        mBtnRepaired = findViewById(R.id.btnRepaired);

        //set details
        Picasso.get().load(getIntent().getStringExtra("dcImg")).fit().centerCrop().into(damagedCarImg);
        dcModelName.setTitle(getIntent().getStringExtra("dcModel"));
        mTechnicianName.setText(getIntent().getStringExtra("technicianName"));
        mTechnicianPhoneNumber.setText(getIntent().getStringExtra("technicianPhone"));
        mSentDate.setText(getIntent().getStringExtra("sentDate"));
        mRepairDays.setText(getIntent().getStringExtra("repairDays"));
        mDCCarPlate.setText(getIntent().getStringExtra("dcCarPlate"));
        mDCCarTransmission.setText(getIntent().getStringExtra("dcTransmission"));
        mDCCarDriven.setText(getIntent().getStringExtra("dcDriven"));
        mDCCarBody.setText(getIntent().getStringExtra("dcBody"));
        mDCPricePerDay.setText(getIntent().getStringExtra("dcPrice"));

        //delete details from database, and change status to available(inventory)
        mBtnRepaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.child(mDCCarPlate.getText().toString()).removeValue();
                rootInv.child(getIntent().getStringExtra("dcBrand")).child(getIntent().getStringExtra("dcCarPlate")).child("status").setValue("Available");
                Toast.makeText(DamagedCarDetails.this, "Damaged car repaired!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(DamagedCarDetails.this, MainActivity.class));
                finish();
            }
        });
    }
}