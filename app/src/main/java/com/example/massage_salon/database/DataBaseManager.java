package com.example.massage_salon.database;

import static com.example.massage_salon.database.DataBaseConstant.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.massage_salon.data.Orders;
import com.example.massage_salon.data.Services;
import com.example.massage_salon.data.Users;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private Context context;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context){
        this.context = context;
        dbHelper = new DataBaseHelper(this.context);
    }
    public void openDb(){
        db = dbHelper.getWritableDatabase();
    }

    public void closeDb(){
        db.close();
    }

    @SuppressLint("Range")
    public Users checkUser(String login, String pass) {
        Users user = new Users();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE_NAME + " WHERE " + USER_LOGIN + " =  + '"
                + login +"'" +  " AND " + USER_PASSWORD + " =  + '" + pass +"'", null);
        Log.d("QWECursor", cursor.moveToFirst() + "");
        if (cursor.moveToFirst()) {
            Log.d("QWEDBM", cursor.getString(cursor.getColumnIndex(USER_LOGIN)) + "");
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ID))));
            user.setLogin(cursor.getString(cursor.getColumnIndex(USER_LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
            user.setSecondName(cursor.getString(cursor.getColumnIndex(USER_SECOND_NAME)));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_FIRST_NAME)));
            user.setPatronymic(cursor.getString(cursor.getColumnIndex(USER_PATRONYMIC)));
        }
        cursor.close();
        return user;
    }

    @SuppressLint("Range")
    public List<Users> getUsers(){
        List<Users> users = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                "" + USERS_TABLE_NAME, null);
        while (cursor.moveToNext()){
            Users user = new Users();
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ID))));
            user.setLogin(cursor.getString(cursor.getColumnIndex(USER_LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_FIRST_NAME)));
            user.setSecondName(cursor.getString(cursor.getColumnIndex(USER_SECOND_NAME)));
            user.setPatronymic(cursor.getString(cursor.getColumnIndex(USER_PATRONYMIC)));
            user.setDateBirthday(cursor.getString(cursor.getColumnIndex(USER_DATE_BIRTHDAY)));
            users.add(user);
        }
        return users;
    }

    @SuppressLint("Range")
    public Services getService(int serviceId){
        Services service = new Services();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SERVICES_TABLE_NAME + " WHERE " + SERVICE_ID + " = " + "\"" + serviceId + "\"", null);
        if (cursor.moveToFirst()){
            service.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_ID))));
            service.setName(cursor.getString(cursor.getColumnIndex(SERVICE_NAME)));
            service.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_PRICE))));
            service.setDescription(cursor.getString(cursor.getColumnIndex(SERVICE_DESCRIPTION)));
        }
        cursor.close();
        return service;
    }

    @SuppressLint("Range")
    public  List<Services> getServices(){
        List<Services> services = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                SERVICES_TABLE_NAME, null);
        while (cursor.moveToNext()){
            Services service = new Services();
            service.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_ID))));
            service.setName(cursor.getString(cursor.getColumnIndex(SERVICE_NAME)));
            service.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_PRICE))));
            service.setDescription(cursor.getString(cursor.getColumnIndex(SERVICE_DESCRIPTION)));
            services.add(service);
        }
        cursor.close();
        return services;
    }

    @SuppressLint("Range")
    public List<Orders> getOrders(){
        List<Orders> orders = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                "" + ORDERS_TABLE_NAME, null);
        while(cursor.moveToNext()){
            Orders order = new Orders();
            order.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ORDER_ID))));
            order.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_USER))));
            order.setServiceId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_SERVICE))));
            order.setDateRecord(cursor.getString(cursor.getColumnIndex(DATE_RECORD)));
            orders.add(order);
        }
        return orders;
    }

    public void addOrder(Orders order){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseConstant.ID_USER, order.getUserId());
        cv.put(DataBaseConstant.ID_SERVICE, order.getServiceId());
        cv.put(DataBaseConstant.DATE_RECORD, order.getDateRecord());
        db.insert(ORDERS_TABLE_NAME, null, cv);
    }

    public void addUser(Users user){
        ContentValues cv = new ContentValues();
        cv.put(USER_LOGIN, user.getLogin());
        cv.put(USER_PASSWORD, user.getPassword());
        cv.put(USER_FIRST_NAME, user.getFirstName());
        cv.put(USER_SECOND_NAME, user.getSecondName());
        cv.put(USER_PATRONYMIC, user.getPatronymic());
        cv.put(USER_DATE_BIRTHDAY, user.getDateBirthday());
        db.insert(USERS_TABLE_NAME, null, cv);
    }

    public void updateUser(Users user){
        ContentValues cv = new ContentValues();
        cv.put(USER_PASSWORD, user.getPassword());
        cv.put(USER_FIRST_NAME, user.getFirstName());
        cv.put(USER_SECOND_NAME, user.getSecondName());
        cv.put(USER_PATRONYMIC, user.getPatronymic());
        cv.put(USER_DATE_BIRTHDAY, user.getDateBirthday());
        db.update(USERS_TABLE_NAME, cv, USER_ID + " = " + user.getId(), null);
    }

    public void deleteUser(Users user){
        db.delete(DataBaseConstant.USERS_TABLE_NAME, DataBaseConstant.USER_ID + " = " + user.getId(), null);
    }

    public void addService(Services service){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseConstant.SERVICE_NAME, service.getName());
        cv.put(DataBaseConstant.SERVICE_PRICE, service.getPrice());
        cv.put(DataBaseConstant.SERVICE_DESCRIPTION, service.getDescription());
        db.insert(SERVICES_TABLE_NAME,null,cv);
    }

    public void deleteService(Services service){
        db.delete(DataBaseConstant.SERVICES_TABLE_NAME, DataBaseConstant.SERVICE_ID + " = " + service.getId(), null);
    }

    public void deleteOrder(Orders order){
        db.delete(DataBaseConstant.ORDERS_TABLE_NAME, DataBaseConstant.ORDER_ID + " = " + order.getId(), null);
    }
}