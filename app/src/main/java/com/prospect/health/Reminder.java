package com.prospect.health;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Reminder extends AppCompatActivity implements View.OnClickListener {
    Button btDate, btTime, buttonMas;
    EditText edDate, edTime, editName;
    private int day, month, year, time, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        btDate=(Button)findViewById(R.id.buttonDate);
        btTime=(Button)findViewById(R.id.buttonTime);
        buttonMas=(Button)findViewById(R.id.buttonMas);
        edDate=(EditText)findViewById(R.id.editDate);
        edTime=(EditText)findViewById(R.id.editTime);
        editName=(EditText)findViewById(R.id.editNameMedicine);
        btDate.setOnClickListener(this);
        btTime.setOnClickListener(this);
        buttonMas.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v==btDate){
            final Calendar calendar= Calendar.getInstance();
            day=calendar.get(Calendar.DAY_OF_MONTH);
            month=calendar.get(Calendar.MONTH);
            year=calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    edDate.setText(dayOfMonth+"/"+ (month+1)+"/"+year);
                }
            }, day,month,year);
            datePickerDialog.show();
        }
        if(v==btTime){
            final Calendar calendar = Calendar.getInstance();
            time=calendar.get(Calendar.HOUR_OF_DAY);
            minutes=calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    edTime.setText(hourOfDay+":"+minute);
                }
            }, time, minutes, false);
            timePickerDialog.show();
        }
        if(v==buttonMas){
            AlertDialog.Builder builder = new AlertDialog.Builder(Reminder.this);
            builder.setIcon(R.mipmap.ic_launcher).
                    setTitle("Alarm Activated").
                    setMessage("Name of the Medicine: "+editName.getText().toString()+"\nDate: " + edDate.getText().toString()+ "\nTime: " + edTime.getText().toString());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            editName.setText("");
            edDate.setText("");
            edTime.setText("");
        }
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicio_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void returnn(View v){
        Intent returnn = new Intent(this, MainActivity.class);
        startActivity(returnn);
    }

    //debe redireccional al pantallazo principal que sera el layout
    //con estadistica de progreso de el usuario e informacion basica de el
    public void continuee(View v){
        Intent returnn = new Intent(this, MainActivity.class);
        startActivity(returnn);
    }

}