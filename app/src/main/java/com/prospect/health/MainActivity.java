package com.prospect.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextRate;
    private EditText mEditTextPresure;
    private EditText mEditTextSaturation;
    private EditText mEditTextTemperature;
    private EditText mEditTextSugar;
    private Button mButtonAnalyze;

    FirebaseDatabase database;
    DatabaseReference mDatabase;

    //Variables
    public static String rate="";
    public static String presure="";
    public static String saturation="";
    public static String temperature="";
    public static String sugar="";

    String id = Login.id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar date = new GregorianCalendar();
        int año = date.get(Calendar.YEAR);
        int mes = date.get(Calendar.MONTH);
        int dia = date.get(Calendar.DAY_OF_MONTH);
        String date1 = dia+"-"+(mes+1)+"-"+año;

        mEditTextRate = (EditText) findViewById(R.id.editRate);
        mEditTextPresure = (EditText) findViewById(R.id.editPressure);
        mEditTextSaturation = (EditText) findViewById(R.id.editSaturation);
        mEditTextTemperature = (EditText) findViewById(R.id.editTemperature);
        mEditTextSugar = (EditText) findViewById(R.id.editSugar);
        mButtonAnalyze = (Button) findViewById(R.id.buttonAnalyze);

        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("Usuario/"+id+"/datosVitales/"+date1);

        mButtonAnalyze.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                rate = mEditTextRate.getText().toString();
                presure = mEditTextPresure.getText().toString();
                saturation = mEditTextSaturation.getText().toString();
                temperature = mEditTextTemperature.getText().toString();
                sugar = mEditTextSugar.getText().toString();

                saveData();
            }
        });


    }

    //Almacenar datos
    private void saveData(){
        Map<String, Object> map = new HashMap<>();
        map.put( "Heart Rate", rate);
        map.put("Blood Presure", presure);
        map.put( "Oxigen Saturation", saturation);
        map.put( "Temperature", temperature);
        map.put( "Sugar", sugar);

        mDatabase.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task2) {
                if(task2.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, Results.class ));
                   // Log.d("myTag", "Su registro ha sido exitoso");
                    Toast.makeText( MainActivity.this, "Analizando datos!", Toast.LENGTH_SHORT).show();

                    finish();
                }/*else{
                    Log.d("myTag", "EL rgistro fallo");
                    Toast.makeText( MainActivity.this, "EL registro fallo", Toast.LENGTH_SHORT).show();

                }*/
            }
        });
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicio_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_profile:
                startActivity(new Intent(MainActivity.this, Profile.class ));
                return true;
            case R.id.action_salir:
                Login.Logintrue.signOut();
                startActivity(new Intent(MainActivity.this, Login.class ));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Set Alarma
    public void proAlarm(View  v){
        Intent proAlarm = new Intent(this, Reminder.class);
        startActivity(proAlarm);
    }

}