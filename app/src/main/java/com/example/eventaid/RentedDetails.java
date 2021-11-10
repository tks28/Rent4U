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

public class RentedDetails extends AppCompatActivity {
    private ImageView rentedImg;
    private CollapsingToolbarLayout modelName;
    private TextView mRenterName, mRenterPhoneNumber, mRentDate, mRentDays, mCarPlate, mCarTransmission, mCarDriven, mCarBody, mPricePerDay;
    private Button mBtnReturn, mBtnCancelRent;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Rented Car");
    private DatabaseReference rootInv = FirebaseDatabase.getInstance().getReference("Inventory Car");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rented_details_activity);

        rentedImg = findViewById(R.id.imageView);
        modelName = findViewById(R.id.ctLayout);
        mRenterName = findViewById(R.id.tvRentedRenterName);
        mRenterPhoneNumber = findViewById(R.id.tvRentedRenterPhoneNumber);
        mRentDate = findViewById(R.id.tvRentedRentDate);
        mRentDays = findViewById(R.id.tvRentedRentDays);
        mCarPlate = findViewById(R.id.tvRentedCarPlate);
        mCarTransmission = findViewById(R.id.tvRentedCarTransmission);
        mCarDriven = findViewById(R.id.tvRentedCarDriven);
        mCarBody = findViewById(R.id.tvRentedCarBody);
        mPricePerDay = findViewById(R.id.tvRentedPricePerDay);
        mBtnReturn = findViewById(R.id.btnReturn);
        mBtnCancelRent = findViewById(R.id.btnCancelRent);

        //set rented car details
        Picasso.get().load(getIntent().getStringExtra("img")).fit().centerCrop().into(rentedImg);
        modelName.setTitle(getIntent().getStringExtra("model"));
        mRenterName.setText(getIntent().getStringExtra("renterName"));
        mRenterPhoneNumber.setText(getIntent().getStringExtra("renterPhone"));
        mRentDate.setText(getIntent().getStringExtra("rentDate"));
        mRentDays.setText(getIntent().getStringExtra("rentDays"));
        mCarPlate.setText(getIntent().getStringExtra("carPlate"));
        mCarTransmission.setText(getIntent().getStringExtra("transmission"));
        mCarDriven.setText(getIntent().getStringExtra("driven"));
        mCarBody.setText(getIntent().getStringExtra("body"));
        mPricePerDay.setText(getIntent().getStringExtra("price"));

        //delete details from database, and change status to available(inventory)
        mBtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.child(mCarPlate.getText().toString()).removeValue();
                rootInv.child(getIntent().getStringExtra("rBrand")).child(getIntent().getStringExtra("carPlate")).child("status").setValue("Available");
                Toast.makeText(RentedDetails.this, "Car rented returned!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RentedDetails.this, MainActivity.class));
                finish();
            }
        });

        //delete details from database, and change status to available(inventory)
        mBtnCancelRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}