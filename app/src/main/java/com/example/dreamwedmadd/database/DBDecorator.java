package com.example.dreamwedmadd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dreamwedmadd.models.Decorator;

public class DBDecorator extends SQLiteOpenHelper {

    private static final int VERSION=2;
    private static final String DB_NAME="dreamwed";
    private static final String TABLE_NAME="decorator";
    private static final String ID="id";
    private static final String FNAME="fname";
    private static final String LNAME="lname";
    private static final String CNAME ="cname";
    private static final String ADDRESS="address";
    private static final String EMAIL ="email";
    private static final String DESCRIPTION ="description";
    private static final String MOBILE ="mobile";
    private static final String PRICE="price";





    public DBDecorator(Context context) {
        super(context,DB_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE "+TABLE_NAME+" "+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+FNAME+
                " TEXT,"+LNAME+" TEXT,"+EMAIL+" TEXT,"+MOBILE+" TEXT,"+CNAME+" TEXT,"+ADDRESS+" TEXT,"+DESCRIPTION+" TEXT,"+PRICE+" REAL"+");";

            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_TABLE_QUERY="DROP TABLE IF EXISTS "+ TABLE_NAME;
        //drop older table if exist
        db.execSQL(DROP_TABLE_QUERY);
        //Create tables again
        onCreate(db);
    }


    public void addDeco(Decorator decorator){

        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FNAME,decorator.getfName());
        contentValues.put(LNAME,decorator.getlName());
        contentValues.put(EMAIL,decorator.getEmail());
        contentValues.put(MOBILE,decorator.getMobile());
        contentValues.put(CNAME,decorator.getcName());
        contentValues.put(ADDRESS,decorator.getAddress());
        contentValues.put(PRICE,decorator.getPrice());
        contentValues.put(DESCRIPTION,decorator.getDescription());

        db.insert(TABLE_NAME,null,contentValues);
        db.close();

    }








}
