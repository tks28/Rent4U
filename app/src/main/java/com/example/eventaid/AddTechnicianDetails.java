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

public class AddTechnicianDetails extends AppCompatActivity {
    TextInputEditText metTechnicianName, metTechnicianPhoneNumber, metTechnicianDate, metTechnicianDays;
    Button mBtnAddTechnician, mBtnCancelTechnician;
    private boolean datePick = false;
    String date;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Damaged Car");
    private DatabaseReference rootInv = FirebaseDatabase.getInstance().getReference("Inventory Car");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_technician_details);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        metTechnicianName = findViewById(R.id.etTechnicianName);
        metTechnicianPhoneNumber = findViewById(R.id.etTechnicianPhoneNumber);
        metTechnicianDate = findViewById(R.id.etTechnicianDate);
        metTechnicianDays = findViewById(R.id.etTechnicianDays);
        mBtnAddTechnician = findViewById(R.id.btnAddTechnician);
        mBtnCancelTechnician = findViewById(R.id.btnCancelTechnician);

        //pop up a calendar for picking purpose
        metTechnicianDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTechnicianDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        date = dayOfMonth + "/" + month + "/" + year;
                        metTechnicianDate.setText(date);
                        datePick = true;
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        //add repair details to database, and set status to repairing(inventory)
        mBtnAddTechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(metTechnicianName.getText().toString().isEmpty()){
                    Toast.makeText(AddTechnicianDetails.this, "Please enter technician/company name!", Toast.LENGTH_SHORT).show();
                }
                else if(!isDigit(metTechnicianPhoneNumber.getText().toString().trim()) || metTechnicianPhoneNumber.length() < 10 || metTechnicianPhoneNumber.length() > 11){
                    Toast.makeText(AddTechnicianDetails.this, "Please re-enter phone number!", Toast.LENGTH_SHORT).show();
                }
                else if(!datePick) {
                    Toast.makeText(AddTechnicianDetails.this, "Please select date!", Toast.LENGTH_SHORT).show();
                }
                else if(!isDigit(metTechnicianDays.getText().toString().trim())){
                    Toast.makeText(AddTechnicianDetails.this, "Please enter or re-enter days!", Toast.LENGTH_SHORT).show();
                }
                else{
                    upload();
                    startActivity(new Intent(AddTechnicianDetails.this, MainActivity.class));
                    finish();
                }
            }
        });

        //cancel adding and end activity
        mBtnCancelTechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //add technician details to database, and set status to repairing(inventory)
    private void upload(){
        String days = metTechnicianDays.getText().toString().trim() + " Days";
        DamagedCarModels upload = new DamagedCarModels(getIntent().getStringExtra("repImg"), getIntent().getStringExtra("repCarName"), getIntent().getStringExtra("repBrand"), metTechnicianName.getText().toString().trim(), metTechnicianPhoneNumber.getText().toString().trim(), date, days, getIntent().getStringExtra("repCarPlate"), getIntent().getStringExtra("repTransmission"), getIntent().getStringExtra("repDriven"), getIntent().getStringExtra("repBody"), getIntent().getStringExtra("repPrice"));
        root.child(getIntent().getStringExtra("repCarPlate")).setValue(upload)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddTechnicianDetails.this, "Car sent to repair", Toast.LENGTH_LONG).show();
                        rootInv.child(getIntent().getStringExtra("repBrand")).child(getIntent().getStringExtra("repCarPlate")).child("status").setValue("Repairing");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddTechnicianDetails.this, "Failed to repair", Toast.LENGTH_LONG).show();
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