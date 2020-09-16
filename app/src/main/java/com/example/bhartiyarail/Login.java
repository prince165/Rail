package com.example.bhartiyarail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button callSignUp, login_btn,forget_password;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username,password;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        image=findViewById(R.id.logo_image);

        logoText =findViewById(R.id.logo_name);
        sloganText=findViewById(R.id.slogan_name);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        forget_password=findViewById(R.id.ForgetPassword);
        login_btn=findViewById(R.id.SignIn);
        callSignUp = findViewById(R.id.signup_screen);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);

            }
        });
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Course");
                reference.setValue("Hello World");
            }
        });
    }

    private Boolean validateUsername() {
        String value = username.getEditText().getText().toString();
        if (value.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String value = password.getEditText().getText().toString();

        if (value.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view){

        if(!validateUsername() | !validatePassword()){
            return;
        }
        else{
            isUser();
        }
    }

    private void isUser() {

        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)) {

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);

                    }
                    else{
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else {
                    username.setError("No such user exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}