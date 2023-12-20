package com.example.massage_salon;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.massage_salon.adapters.OrdersAdapter;
import com.example.massage_salon.adapters.ServicesAdapter;
import com.example.massage_salon.data.Orders;
import com.example.massage_salon.data.Services;
import com.example.massage_salon.data.Users;
import com.example.massage_salon.database.DataBaseManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InfoActivity extends AppCompatActivity {
    Button allServices, editUserData;
    TextView tvHello;
    RecyclerView recyclerView;
    DataBaseManager dbManager;
    Users user = new Users();
    List<Orders> orders;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        dbManager = new DataBaseManager(this);
        dbManager.openDb();
        allServices = findViewById(R.id.showAllServices);
        editUserData = findViewById(R.id.btnEditUserData);
        recyclerView = findViewById(R.id.recyclerView);
        tvHello = findViewById(R.id.txtHelloString);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null){
            user = (Users) arguments.getSerializable(Users.class.getSimpleName());
            tvHello.setText("Здравствуйте, " + Objects.requireNonNull(user).getFirstName());
            Log.d("login", user.getLogin());
        }
        Users recordUser = user;
        allServices.setOnClickListener(v -> { // список всех услуг
            Intent intent = new Intent(InfoActivity.this, AllServices.class);
            intent.putExtra(Users.class.getSimpleName(), recordUser);
            startActivity(intent);
        });

        Users updateUser = user;
        editUserData.setOnClickListener(new View.OnClickListener() { // редактировать данные пользователя
            @Override
            public void onClick(View v) {
                Intent editUserDataIntent = new Intent(InfoActivity.this, EditUserDataActivity.class);
                editUserDataIntent.putExtra(Users.class.getSimpleName(), updateUser);
                startActivity(editUserDataIntent);
            }
        });

        dbManager = new DataBaseManager(this);
        dbManager.openDb();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        orders = dbManager.getOrders();
        for (int i = 0; i < orders.size(); i++)
            if (orders.get(i).getUserId() != user.getId())
                orders.remove(i);
        OrdersAdapter ordersAdapter = new OrdersAdapter(orders, this);
        recyclerView.setAdapter(ordersAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDb();
    }
}