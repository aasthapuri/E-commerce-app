package com.example.pikapainting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView wings,mandala,flowers,nature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        wings = (ImageView) findViewById(R.id.wings);
        mandala = (ImageView) findViewById(R.id.mandala);
        flowers = (ImageView) findViewById(R.id.flowers);
        nature = (ImageView) findViewById(R.id.nature);

        wings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
                intent.putExtra("Category","wings");
                startActivity(intent);
            }
        });
        mandala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
                intent.putExtra("Category","mandala ");
                startActivity(intent);
            }
        });
        flowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
                intent.putExtra("Category","flowers ");
                startActivity(intent);
            }
        });
        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminActivity.class);
                intent.putExtra("Category","nature ");
                startActivity(intent);
            }
        });
    }
}