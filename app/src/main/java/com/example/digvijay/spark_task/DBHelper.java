package com.example.digvijay.spark_task;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "task";
    private static final String TABLE_NAME = "users";
    private static final String COLOUMN_EMAIL = "email";
    private static final String COLOUMN_ID = "id";
    private static final String COLOUMN_NAME = "name";
    private static final String COLOUMN_CREDIT = "credit";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String s = "CREATE TABLE " + TABLE_NAME + " ( " + COLOUMN_ID + " INTEGER PRIMARY KEY , " + COLOUMN_NAME + " TEXT , " + COLOUMN_EMAIL + " TEXT ," + COLOUMN_CREDIT + " INTEGER )";
        sqLiteDatabase.execSQL(s);
        String s1 = "CREATE TABLE RECORDS (CREDIT_TO TEXT , CREDIT_FROM TEXT , CREDIT INTEGER )";
        sqLiteDatabase.execSQL(s1);
        fillTable(sqLiteDatabase);
    }

    private void fillTable(SQLiteDatabase sqLiteDatabase) {
        String query = " INSERT INTO " + TABLE_NAME + "  VALUES ( 1 ,'Digvijay', 'Digvijaysingh6662@gamil.com' , 1000 )";
        sqLiteDatabase.execSQL(query);
        query = " INSERT INTO " + TABLE_NAME + "  VALUES ( 2 ,'Yugal', 'yugalkukde6662@gamil.com' , 2000 )";
        sqLiteDatabase.execSQL(query);
        query = " INSERT INTO " + TABLE_NAME + "  VALUES ( 3 ,'Satin', 'satin6662@gamil.com' , 2000 )";
        sqLiteDatabase.execSQL(query);
        query = " INSERT INTO " + TABLE_NAME + "  VALUES ( 4 ,'Akash', 'akash6662@gamil.com' , 2000 )";
        sqLiteDatabase.execSQL(query);
        query = " INSERT INTO " + TABLE_NAME + "  VALUES ( 5 ,'Aman', 'aman6662@gamil.com' , 2000 )";
        sqLiteDatabase.execSQL(query);
        query = " INSERT INTO " + TABLE_NAME + "  VALUES ( 6 ,'Niketan', 'niketan6662@gamil.com' , 2000 )";
        sqLiteDatabase.execSQL(query);
        query = " INSERT INTO " + TABLE_NAME + "  VALUES ( 7 ,'Milan', 'milan6662@gamil.com' , 2000 )";
        sqLiteDatabase.execSQL(query);
        query = " INSERT INTO " + TABLE_NAME + "  VALUES ( 8 ,'Nitesh', 'nitesh6662@gamil.com' , 2000 )";
        sqLiteDatabase.execSQL(query);
        query = " INSERT INTO " + TABLE_NAME + "  VALUES ( 9 ,'Sourabh', 'sourabh6662@gamil.com' , 2000 )";
        sqLiteDatabase.execSQL(query);
        query = " INSERT INTO " + TABLE_NAME + "  VALUES ( 10 ,'Piyush', 'piyush6662@gamil.com' , 2000 )";
        sqLiteDatabase.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> users = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                users.add(c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return users;
    }

    public User getData(String s) {
        User user = new User();
        String query = " SELECT " + COLOUMN_NAME + "," + COLOUMN_EMAIL + "," + COLOUMN_CREDIT + " FROM " + TABLE_NAME + " WHERE " + COLOUMN_NAME + " = '" + s + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            user.setName(c.getString(0));
            user.setEmail(c.getString(1));
            user.setCredits(c.getInt(2));
        }
        return user;
    }

    public void updateUser(String to, String from, int credits) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = " UPDATE " + TABLE_NAME + " SET " + COLOUMN_CREDIT + " = " + COLOUMN_CREDIT + " - " + credits + " WHERE " + COLOUMN_NAME + " = '" + from + "'";
        db.execSQL(query);
        query = " UPDATE " + TABLE_NAME + " SET " + COLOUMN_CREDIT + " = " + COLOUMN_CREDIT + " + " + credits + " WHERE " + COLOUMN_NAME + " = '" + to + "'";
        db.execSQL(query);
        query  =  "INSERT INTO RECORDS VALUES('"+to+"','"+from+"',"+credits+")";
        db.execSQL(query);

    }


    public ArrayList<String> getAllToSend(String from) {
        ArrayList<String> users = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+COLOUMN_NAME+" != '"+from+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                users.add(c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return users;
    }

}
