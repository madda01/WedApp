package com.example.dreamwedmadd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dreamwedmadd.models.User;

import java.net.ConnectException;

public class DBConnection extends SQLiteOpenHelper {

    private static final int VERSION = 1; //version
    private static final String DB_NAME = "dreamwed"; //database name

    public DBConnection(Context context) {
        super(context,DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_USER_ENTRIES =
                "CREATE TABLE "+ DBMaster.Users.TABLE_NAME1 + " (" +
                        DBMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        DBMaster.Users.COLUMN_NAME_NAME + " TEXT," +
                        DBMaster.Users.COLUMN_NAME_EMAIL + " TEXT," +
                        DBMaster.Users.COLUMN_NAME_MOBILE + " TEXT," +
                        DBMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(SQL_CREATE_USER_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    //add a new user
    public boolean insertUser(User user){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(DBMaster.Users.COLUMN_NAME_NAME,user.getName());
        values.put(DBMaster.Users.COLUMN_NAME_EMAIL,user.getEmail());
        values.put(DBMaster.Users.COLUMN_NAME_MOBILE,user.getMobile());
        values.put(DBMaster.Users.COLUMN_NAME_PASSWORD,user.getPassword());

        long newRowId= db.insert(DBMaster.Users.TABLE_NAME1,null,values);
        if (newRowId>=1)
            return true;
        else
            return false;
    }

    //check user when register
    public boolean checkUser(String email){
        //array of columns to fetch
        String[] columns={DBMaster.Users._ID};
        SQLiteDatabase db= this.getReadableDatabase();

        //selection criteria
        String selection= DBMaster.Users.COLUMN_NAME_EMAIL +" = ?";

        //selection arugument
        String[] selectionArgs={email};

        Cursor cursor=db.query(DBMaster.Users.TABLE_NAME1,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount=cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount>0)
            return true;
        else
            return false;
    }

    //check user when login
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                DBMaster.Users._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = DBMaster.Users.COLUMN_NAME_EMAIL + " = ?" + " AND " + DBMaster.Users.COLUMN_NAME_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(DBMaster.Users.TABLE_NAME1, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                //group the rows
                null,                 //filter by row groups
                null);               //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    //delete user
    public void deleteUser(User email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs= {String.valueOf(email)};
        // delete user record by id
        db.delete(DBMaster.Users.TABLE_NAME1, DBMaster.Users.COLUMN_NAME_EMAIL + " LIKE ?", selectionArgs);
        db.close();
    }

    //update user
    public boolean updateUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBMaster.Users.COLUMN_NAME_PASSWORD,password);

        long count=db.update(DBMaster.Users.TABLE_NAME1,values,DBMaster.Users.COLUMN_NAME_EMAIL+" = ?",new String[]{ email });

        if (count==-1)
            return false;
        else
            return true;
    }

}
