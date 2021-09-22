package com.example.dreamwedmadd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dreamwedmadd.models.Photographermodel;

import java.util.ArrayList;
import java.util.List;

public class photoDbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 15;
    private static final String DB_NAME = "dreamwed" ;
    private static final String TABLE_NAME = "photographyadmin" ;

    //Column names
     private static final String ID = "id";
     private static final String FIRSTNAME = "firstname";
     private static final String LASTNAME = "lastname";
     private static final String EMAIL = "email";
     private static final String MOBILENUMBER = "mobilenumber";
     private static final String COMPANYNAME = "companyname";
    private static final  String ADDRESS = "address";
     private static final String PRICE = "price";
     private static final String DESCRIPTION = "description";

    public photoDbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" "+
                    "("
                    +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +FIRSTNAME+ " TEXT,"
                    +LASTNAME+ " TEXT,"
                    +EMAIL+ " TEXT,"
                    +MOBILENUMBER+ " TEXT,"
                    +COMPANYNAME+ " TEXT,"
                    +ADDRESS+ " TEXT,"
                    +PRICE+ " REAL,"
                    +DESCRIPTION+ " TEXT" +
                    ");";

            db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;

        //drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);

        //create table again
        onCreate(db);
    }

    public void addPhotographer(Photographermodel phto){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase() ;

        ContentValues contentValues = new ContentValues();

                contentValues.put(FIRSTNAME,phto.getFnamee());
                contentValues.put(LASTNAME,phto.getLnamee());
                contentValues.put(EMAIL,phto.getEmaile());
                contentValues.put(MOBILENUMBER,phto.getPhonee());
                contentValues.put(COMPANYNAME,phto.getComanpnynamee());
                contentValues.put(ADDRESS,phto.getAddresse());
                contentValues.put(PRICE,phto.getPricee());
                contentValues.put(DESCRIPTION,phto.getDescriptione());

                //save to table

                sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
                sqLiteDatabase.close();


    }

    public int countPhotographer(){
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //get all photographers

    public List<Photographermodel> getAllPhotographers(){

        List<Photographermodel> photogra = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Photographermodel photodb = new Photographermodel();

                photodb.setId(cursor.getInt(0));
                photodb.setFnamee(cursor.getString(1));
                photodb.setLnamee(cursor.getString(2));
                photodb.setEmaile(cursor.getString(3));
                photodb.setPhonee(cursor.getString(4));
                photodb.setComanpnynamee(cursor.getString(5));
                photodb.setAddresse(cursor.getString(6));
                photodb.setPricee(cursor.getDouble(7));
                photodb.setDescriptione(cursor.getString(8));




                photogra.add(photodb);
            }while (cursor.moveToNext());
        }
        return  photogra;
    }

    public Photographermodel getSinglePhotographer(int id){

        SQLiteDatabase db =getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,FIRSTNAME,LASTNAME,EMAIL,MOBILENUMBER,COMPANYNAME,
        ADDRESS,PRICE,DESCRIPTION},ID + "= ?",new String[]{String.valueOf(id)},null,null,null);

        Photographermodel photoobj ;
        if(cursor != null){
            cursor.moveToFirst();
            photoobj = new Photographermodel (
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getDouble(7),
                    cursor.getString(8)
            );
            return  photoobj;
        }
        return  null;

    }

    public int updatePhotographer(Photographermodel photogrp){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(FIRSTNAME,photogrp.getFnamee());
        contentValues.put(LASTNAME,photogrp.getLnamee());
        contentValues.put(EMAIL,photogrp.getEmaile());
        contentValues.put(MOBILENUMBER,photogrp.getPhonee());
        contentValues.put(COMPANYNAME,photogrp.getComanpnynamee());
        contentValues.put(ADDRESS,photogrp.getAddresse());
        contentValues.put(PRICE,photogrp.getPricee());
        contentValues.put(DESCRIPTION,photogrp.getDescriptione());

        int stetus = db.update(TABLE_NAME,contentValues,ID +" =?"
                ,new String[]{String.valueOf(photogrp.getId())});

        db.close();
        return stetus;

    }

    public void deletePhotographer(int id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,ID +" =?",new String[]{String.valueOf(id)});
        db.close();

    }
}
