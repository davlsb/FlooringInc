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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class employeeRegister extends AppCompatActivity {
    private TextView userButton;
    private EditText employeeID;
    private EditText employeePIN;
    private EditText storeID;
    private Button registerButton;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //init
        employeeID = (EditText) findViewById(R.id.floorName);
        employeePIN = (EditText) findViewById(R.id.categoryDropdown);
        registerButton = (Button) findViewById(R.id.registerButton);
        storeID = (EditText) findViewById(R.id.type);

        firebaseAuth = FirebaseAuth.getInstance();

        TextView userLogin = (TextView) findViewById(R.id.userClickHere);
        userLogin.setPaintFlags(userLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        userButton = (TextView) findViewById(R.id.userClickHere);
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserLogin();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputData();
            }
        });

    }

    private String email;
    private String password;
    private String store;

    public void inputData(){
            email = employeeID.getText().toString().trim();
            password = employeePIN.getText().toString().trim();
            store = storeID.getText().toString().trim();

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

            createAccount();
    }

    private void createAccount() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Registering");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();
                        saveFirebaseData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(employeeRegister.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveFirebaseData() {
        progressDialog.setMessage("Saving Account Info, Please Wait");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", "" + firebaseAuth.getUid());
        hashMap.put("email", "" + email);
        hashMap.put("storeID", "" + store);

        //save to DB
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid()).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        startActivity(new Intent(employeeRegister.this, employeeLogin.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed updating db
                        progressDialog.dismiss();
                        startActivity(new Intent(employeeRegister.this, employeeRegister.class));
                        finish();
                    }
                });
    }

    public void openUserLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}