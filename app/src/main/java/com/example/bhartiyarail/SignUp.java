package com.example.bhartiyarail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    ImageView logo;
    TextView welcome,signUpText;
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        logo = findViewById(R.id.logo);
        welcome = findViewById(R.id.Welcome);
        signUpText = findViewById(R.id.SignUpText);
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);


    }


    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpaces = "(?=\\s+$)";
        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        }
        else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        }
        else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();
        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal ="^"+
                "$";
        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }


    public void registerUser(View view) {

        if (!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()){
            return;
        }
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
        reference.child(username).setValue(helperClass);
        Toast.makeText(this,"Your Account has been created successfully!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();
    }
}













