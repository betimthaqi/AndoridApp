package com.example.lejlekustore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AdminAddNewProductAcitivity extends AppCompatActivity {

    private String CategoryName, Description, Price, Pname, saveCurrentDate, saveCurrentTime;
    private Button AddNewProductBtn;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription, InputProductPrice;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private FirebaseFirestore fStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product_acitivity);

        CategoryName = getIntent().getExtras().get("category").toString();
        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Product Image");
       // ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        fStore = FirebaseFirestore.getInstance();

        AddNewProductBtn = findViewById(R.id.add_new_product_btn);
        InputProductImage = findViewById(R.id.select_product_id);
        InputProductName = findViewById(R.id.product_name);
        InputProductDescription = findViewById(R.id.product_description);
        InputProductPrice = findViewById(R.id.product_price);

        //loadingBar = new ProgressDialog(this);



        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        AddNewProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });

    }


    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }

    }

    private void ValidateProductData(){
        Description = InputProductDescription.getText().toString();
        Price = InputProductPrice.getText().toString();
        Pname = InputProductName.getText().toString();

        if(ImageUri == null){
            Toast.makeText(this, "Product image is required...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Description)){
            Toast.makeText(this, "Please write product description...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Price)){
            Toast.makeText(this, "Please write product price...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Pname)){
            Toast.makeText(this, "Please write product name ...", Toast.LENGTH_SHORT).show();
        }
        else{
            StoreProductInformation();
        }


    }

    private void StoreProductInformation() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(AdminAddNewProductAcitivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                //loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AdminAddNewProductAcitivity.this, "Product image uploaded successfully", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if (!task.isSuccessful()){
                            throw task.getException();

                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if(task.isSuccessful()){

                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(AdminAddNewProductAcitivity.this, "got the product image successfully", Toast.LENGTH_SHORT).show();

                        }

                        SaveProductInfoToDatabase();
                    }
                });
            }
        });


    }

    private void SaveProductInfoToDatabase() {


        DocumentReference df = fStore.collection("Products").document(productRandomKey);
        Map<String, Object> productMap = new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", Description);
        productMap.put("image", downloadImageUrl);
        productMap.put("category", CategoryName);
        productMap.put("price", Price);
        productMap.put("pname", Pname);
        df.set(productMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(AdminAddNewProductAcitivity.this, AdminCategoryActivity.class);
                startActivity(intent);

                Toast.makeText(AdminAddNewProductAcitivity.this, "Product is added successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminAddNewProductAcitivity.this, "Error: Find by yourself", Toast.LENGTH_SHORT).show();
            }
        });

        finish();



    }

}