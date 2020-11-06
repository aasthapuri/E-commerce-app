package com.example.pikapainting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    private String categoryName,description,price,pname;
    private Button addnewproduct;
    private ImageView productimage;
    private EditText productdescription ,productname  ,productprice;
    private static final int GalleryPick =1;
    private Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        categoryName = getIntent().getExtras().get("Category").toString();
        addnewproduct = (Button) findViewById(R.id.addnewproduct);
        productimage = (ImageView) findViewById(R.id.selectproduct);
        productdescription = (EditText) findViewById(R.id.product_description);
        productname = (EditText) findViewById(R.id.product_name);
        productprice = (EditText) findViewById(R.id.product_price);

        productimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });
        addnewproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProductData();
            }
        });
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GalleryPick && resultCode==RESULT_OK && data!=null){
            ImageUri = data.getData();
            productimage.setImageURI(ImageUri);


        }
    }

    private void ValidateProductData()
    {
        description = productdescription.getText().toString();
        pname = productname.getText().toString();
        price = productprice.getText().toString();

    }
}