package com.prospect.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicio_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void Profile(int v){
        Intent Profile = new Intent(this, Profile.class);
        startActivity(Profile);
    }
    public void login(int v){
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }

    @Override
    public boolean onContextItemSelected(android.view.MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.action_profile:
                Profile((int)info.id);
                return true;
            case R.id.action_salir:
                login((int)info.id);
                return true;
            default:
                return super.onContextItemSelected((android.view.MenuItem) item);
        }
    }




    //Results Screen
    public void results(View v){
        Intent results = new Intent(this, Results.class);
        startActivity(results);
    }

    //Set Alarma
    public void proAlarm(View  v){
        Intent proAlarm = new Intent(this, Reminder.class);
        startActivity(proAlarm);
    }


    public void Analyze(View v){
        EditText editT = findViewById(R.id.editRate);
        Log.d("Informacion: ",editT.getText().toString());
    }

    public void Pro(View v){
        Intent Pro = new Intent(this, Profile.class);
        startActivity(Pro);
    }

}