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
import android.widget.TextView;
import android.widget.Toast;
import com.example.pikapainting.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    private Button LoginButton;
    private EditText InputPhoneNumber,InputPassword;
    private ProgressDialog loadingBar;
    private String parentdbname = "Users";
    private TextView adminlink,notadminlink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        InputPassword = (EditText) findViewById(R.id.password);
        InputPhoneNumber = (EditText) findViewById(R.id.phonenumber);
        adminlink = (TextView) findViewById(R.id.adminpanellink);
        notadminlink = (TextView) findViewById(R.id.not_admin_panel_link);
        LoginButton = (Button) findViewById(R.id.loginbtn);
        loadingBar = new ProgressDialog(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });
        adminlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login Admin");
                adminlink.setVisibility(View.INVISIBLE);
                notadminlink.setVisibility(View.VISIBLE);
                parentdbname = "Admins";
            }
        });
        notadminlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login");
                adminlink.setVisibility(View.VISIBLE);
                notadminlink.setVisibility(View.INVISIBLE);
                parentdbname = "Users";
            }
        });




    }

    private void LoginUser() {

        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your password...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter your phonenumber...", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Create an Account");
            loadingBar.setMessage("Please wait while we are checking your credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phone,password);
        }


    }

    private void AllowAccessToAccount(final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentdbname).child(phone).exists()){

                    Users usersData = dataSnapshot.child(parentdbname).child(phone).getValue(Users.class);


                    if (usersData.getphone().equals(phone))
                    {
                        if (usersData.getpassword().equals(password))
                        {
                          if(parentdbname.equals("Admins")){
                              Toast.makeText(MainActivity2.this, "Welcome Aastha:)", Toast.LENGTH_SHORT).show();
                              loadingBar.dismiss();
                              Intent intent = new Intent(MainActivity2.this,AdminCategoryActivity .class);
                              startActivity(intent);
                          }
                          else if(parentdbname.equals("Users")){
                              Toast.makeText(MainActivity2.this, "You have logged in successfully", Toast.LENGTH_SHORT).show();
                              loadingBar.dismiss();
                              Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                              startActivity(intent);

                          }

                        }
                        else{
                            Toast.makeText(MainActivity2.this, "Account with this" + phone + "does not exist", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}