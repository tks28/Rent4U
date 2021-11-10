package com.example.eventaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

public class InventoryCarDetails extends AppCompatActivity {
    private ImageView carImg;
    private CollapsingToolbarLayout inventoryModelName;
    private TextView mInventoryCarPlate, mInventoryCarTransmission, mInventoryCarDriven, mInventoryCarBody, mInventoryPricePerDay;
    private Button mBtnEdit, mBtnRent, mBtnRepair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_car_details);

        carImg = findViewById(R.id.imageViewCar);
        inventoryModelName = findViewById(R.id.carCTLayout);
        mInventoryCarPlate = findViewById(R.id.tvInventoryCarPlate);
        mInventoryCarTransmission = findViewById(R.id.tvInventoryCarTransmission);
        mInventoryCarDriven = findViewById(R.id.tvInventoryCarDriven);
        mInventoryCarBody= findViewById(R.id.tvInventoryCarBody);
        mInventoryPricePerDay = findViewById(R.id.tvInventoryPricePerDay);
        mBtnEdit = findViewById(R.id.btnEdit);
        mBtnRent = findViewById(R.id.btnRent);
        mBtnRepair = findViewById(R.id.btnRepair);

        //set car details
        Picasso.get().load(getIntent().getStringExtra("carImg")).fit().centerCrop().into(carImg);
        inventoryModelName.setTitle(getIntent().getStringExtra("carName"));
        mInventoryCarPlate.setText(getIntent().getStringExtra("carPlate"));
        mInventoryCarTransmission.setText(getIntent().getStringExtra("carTransmission"));
        mInventoryCarDriven.setText(getIntent().getStringExtra("carDriven"));
        mInventoryCarBody.setText(getIntent().getStringExtra("carBody"));
        mInventoryPricePerDay.setText(getIntent().getStringExtra("carPrice"));

        //go to edit car details
        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryCarDetails.this, EditCarDetails.class);
                intent.putExtra("editImg", getIntent().getStringExtra("carImg"));
                intent.putExtra("editBrand", getIntent().getStringExtra("carBrand"));
                intent.putExtra("editModel", getIntent().getStringExtra("carModel"));
                intent.putExtra("editYear", getIntent().getStringExtra("carYear"));
                intent.putExtra("editCarPlate", getIntent().getStringExtra("carPlate"));
                intent.putExtra("editTransmission", getIntent().getStringExtra("carTransmission"));
                intent.putExtra("editDriven", getIntent().getStringExtra("carDriven"));
                intent.putExtra("editBody", getIntent().getStringExtra("carBody"));
                intent.putExtra("editPrice", getIntent().getStringExtra("carPrice"));
                startActivity(intent);
                finish();
            }
        });

        //go to add renter details
        mBtnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryCarDetails.this, AddRenterDetails.class);
                intent.putExtra("rentImg", getIntent().getStringExtra("carImg"));
                intent.putExtra("rentBrand", getIntent().getStringExtra("carBrand"));
                intent.putExtra("rentCarName", getIntent().getStringExtra("carName"));
                intent.putExtra("rentCarPlate", getIntent().getStringExtra("carPlate"));
                intent.putExtra("rentTransmission", getIntent().getStringExtra("carTransmission"));
                intent.putExtra("rentDriven", getIntent().getStringExtra("carDriven"));
                intent.putExtra("rentBody", getIntent().getStringExtra("carBody"));
                intent.putExtra("rentPrice", getIntent().getStringExtra("carPrice"));
                startActivity(intent);
                finish();
            }
        });

        //go to add technician details
        mBtnRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryCarDetails.this, AddTechnicianDetails.class);
                intent.putExtra("repImg", getIntent().getStringExtra("carImg"));
                intent.putExtra("repBrand", getIntent().getStringExtra("carBrand"));
                intent.putExtra("repCarName", getIntent().getStringExtra("carName"));
                intent.putExtra("repCarPlate", getIntent().getStringExtra("carPlate"));
                intent.putExtra("repTransmission", getIntent().getStringExtra("carTransmission"));
                intent.putExtra("repDriven", getIntent().getStringExtra("carDriven"));
                intent.putExtra("repBody", getIntent().getStringExtra("carBody"));
                intent.putExtra("repPrice", getIntent().getStringExtra("carPrice"));
                startActivity(intent);
                finish();
            }
        });
    }
}