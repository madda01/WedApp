package com.example.dreamwedmadd.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {

    private static final int VERSION=1; //version
    private static final String DB_NAME="dreamwed"; //database name
    private static final String TABLE_NAME1="user"; //table1 name
    private static final String TABLE_NAME2="costume"; //table2 name

    //COLUMN NAMES FOR USER
    private static final String Userid="userid";
    private static final String Name="name";
    private static final String Email="email";
    private static final String Mobile="mobile";
    private static final String Password="password";

    //COLUMN NAMES FOR COSTUME
    private static final String Cosid="cosid";
    private static final String Title="title";
    private static final String Shop="shop";
    private static final String Price="price";
    private static final String Cphone="cphone";
    private static final String Sizes="sizes";
    private static final String Description="description";

    public DBConnection(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //creating tables
        String TABLE_CREATE_QUERY1 = "CREATE TABLE "+TABLE_NAME1+" "+
                "("
                +Userid+"INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Name+ "TEXT,"
                +Email+ "TEXT,"
                +Mobile+ "TEXT,"
                +Password+ "TEXT" +
                ");";

        String TABLE_CREATE_QUERY2 = "CREATE TABLE "+TABLE_NAME2+" "+
                "("
                +Cosid+"INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Title+ "TEXT,"
                +Shop+ "TEXT,"
                +Price+ "TEXT,"
                +Cphone+ "TEXT,"
                +Sizes+ "TEXT,"
                +Description+ "TEXT" +
                ");";
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY1);
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY1="DROP TABLE IF EXISTS "+ TABLE_NAME1;
        String DROP_TABLE_QUERY2="DROP TABLE IF EXISTS "+ TABLE_NAME2;

        //drop older table if exists
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY1);
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY2);

        //create tables again
        onCreate(sqLiteDatabase);
    }
}
