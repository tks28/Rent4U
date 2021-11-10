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

public class AddBrand extends AppCompatActivity {
    private ImageView ivLogo;
    private TextInputEditText mEtLogo;
    Button mBtnAddLogo, mBtnCancelLogo;
    Uri logoUri;
    final int REQUEST_CODE_GALLERY = 999;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Brand");
    private StorageReference ref = FirebaseStorage.getInstance().getReference( "Brand");
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brand);

        ivLogo = findViewById(R.id.logoImageView);
        mEtLogo = findViewById(R.id.etBrand);
        mBtnAddLogo = findViewById(R.id.btnAddLogo);
        mBtnCancelLogo = findViewById(R.id.btnCancelLogo);

        //access the phone gallery to select image
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddBrand.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        //save image and brand name to database when button clicked
        mBtnAddLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if image and input is empty
                if(logoUri == null){
                    Toast.makeText(AddBrand.this, "Please select a picture!", Toast.LENGTH_SHORT).show();
                }
                else if(mEtLogo.getText().toString().isEmpty()){
                    Toast.makeText(AddBrand.this, "Please enter brand name!", Toast.LENGTH_SHORT).show();
                }
                else{
                    //save to firebase and end activity
                    upload(logoUri);
                    startActivity(new Intent(AddBrand.this, MainActivity.class));
                    finish();
                }
            }
        });

        //cancel adding and go back to inventory
        mBtnCancelLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //request permission to use the gallery of the phone
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
            logoUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(logoUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivLogo.setImageBitmap(bitmap);
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
        //store the image in storage
        StorageReference fileReference = ref.child(System.currentTimeMillis()
                + "." + getFileExtension(uri));
        mUploadTask = fileReference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //get the url to the image
                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!urlTask.isSuccessful());
                        Uri downloadUrl = urlTask.getResult();

                        InventoryModels upload = new InventoryModels(mEtLogo.getText().toString().trim(),downloadUrl.toString());

                        String uploadId = root.push().getKey();
                        root.child(uploadId).setValue(upload);

                        Toast.makeText(AddBrand.this, "Brand added!", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddBrand.this, "Fail to insert!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}