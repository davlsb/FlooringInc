package edu.qc.seclass.fim;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class employeeLogin extends AppCompatActivity {
    private TextView userButton;
    private TextView regButton;
    private Button loginButton;
    private EditText employeeID;
    private EditText employeePIN;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        employeeID = (EditText) findViewById(R.id.floorName);
        employeePIN = (EditText) findViewById(R.id.categoryDropdown);
        loginButton = (Button) findViewById(R.id.registerButton);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);

        TextView userLogin = (TextView) findViewById(R.id.userClickHere);
        userLogin.setPaintFlags(userLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        userButton = (TextView) findViewById(R.id.userClickHere);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategories();
            }
        });

        TextView employeeReg = (TextView) findViewById(R.id.registerClickHere);
        employeeReg.setPaintFlags(employeeReg.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        regButton = (TextView) findViewById(R.id.registerClickHere);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private String email;
    private String password;

    private void loginUser() {
        email = employeeID.getText().toString().trim();
        password = employeePIN.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();
                        startActivity(new Intent(employeeLogin.this, categoriesAndSearch.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed
                        progressDialog.dismiss();
                        Toast.makeText(employeeLogin.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void openCategories(){
        Intent intent = new Intent(this, categoriesAndSearch.class);
        startActivity(intent);
    }

    public void openRegister(){
        Intent intent = new Intent(this, employeeRegister.class);
        startActivity(intent);
    }

}