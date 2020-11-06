package com.example.pikapainting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {

    private EditText InputName,InputPassword,InputPhoneNumber;
    private Button Createanaccount;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        InputName = (EditText) findViewById(R.id.name);
        InputPassword = (EditText) findViewById(R.id.password);
        InputPhoneNumber = (EditText) findViewById(R.id.phonenumber);
        Createanaccount = (Button) findViewById(R.id.createanaccount);
        loadingBar = new ProgressDialog(this);

        Createanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Createanaccount();
            }
        });


    }

    private void Createanaccount() {

        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter your name...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your password...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please enter your phonenumber...", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Create an Account");
            loadingBar.setMessage("Please wait while we are checking your credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber(name,phone,password);

        }

    }

    private void ValidatephoneNumber(final String name, final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(phone).exists())){

                   HashMap<String , Object> userdataMap = new HashMap<>();
                   userdataMap.put("phone" , phone);
                   userdataMap.put("password" , password);
                   userdataMap.put("name" , name);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity3.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                                startActivity(intent);
                            }
                            else {
                                loadingBar.dismiss();
                                Toast.makeText(MainActivity3.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });




                }
                else{
                    Toast.makeText(MainActivity3.this, "This " + phone + "already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(MainActivity3.this, "Please try again with another phonenumber", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity3.this,MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}