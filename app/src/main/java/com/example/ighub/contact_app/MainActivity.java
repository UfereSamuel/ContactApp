package com.example.ighub.contact_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
//        gender = spGender.getSelectedItem(spGender.getSelectedItemId());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });

    }

    private void validation() {
        if (TextUtils.isEmpty(fullname)) {
            etFirstname.setError("enter first name");
            etLastname.setError("enter last name");
        } else if (TextUtils.isEmpty(email)) {
            etEmail.setError("enter your email");
        }
    }
}
