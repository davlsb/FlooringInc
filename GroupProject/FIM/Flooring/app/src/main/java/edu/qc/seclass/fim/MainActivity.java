package edu.qc.seclass.fim;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private TextView buttonEmployee;
    private Button buttonUser;

    //THE NEW ONE
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        TextView employeeLogin = (TextView) findViewById(R.id.employeeClickHere);
        employeeLogin.setPaintFlags(employeeLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        buttonUser = (Button) findViewById(R.id.userButton);
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserCategories();
            }
        });
        buttonEmployee = (TextView) findViewById(R.id.employeeClickHere);
        buttonEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeLogin();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    if(!user.isAnonymous()) {
                        Intent intent = new Intent(MainActivity.this, categoriesAndSearch.class);
                        startActivity(intent);
                    }
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };


    }

    // Add Auth state listener in onStart method.
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // stop listener in onStop
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void openUserCategories(){

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "Auth : " +task.isSuccessful());                if (!task.isSuccessful()) {
                            Log.w(TAG, "Failed : ", task.getException());
                            Toast.makeText(MainActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(MainActivity.this, categoriesAndSearch.class);
                            startActivity(intent);
                        }

                    }
                });

    }

    public void openEmployeeLogin(){
        Intent intent = new Intent(this, employeeLogin.class);
        startActivity(intent);
    }
}