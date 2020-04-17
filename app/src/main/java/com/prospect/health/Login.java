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

    private EditText mEdiTextUser;
    private EditText mEditTextPassword;
    private Button mButtonRegister;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    //Variables
    private String name="";
    private String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEdiTextUser = (EditText) findViewById(R.id.editEmail);
        mEditTextPassword = (EditText) findViewById(R.id.editPass);
        mButtonRegister = (Button) findViewById(R.id.buttonSingup);

        /*mButtonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                name = mEdiTextUser.getText().toString();
                password = mEditTextPassword.getText().toString();
                //mDatabase.child("prueba2").setValue(name);

                registerUser();
            }
        });*/
    }

    //Results Screen
    public void Registro(View v) {
        Intent Registro = new Intent(this, Registre.class);
        startActivity(Registro);
    }

    /*private void registerUser(){
        mAuth.createUserWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Map<String, Object> map = new HashMap<>();
                    map.put( "name", name);
                    map.put( "password", password);

                    String id= mAuth.getCurrentUser().getUid();
                    mDatabase.child("Prueba2").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(Login.this, MainActivity.class ));
                                Log.d("myTag", "Su registro ha sido exitoso");
                                Toast.makeText( Login.this, "Su registro ha sido exitoso", Toast.LENGTH_SHORT).show();

                                finish();
                            }else{
                                Log.d("myTag", "EL rgistro fallo");
                                Toast.makeText( Login.this, "EL rgistro fallo", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }else{
                    Toast.makeText( Login.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }*/

}
