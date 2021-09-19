package com.example.dreamwedmadd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dreamwedmadd.models.Photographermodel;

public class photoDbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 2;
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
}
