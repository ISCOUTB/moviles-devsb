package com.prospect.health;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonLogin;

    public static FirebaseAuth Logintrue;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    //Variables
    private String email="";
    private String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        Logintrue = mAuth;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEditTextEmail = (EditText) findViewById(R.id.editEmail);
        mEditTextPassword = (EditText) findViewById(R.id.editPass);
        mButtonLogin = (Button) findViewById(R.id.buttonSingup);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mProgress.message
                email=mEditTextEmail.getText().toString();
                password=mEditTextPassword.getText().toString();
                if (!email.isEmpty()&&!password.isEmpty()){
                    loginUser();
                }else{
                    Toast.makeText(Login.this, "Completa los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public static String id ="";
    private void loginUser(){

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this,MainActivity.class));
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this, "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
                }
            }
        });
        id= Logintrue.getCurrentUser().getUid();
        Log.d("hola id", id);
    }

    public void forgetPassword(View v) {
        Intent term = new Intent(this, Reminder.class);
        startActivity(term);
    }

    //Results Screen
    public void Registro(View v) {
        Intent Registro = new Intent(this, Registre.class);
        startActivity(Registro);
    }


}
