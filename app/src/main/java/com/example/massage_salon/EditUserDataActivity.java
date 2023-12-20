package com.example.massage_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.massage_salon.data.Users;
import com.example.massage_salon.database.DataBaseManager;

import java.util.Objects;

public class EditUserDataActivity extends AppCompatActivity {
    EditText login, password, firstName, secondName, patronymic, dateBirthday;
    Button save;
    DataBaseManager dbManager;
    Users user = new Users();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_data);
        dbManager = new DataBaseManager(this);
        dbManager.openDb();
        login = findViewById(R.id.editLogin);
        password = findViewById(R.id.editPassword);
        firstName = findViewById(R.id.editFirstName);
        secondName = findViewById(R.id.editSecondName);
        patronymic = findViewById(R.id.editPatronymic);
        dateBirthday = findViewById(R.id.editBirthdayDate);
        save = findViewById(R.id.buttonSave);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null){
            user = (Users) arguments.getSerializable(Users.class.getSimpleName());
            login.setText(Objects.requireNonNull(user).getLogin());
            password.setText(Objects.requireNonNull(user).getPassword());
            firstName.setText(Objects.requireNonNull(user).getFirstName());
            secondName.setText(Objects.requireNonNull(user).getSecondName());
            patronymic.setText(Objects.requireNonNull(user).getPatronymic());
            dateBirthday.setText(Objects.requireNonNull(user).getDateBirthday());
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setId(user.getId());
                user.setLogin(user.getLogin().toString());
                user.setPassword(password.getText().toString());
                user.setFirstName(firstName.getText().toString());
                user.setSecondName(secondName.getText().toString());
                user.setPatronymic(patronymic.getText().toString());
                user.setDateBirthday(dateBirthday.getText().toString());
                Toast.makeText(EditUserDataActivity.this, "Данные обновлены",
                        Toast.LENGTH_SHORT).show();
                dbManager.updateUser(user);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDb();
    }
}