package com.example.eventaid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCar extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    private String transmissionType = "", drivenType = "", bodyType = "";

    //spinners options
    String[] transmissionItems = {"Automatic", "Manual"};
    String[] drivenItems = {"FWD", "RWD", "AWD"};
    String[] bodyItems = {"Sedan", "SUV", "MPV", "Pickup Truck", "Coupe", "Hatchback", "Convertible"};

    //access database
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Inventory Car");
    private StorageReference ref = FirebaseStorage.getInstance().getReference( "Inventory Car");
    private StorageTask mUploadTask;

    Uri carUri;
    AutoCompleteTextView transmissionTxt, drivenTxt, bodyTxt;
    ImageView carIV;
    TextInputEditText modelET, yearET, carPlateET, priceET;
    Button mBtnAddCar, mBtnCancelCar;

    ArrayAdapter<String> transmissionAdapter;
    ArrayAdapter<String> drivenAdapter;
    ArrayAdapter<String> bodyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        carIV = findViewById(R.id.carImageView);
        modelET = findViewById(R.id.etModel);
        yearET = findViewById(R.id.etYear);
        carPlateET = findViewById(R.id.etCarPlate);
        priceET = findViewById(R.id.etPrice);
        mBtnAddCar = findViewById(R.id.btnAddCar);
        mBtnCancelCar = findViewById(R.id.btnCancelCar);

        transmissionTxt = findViewById(R.id.spTransmission);
        drivenTxt = findViewById(R.id.spDriven);
        bodyTxt = findViewById(R.id.spBody);

        transmissionAdapter = new ArrayAdapter<String>(this, R.layout.text_item, transmissionItems);
        drivenAdapter = new ArrayAdapter<String>(this, R.layout.text_item, drivenItems);
        bodyAdapter = new ArrayAdapter<String>(this, R.layout.text_item, bodyItems);

        //set adapter for the options in spinner
        transmissionTxt.setAdapter(transmissionAdapter);
        drivenTxt.setAdapter(drivenAdapter);
        bodyTxt.setAdapter(bodyAdapter);

        //select transmission type
        transmissionTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                transmissionType = parent.getItemAtPosition(position).toString();
            }
        });

        //select driven type
        drivenTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drivenType = parent.getItemAtPosition(position).toString();
            }
        });

        //select body type
        bodyTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bodyType = parent.getItemAtPosition(position).toString();
            }
        });

        //access to gallery when image clicked
        carIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        AddCar.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        //add car details to database when button clicked and validate if all option is filled
        mBtnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carUri == null){
                    Toast.makeText(AddCar.this, "Please select a picture!", Toast.LENGTH_SHORT).show();
                }
                else if(modelET.getText().toString().isEmpty()){
                    Toast.makeText(AddCar.this, "Please enter model name!", Toast.LENGTH_SHORT).show();
                }
                else if(!isDigit(yearET.getText().toString().trim())){
                    Toast.makeText(AddCar.this, "Please enter year!", Toast.LENGTH_SHORT).show();
                }
                else if(carPlateET.getText().toString().isEmpty()){
                    Toast.makeText(AddCar.this, "Please enter car plate!", Toast.LENGTH_SHORT).show();
                }
                else if(transmissionType.equals("") || drivenType.equals("") || bodyType.equals("")){
                    Toast.makeText(AddCar.this, "Please select options given in Transmission, Driven, and Body!", Toast.LENGTH_SHORT).show();
                }
                else if(!isDigit(priceET.getText().toString().trim())){
                    Toast.makeText(AddCar.this, "Please enter or re-enter price!", Toast.LENGTH_SHORT).show();
                }
                else{
                    upload(carUri);
                    startActivity(new Intent(AddCar.this, MainActivity.class));
                    finish();
                }
            }
        });

        //cancel adding and back to list
        mBtnCancelCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //request permission to access the gallery
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            carUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(carUri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                carIV.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String getFileExtension(Uri mUri){
        ContentResolver img = getContentResolver();
        MimeTypeMap mm = MimeTypeMap.getSingleton();
        return mm.getExtensionFromMimeType(img.getType(mUri));
    }

    //upload the image and brand name to the firebase database by converting the image to a uri
    private void upload(Uri uri){
        StorageReference fileReference = ref.child(System.currentTimeMillis()
                + "." + getFileExtension(uri));
        mUploadTask = fileReference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!urlTask.isSuccessful());
                        Uri downloadUrl = urlTask.getResult();

                        String price = "RM" + priceET.getText().toString().trim() + " / day";

                        InventoryCarModels upload = new InventoryCarModels(downloadUrl.toString(), getIntent().getStringExtra("addBrandName"), modelET.getText().toString().trim(), yearET.getText().toString().trim(), carPlateET.getText().toString().trim(), transmissionType, drivenType, bodyType, price, "Available");
                        root.child(getIntent().getStringExtra("addBrandName")).child(carPlateET.getText().toString().trim()).setValue(upload);

                        Toast.makeText(AddCar.this, "New car added", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCar.this, "Fail to add car!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //check if it is digit
    private boolean isDigit(String txt){
        if(txt.isEmpty() || !txt.matches("[0-9]+")){
            return false;
        }
        else{
            return true;
        }
    }
}