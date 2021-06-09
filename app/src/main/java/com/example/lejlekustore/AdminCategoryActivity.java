package com.example.lejlekustore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView laptops, mobiles, headphones, mouses, keyboards, cameras, watches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        laptops = findViewById(R.id.laptops);
        mobiles = findViewById(R.id.mobiles);
        mouses = findViewById(R.id.mouses);
        headphones = findViewById(R.id.headphones);
        keyboards = findViewById(R.id.keyboards);
        cameras = findViewById(R.id.cameras);
        watches = findViewById(R.id.watches);

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductAcitivity.class);
                intent.putExtra("category", "Laptops");
                startActivity(intent);
            }
        });

        mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductAcitivity.class);
                intent.putExtra("category", "Mobiles");
                startActivity(intent);
            }
        });

        mouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductAcitivity.class);
                intent.putExtra("category", "Mouses");
                startActivity(intent);
            }
        });

        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductAcitivity.class);
                intent.putExtra("category", "Headphones");
                startActivity(intent);
            }
        });

        keyboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductAcitivity.class);
                intent.putExtra("category", "Keyboards");
                startActivity(intent);
            }
        });

        cameras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductAcitivity.class);
                intent.putExtra("category", "Cameras");
                startActivity(intent);
            }
        });

        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductAcitivity.class);
                intent.putExtra("category", "Watches");
                startActivity(intent);
            }
        });
    }
}
