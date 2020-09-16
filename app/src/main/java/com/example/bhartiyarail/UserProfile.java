package com.example.bhartiyarail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.example.bhartiyarail.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName, email, phoneNo, password;
    TextView fullNameLabel, usernameLabel;
    Button btn_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);
        btn_main = findViewById(R.id.btn_main);

        showAllUserData();
        btn_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, UserDashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAllUserData() {

        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_password = intent.getStringExtra("password");

        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        fullName.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phoneNo);
        password.getEditText().setText(user_password);



    }
}