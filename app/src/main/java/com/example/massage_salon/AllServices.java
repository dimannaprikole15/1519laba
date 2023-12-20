package com.example.massage_salon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.massage_salon.adapters.ServicesAdapter;
import com.example.massage_salon.data.Services;
import com.example.massage_salon.data.Users;
import com.example.massage_salon.database.DataBaseHelper;
import com.example.massage_salon.database.DataBaseManager;

import java.util.ArrayList;
import java.util.List;

public class AllServices extends AppCompatActivity {
    TextView tv;
    RecyclerView recyclerView;
    List<Services> services;
    DataBaseManager dbManager;
    Users recordUser = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_services);
        Toast.makeText(AllServices.this, "Для выбора услуги для записи, нажми на её название", Toast.LENGTH_LONG).show();
        tv = findViewById(R.id.test);
        dbManager = new DataBaseManager(this);
        dbManager.openDb();
        recyclerView = findViewById(R.id.recView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        services = dbManager.getServices();
        Bundle arguments = getIntent().getExtras();
        if (arguments != null){
            recordUser = (Users) arguments.getSerializable(Users.class.getSimpleName());
            tv.setText(recordUser.getFirstName());
        }
        ServicesAdapter servicesAdapter = new ServicesAdapter(services, recordUser, this);
        if (services.size() > 0){
            recyclerView.setAdapter(servicesAdapter);
        }
    }
}

