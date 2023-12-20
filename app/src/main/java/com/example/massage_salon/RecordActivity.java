package com.example.massage_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.massage_salon.data.Orders;
import com.example.massage_salon.data.Services;
import com.example.massage_salon.data.Users;
import com.example.massage_salon.database.DataBaseManager;

public class RecordActivity extends AppCompatActivity {

    TextView serviceName;
    EditText dateRecord;
    Button record;
    DataBaseManager dbManager;
    Orders order = new Orders();
    Users user = new Users();
    Services service = new Services();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        serviceName = findViewById(R.id.nameService);
        dbManager = new DataBaseManager(this);
        dateRecord = findViewById(R.id.editDateRecord);
        record = findViewById(R.id.buttonRecord);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int idService = intent.getIntExtra("id", 0);
        int userID = intent.getIntExtra("userId", 0);
        serviceName.setText(name);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.openDb();
                Orders newOrder = new Orders();
                newOrder.setServiceId(idService);
                newOrder.setDateRecord(dateRecord.getText().toString());
                newOrder.setUserId(userID);
                dbManager.addOrder(newOrder);
                Toast.makeText(RecordActivity.this, newOrder.getId() + " " + newOrder.getServiceId() + " " + newOrder.getUserId(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDb();
    }
}