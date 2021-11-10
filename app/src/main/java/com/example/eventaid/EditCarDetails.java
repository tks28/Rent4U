package com.example.eventaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class EditCarDetails extends AppCompatActivity {
    private String transmissionType, drivenType, bodyType;
    private ImageView mEditImg;
    private TextInputEditText mEditModel, mEditYear, mEditPricePerDay;
    AutoCompleteTextView transmissionEdit, drivenEdit, bodyEdit;
    private Button mbtnEditCar, mbtnDelete, mbtnCancelEdit;

    String[] transmissionItems = {"Automatic", "Manual"};
    String[] drivenItems = {"FWD", "RWD", "AWD"};
    String[] bodyItems = {"Sedan", "SUV", "MPV", "Pickup Truck", "Coupe", "Hatchback", "Convertible"};

    ArrayAdapter<String> transmissionAdapter;
    ArrayAdapter<String> drivenAdapter;
    ArrayAdapter<String> bodyAdapter;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Inventory Car");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car_details);

        mEditImg = findViewById(R.id.editImageView);
        mEditModel = findViewById(R.id.etEditModel);
        mEditYear = findViewById(R.id.etEditYear);
        transmissionEdit = findViewById(R.id.spEditTransmission);
        drivenEdit= findViewById(R.id.spEditDriven);
        bodyEdit = findViewById(R.id.spEditBody);
        mEditPricePerDay = findViewById(R.id.etEditPrice);
        mbtnEditCar = findViewById(R.id.btnEditCar);
        mbtnDelete = findViewById(R.id.btnDeleteEditCar);
        mbtnCancelEdit = findViewById(R.id.btnCancelEditCar);

        //set details
        Picasso.get().load(getIntent().getStringExtra("editImg")).fit().centerCrop().into(mEditImg);
        mEditModel.setText(getIntent().getStringExtra("editModel"));
        mEditYear.setText(getIntent().getStringExtra("editYear"));
        transmissionEdit.setText(getIntent().getStringExtra("editTransmission"));
        drivenEdit.setText(getIntent().getStringExtra("editDriven"));
        bodyEdit.setText(getIntent().getStringExtra("editBody"));
        mEditPricePerDay.setText(getIntent().getStringExtra("editPrice"));

        //Initialize spinners options
        transmissionType = getIntent().getStringExtra("editTransmission");
        drivenType = getIntent().getStringExtra("editDriven");
        bodyType = getIntent().getStringExtra("editBody");

        //set spinners
        transmissionAdapter = new ArrayAdapter<String>(this, R.layout.text_item, transmissionItems);
        drivenAdapter = new ArrayAdapter<String>(this, R.layout.text_item, drivenItems);
        bodyAdapter = new ArrayAdapter<String>(this, R.layout.text_item, bodyItems);

        //set spinners adapters
        transmissionEdit.setAdapter(transmissionAdapter);
        drivenEdit.setAdapter(drivenAdapter);
        bodyEdit.setAdapter(bodyAdapter);

        //select transmission type
        transmissionEdit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                transmissionType = parent.getItemAtPosition(position).toString();
            }
        });

        //select driven type
        drivenEdit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drivenType = parent.getItemAtPosition(position).toString();
            }
        });

        //select body type
        bodyEdit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bodyType = parent.getItemAtPosition(position).toString();
            }
        });

        //edit data from firebase
        mbtnEditCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditModel.getText().toString().trim().isEmpty()){
                    Toast.makeText(EditCarDetails.this, "Please enter model again!", Toast.LENGTH_LONG).show();
                }
                else if(!isDigit(mEditYear.getText().toString().trim()) || !isDigit(mEditPricePerDay.getText().toString().trim())){
                    Toast.makeText(EditCarDetails.this, "Year and Price required numeric only!", Toast.LENGTH_LONG).show();
                }
                else {
                    root.child(getIntent().getStringExtra("editBrand")).child(getIntent().getStringExtra("editCarPlate")).child("mtvInventoryCarName").setValue(getIntent().getStringExtra("editBrand") + " " + mEditModel.getText().toString().trim() + " " + mEditYear.getText().toString().trim());
                    root.child(getIntent().getStringExtra("editBrand")).child(getIntent().getStringExtra("editCarPlate")).child("mtvInventoryModel").setValue(mEditModel.getText().toString().trim());
                    root.child(getIntent().getStringExtra("editBrand")).child(getIntent().getStringExtra("editCarPlate")).child("mtvInventoryYear").setValue(mEditYear.getText().toString().trim());
                    root.child(getIntent().getStringExtra("editBrand")).child(getIntent().getStringExtra("editCarPlate")).child("mtvInventoryCarTransmission").setValue(transmissionType);
                    root.child(getIntent().getStringExtra("editBrand")).child(getIntent().getStringExtra("editCarPlate")).child("mtvInventoryCarDriven").setValue(drivenType);
                    root.child(getIntent().getStringExtra("editBrand")).child(getIntent().getStringExtra("editCarPlate")).child("mtvInventoryCarBody").setValue(bodyType);
                    root.child(getIntent().getStringExtra("editBrand")).child(getIntent().getStringExtra("editCarPlate")).child("mtvInventoryPricePerDay").setValue("RM" + mEditPricePerDay.getText().toString().trim() + " / day");

                    startActivity(new Intent(EditCarDetails.this, MainActivity.class));
                    Toast.makeText(EditCarDetails.this, "Car details edited!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        //delete car details
        mbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.child(getIntent().getStringExtra("editBrand")).child(getIntent().getStringExtra("editCarPlate")).removeValue();
                startActivity(new Intent(EditCarDetails.this, MainActivity.class));
                finish();
            }
        });

        //cancel editing and end activity
        mbtnCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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