package com.example.dreamwedmadd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dreamwedmadd.models.Vehicle;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class VehicleDBHandler extends SQLiteOpenHelper {


    //db details



    private static final int VERSION = 21;


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
                +ADDRESS+ " TEXT," +
                "avatar Blob);";

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
        contentValues.put("avatar",vehicle.getImage());

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

    //retrieving all vehicles to the list
    public List<Vehicle> getAllVehicles(){


        List<Vehicle> vehicles = new ArrayList<>(); //Array list for adding vehicles
        SQLiteDatabase db = getReadableDatabase();  //readable SQLiteDatabase object
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){  //checking db empty or not

            //getting all values from db and add to vehicle object
            do{
                //create new vehicle object
                Vehicle vehicle =new Vehicle();

                vehicle.setId(cursor.getInt(0));
                vehicle.setBrand(cursor.getString(1));
                vehicle.setModel(cursor.getString(2));
                vehicle.setYear(cursor.getString(3));
                vehicle.setPrice(cursor.getDouble(4));
                vehicle.setDescription(cursor.getString(5));
                vehicle.setOwner(cursor.getString(6));
                vehicle.setPhone(cursor.getString(7));
                vehicle.setAddress(cursor.getString(8));
                vehicle.setImage(cursor.getBlob(9));

                vehicles.add(vehicle); //add vehicle objects to the ArrayList
            }while(cursor.moveToNext());

        }
        return vehicles;
    }
    //delete selected vehicle from databaase
    public void deleteVehicle(int id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,ID+" =?",new String[]{String.valueOf(id)});
        db.close();

    }

    public Vehicle getSingleVehicle(int id){

        SQLiteDatabase db = getReadableDatabase();
       Cursor cursor = db.query(TABLE_NAME,new String[]{ID,BRAND,MODEL,YEAR,PRICE,DESCRIPTION,OWNER,PHONE,ADDRESS},ID+"= ?",new String[]{String.valueOf(id)},null,null,null);

        Vehicle vehicle;
        if(cursor!=null){

            cursor.moveToFirst();

            vehicle = new Vehicle(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getDouble(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8)

            );
            return vehicle;
        }
        return null;
    }

    //update vehicle details
    public int updateVehicle(Vehicle vehicle){
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
        int status = db.update(TABLE_NAME,contentValues,ID +" =?", new String[]{String.valueOf(vehicle.getId())});

        //close database
        db.close();

        return status;

    }



}
