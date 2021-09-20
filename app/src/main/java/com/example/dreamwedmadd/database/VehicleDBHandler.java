package com.example.dreamwedmadd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dreamwedmadd.models.Vehicle;

public class VehicleDBHandler extends SQLiteOpenHelper {
    //db details
    private static final int VERSION = 3;
    private static final String DB_NAME = "dreamwed" ;
    private static final String TABLE_NAME = "vehicle" ;

    //Column names
    private static final String ID = "id";
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String YEAR = "year";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final  String OWNER = "owner";
    private static final String PHONE = "phone";
    private static final String ADDRESS = "address";


    public VehicleDBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating table
        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" "+
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +BRAND+ " TEXT,"
                +MODEL+ " TEXT,"
                +YEAR+ " TEXT,"
                +PRICE+ " REAL,"
                +DESCRIPTION+ " TEXT,"
                +OWNER+ " TEXT,"
                +PHONE+ " TEXT,"
                +ADDRESS+ " TEXT" +
                ");";

        db.execSQL(TABLE_CREATE_QUERY);
    }

    //for changes of database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;

        //drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);

        //create table again
        onCreate(db);
    }

    //add vehicle to database
    public void addVehicle(Vehicle vehicle){

        //create object to insert data into database
        SQLiteDatabase db = getWritableDatabase();

        //create content values object to bundle dataset before insert
        ContentValues contentValues = new ContentValues();

        contentValues.put(BRAND,vehicle.getBrand());
        contentValues.put(MODEL,vehicle.getModel());
        contentValues.put(YEAR,vehicle.getYear());
        contentValues.put(PRICE,vehicle.getPrice());
        contentValues.put(DESCRIPTION,vehicle.getDescription());
        contentValues.put(OWNER,vehicle.getOwner());
        contentValues.put(PHONE,vehicle.getPhone());
        contentValues.put(ADDRESS,vehicle.getAddress());

        //add data into the table
        db.insert(TABLE_NAME,null,contentValues);

        //close database
        db.close();
    }

    //row counting method
    public int countVehicles(){

        //create object to insert data into database
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        return cursor.getCount();

    }


}
