package com.example.dreamwedmadd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dreamwedmadd.models.Rating;
import com.example.dreamwedmadd.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class RatingDBHandler extends SQLiteOpenHelper {


    //db details
    private static final int VERSION = 20;
    private static final String DB_NAME = "dreamwed" ;
    private static final String TABLE_NAME = "rating" ;

    //Column names
    private static final String ID = "id";
    private static final String DECORATOR = "decorator";
    private static final String PHOTOGRAPHY = "photography";
    private static final String COSTUME = "costume";
    private static final String VEHICLE = "vehicle";


    public RatingDBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating table
        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" "+
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +DECORATOR+ " REAL,"
                +PHOTOGRAPHY+ " REAL,"
                +COSTUME+ " REAL,"
                +VEHICLE+ " REAL" +
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
    public void addRating(Rating rating){

        //create object to insert data into database
        SQLiteDatabase db = getWritableDatabase();

        //create content values object to bundle dataset before insert
        ContentValues contentValues = new ContentValues();

        contentValues.put(DECORATOR,rating.getDecoRating());
        contentValues.put(PHOTOGRAPHY,rating.getPhotoRating());
        contentValues.put(COSTUME,rating.getCosRating());
        contentValues.put(VEHICLE,rating.getVehRating());

        //add data into the table
        db.insert(TABLE_NAME,null,contentValues);

        //close database
        db.close();
    }

    //get number of ratings for decorater
    public int getRatingCount(){

        //create object to insert data into database
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        return cursor.getCount();

    }

    //get sum of ratings from decorater
//   public float getVehRatingSum(){
//
//        //create object to insert data into database
//        SQLiteDatabase db = getWritableDatabase();
//
//        String query = "SELECT SUM("+VEHICLE+")FROM "+TABLE_NAME;
//
//        Cursor cursor = db.rawQuery(query,null);
//
//        return cursor.getCount();
//
//    }

    public float getDecoRatings(){

        SQLiteDatabase db = getReadableDatabase();  //readable SQLiteDatabase object
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        float deco=0;
        if(cursor.moveToFirst()){  //checking db empty or not

            //getting all values from db and getting sum
            do{
              deco = deco+cursor.getFloat(1);
            }while(cursor.moveToNext());

        }
        return deco;
    }

    public float getPhotoRatings(){

        SQLiteDatabase db = getReadableDatabase();  //readable SQLiteDatabase object
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        float deco=0;
        if(cursor.moveToFirst()){  //checking db empty or not

            //getting all values from db and getting sum
            do{
                deco = deco+cursor.getFloat(2);
            }while(cursor.moveToNext());

        }
        return deco;
    }

    public float getCosRatings(){

        SQLiteDatabase db = getReadableDatabase();  //readable SQLiteDatabase object
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        float deco=0;
        if(cursor.moveToFirst()){  //checking db empty or not

            //getting all values from db and getting sum
            do{
                deco = deco+cursor.getFloat(3);
            }while(cursor.moveToNext());

        }
        return deco;
    }

    public float getVehRatings(){

        SQLiteDatabase db = getReadableDatabase();  //readable SQLiteDatabase object
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        float deco=0;
        if(cursor.moveToFirst()){  //checking db empty or not

            //getting all values from db and getting sum
            do{
                deco = deco+cursor.getFloat(4);
            }while(cursor.moveToNext());

        }
        return deco;
    }


}
