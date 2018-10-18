package com.example.ighub.contact_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText etFirstname, etLastname, etEmail, etPhone, etPassword, etConfrimPass;
    private Button btnRegister;
    private Spinner spGender;
    private String firstName, lastName, email, phone, password, confirmpass, gender, fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstname = findViewById(R.id.etFirstname);
        etLastname = findViewById(R.id.etLastname);
        etEmail = findViewById(R.id.etEmailAddress);
        etPhone = findViewById(R.id.etPhoneNumber);
        etConfrimPass = findViewById(R.id.etConfirmPassword);
        etPassword = findViewById(R.id.etPassword);
        spGender = findViewById(R.id.spGender);
        btnRegister = findViewById(R.id.btnRegister);

        firstName = etFirstname.getText().toString().trim();
        lastName = etLastname.getText().toString().trim();
        fullname = firstName + " " + lastName;
        email = etEmail.getText().toString().toLowerCase().trim();
        phone = etPhone.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirmpass = etConfrimPass.getText().toString().trim();
        gender = spGender.getItemAtPosition(spGender.getSelectedItemPosition()).toString();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });

    }

    private void validation() {
        if (isNetworkConnectionAvailable()) {
            //do something
            if (TextUtils.isEmpty(firstName)) {
                etFirstname.setError("First name cannot be empty");
                return;
            } if (TextUtils.isEmpty(lastName)) {
                etLastname.setError("Last name cannot be empty");
                return;
            } if (isValidEmaillId(email)) {
                etEmail.setError("Please enter a correct email");
                return;
            } if (password.length() < 6) {
                etPassword.setError("Password cannot be less than 6");
                return;
            } if (confirmpass != password) {
                etConfrimPass.setError("Password does not match");
                return;
            } if (!phone.startsWith("0")) {
                etPhone.setError("Invalid phone number");
                return;
            } if (phone.length() != 11 ) {
                etPhone.setError("Phone number cannot be less than 11");
            } else {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Welcome.class));
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please put on your data connection",
                    Toast.LENGTH_LONG).show();
        }
    }

    // added as an instance method to an Activity
    boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }

    boolean isValidEmaillId(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}
