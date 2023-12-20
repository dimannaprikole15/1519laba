package com.example.massage_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.massage_salon.data.Users;
import com.example.massage_salon.database.DataBaseManager;

import java.util.Calendar;

public class RegActivity extends AppCompatActivity {
    TextView loginTxt;
    EditText login, pass, fname, sname, patr, birthday;
    Button reg;
    Users user = new Users();
    DataBaseManager dbManager;
    Calendar date=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        loginTxt = findViewById(R.id.ansLogIn);
        login = findViewById(R.id.editLogin);
        pass = findViewById(R.id.editPassword);
        fname = findViewById(R.id.editFirstName);
        sname = findViewById(R.id.editSecondName);
        patr = findViewById(R.id.editPatronymic);
        birthday = findViewById(R.id.editbDate);
        reg  = findViewById(R.id.buttonSave);
        dbManager = new DataBaseManager(this);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login.getText().toString().equals("") ||
                        pass.getText().toString().equals("") ||
                        fname.getText().toString().equals("") ||
                        sname.getText().toString().equals("") ||
                        patr.getText().toString().equals("") ||
                        birthday.getText().toString().equals("")){
                    Toast.makeText(RegActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
                else {
                    try{
                        dbManager.openDb();
                        Users newUser = new Users();
                        newUser.setLogin(login.getText().toString());
                        newUser.setPassword(pass.getText().toString());
                        newUser.setFirstName(fname.getText().toString());
                        newUser.setSecondName(sname.getText().toString());
                        newUser.setPatronymic(patr.getText().toString());
                        newUser.setDateBirthday(birthday.getText().toString());
                        dbManager.addUser(newUser);
                        Log.d("NEW_USER", newUser.getDateBirthday());
                        Intent intent = new Intent(RegActivity.this, InfoActivity.class);
                        intent.putExtra(Users.class.getSimpleName(), newUser);
                        startActivity(intent);
                    }
                    catch (Exception exception){
                        Toast.makeText(RegActivity.this, exception.toString(), Toast.LENGTH_LONG).show();
                        Log.d("QWE", exception.toString());
                    }
                }
            }
        });



        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegActivity.this, AutorizatonActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDb();
    }
}