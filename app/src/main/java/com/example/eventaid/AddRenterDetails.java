package com.example.eventaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddRenterDetails extends AppCompatActivity {
    TextInputEditText metRenterName, metPhoneNumber, metDate, metDays;
    Button mBtnAddRenter, mBtnCancelRenter;
    private boolean datePick = false;
    String date;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Rented Car");
    private DatabaseReference rootInv = FirebaseDatabase.getInstance().getReference("Inventory Car");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_renter_details);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        metRenterName = findViewById(R.id.etRenterName);
        metPhoneNumber = findViewById(R.id.etPhoneNumber);
        metDate = findViewById(R.id.etDate);
        metDays = findViewById(R.id.etDays);
        mBtnAddRenter = findViewById(R.id.btnAddRenter);
        mBtnCancelRenter = findViewById(R.id.btnCancelRenter);

        //pop up a calendar for picking purpose
        metDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddRenterDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        date = dayOfMonth + "/" + month + "/" + year;
                        metDate.setText(date);
                        datePick = true;
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        //add renter details to database, and set status to renting(inventory)
        mBtnAddRenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(metRenterName.getText().toString().isEmpty()){
                    Toast.makeText(AddRenterDetails.this, "Please enter renter name!", Toast.LENGTH_SHORT).show();
                }
                else if(!isDigit(metPhoneNumber.getText().toString().trim()) || metPhoneNumber.length() < 10 || metPhoneNumber.length() > 11){
                    Toast.makeText(AddRenterDetails.this, "Please enter or re-enter phone number!", Toast.LENGTH_SHORT).show();
                }
                else if(!datePick) {
                    Toast.makeText(AddRenterDetails.this, "Please select date!", Toast.LENGTH_SHORT).show();
                }
                else if(!isDigit(metDays.getText().toString().trim())){
                    Toast.makeText(AddRenterDetails.this, "Please enter or re-enter days!", Toast.LENGTH_SHORT).show();
                }
                else{
                    upload();
                    startActivity(new Intent(AddRenterDetails.this, MainActivity.class));
                    finish();
                }
            }
        });

        //cancel adding and end activity
        mBtnCancelRenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //upload the details to the database
    private void upload(){
        String days = metDays.getText().toString().trim() + " Days";
        RentedModels upload = new RentedModels(getIntent().getStringExtra("rentImg"), getIntent().getStringExtra("rentCarName"), getIntent().getStringExtra("rentBrand"), metRenterName.getText().toString().trim(), metPhoneNumber.getText().toString().trim(), date, days, getIntent().getStringExtra("rentCarPlate"), getIntent().getStringExtra("rentTransmission"), getIntent().getStringExtra("rentDriven"), getIntent().getStringExtra("rentBody"), getIntent().getStringExtra("rentPrice"));
        root.child(getIntent().getStringExtra("rentCarPlate")).setValue(upload)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        rootInv.child(getIntent().getStringExtra("rentBrand")).child(getIntent().getStringExtra("rentCarPlate")).child("status").setValue("Rented");
                        Toast.makeText(AddRenterDetails.this, "Rented car successful", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddRenterDetails.this, "Error to rent car", Toast.LENGTH_LONG).show();
                    }
                });
    }

    //check if is digit
    private boolean isDigit(String txt){
        if(txt.isEmpty() || !txt.matches("[0-9]+")){
            return false;
        }
        else{
            return true;
        }
    }
}
