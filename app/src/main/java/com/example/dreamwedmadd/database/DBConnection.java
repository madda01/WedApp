package com.example.dreamwedmadd.database;

import static com.example.dreamwedmadd.database.DBMaster.Users.TABLE_NAME1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dreamwedmadd.models.Costume;
import com.example.dreamwedmadd.models.Photographermodel;
import com.example.dreamwedmadd.models.User;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection extends SQLiteOpenHelper {


    private static final int VERSION = 21; //version

    private static final String DB_NAME = "dreamwed"; //database name

    public DBConnection(Context context) {
        super(context,DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creating user table
        String SQL_CREATE_USER_ENTRIES =
                "CREATE TABLE "+ DBMaster.Users.TABLE_NAME1 + " (" +
                        DBMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        DBMaster.Users.COLUMN_NAME_NAME + " TEXT," +
                        DBMaster.Users.COLUMN_NAME_EMAIL + " TEXT," +
                        DBMaster.Users.COLUMN_NAME_MOBILE + " TEXT," +
                        DBMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(SQL_CREATE_USER_ENTRIES);

        //creating costume table
        String SQL_CREATE_COSTUME_ENTRIES=
                "CREATE TABLE "+ DBMaster.Costumes.TABLE_NAME2 + "(" +
                        DBMaster.Costumes._ID + " INTEGER PRIMARY KEY," +
                        DBMaster.Costumes.COLUMN_NAME_TITLE + " TEXT," +
                        DBMaster.Costumes.COLUMN_NAME_PRICE + " REAL," +
                        DBMaster.Costumes.COLUMN_NAME_EMAIL + " TEXT," +
                        DBMaster.Costumes.COLUMN_NAME_SHOP + " TEXT," +
                        DBMaster.Costumes.COLUMN_NAME_PHONE + " TEXT," +
                        DBMaster.Costumes.COLUMN_NAME_DESCRIPTION + " TEXT," +
                        DBMaster.Costumes.COLUMN_NAME_IMAGE + " BLOB"+
                        ");";
        sqLiteDatabase.execSQL(SQL_CREATE_COSTUME_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE_QUERY1 = "DROP TABLE IF EXISTS "+ DBMaster.Users.TABLE_NAME1;
        String DROP_TABLE_QUERY2 = "DROP TABLE IF EXISTS "+ DBMaster.Costumes.TABLE_NAME2;

        // Drop older table if existed
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY1);
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY2);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

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

    //add a new costume
    public boolean insertCostume(Costume costume){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(DBMaster.Costumes.COLUMN_NAME_TITLE,costume.getTitle());
        values.put(DBMaster.Costumes.COLUMN_NAME_PRICE,costume.getPrice());
        values.put(DBMaster.Costumes.COLUMN_NAME_EMAIL,costume.getEmail());
        values.put(DBMaster.Costumes.COLUMN_NAME_SHOP,costume.getShop());
        values.put(DBMaster.Costumes.COLUMN_NAME_PHONE,costume.getPhone());
        values.put(DBMaster.Costumes.COLUMN_NAME_DESCRIPTION,costume.getDescription());
        values.put(DBMaster.Costumes.COLUMN_NAME_IMAGE,costume.getImage());
        long newRowId= db.insert(DBMaster.Costumes.TABLE_NAME2,null,values);
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
    public void deleteUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs= {String.valueOf(email)};
        // delete user record by id
        db.delete(DBMaster.Users.TABLE_NAME1, DBMaster.Users.COLUMN_NAME_EMAIL + " LIKE ?", selectionArgs);
        db.close();
    }

    //delete costume
    public void deleteCostume(int cosid) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs= {String.valueOf(cosid)};
        // delete costume record by id
        db.delete(DBMaster.Costumes.TABLE_NAME2, DBMaster.Costumes._ID + " LIKE ?", selectionArgs);
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

    //update costumes
    public int updateSingleCostume(Costume costume) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBMaster.Costumes.COLUMN_NAME_TITLE,costume.getTitle());
        values.put(DBMaster.Costumes.COLUMN_NAME_PRICE,costume.getPrice());
        values.put(DBMaster.Costumes.COLUMN_NAME_EMAIL,costume.getEmail());
        values.put(DBMaster.Costumes.COLUMN_NAME_SHOP,costume.getShop());
        values.put(DBMaster.Costumes.COLUMN_NAME_PHONE,costume.getPhone());
        values.put(DBMaster.Costumes.COLUMN_NAME_DESCRIPTION,costume.getDescription());

        int status = db.update(DBMaster.Costumes.TABLE_NAME2,values,DBMaster.Costumes._ID +" = ?",
                new String[]{String.valueOf(costume.getId())});

        db.close();
        return status;
    }

    // Count costume table records
    public int countCostumes(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ DBMaster.Costumes.TABLE_NAME2;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    // Get all costumes into a list
    public List<Costume> getAllCostumes(){

        List<Costume> costumes = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+DBMaster.Costumes.TABLE_NAME2;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                // Create new costume object
                Costume costume = new Costume();

                costume.setId(cursor.getInt(0));
                costume.setTitle(cursor.getString(1));
                costume.setPrice(cursor.getDouble(2));
                costume.setEmail(cursor.getString(3));
                costume.setShop(cursor.getString(4));
                costume.setPhone(cursor.getString(5));
                costume.setDescription(cursor.getString(6));
                costume.setImage(cursor.getBlob(7));

                costumes.add(costume);
            }while (cursor.moveToNext());
        }
        return costumes;
    }


    // Get a single costume
    public Costume getSingleCostume(int cosid){
        Costume costume;
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(DBMaster.Costumes.TABLE_NAME2,new String[]{DBMaster.Costumes._ID,DBMaster.Costumes.COLUMN_NAME_TITLE, DBMaster.Costumes.COLUMN_NAME_PRICE,DBMaster.Costumes.COLUMN_NAME_EMAIL,DBMaster.Costumes.COLUMN_NAME_SHOP, DBMaster.Costumes.COLUMN_NAME_PHONE,DBMaster.Costumes.COLUMN_NAME_DESCRIPTION}, DBMaster.Costumes._ID + " =?",new String[]{String.valueOf(cosid)},null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
            costume = new Costume(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            return costume;

        }
        return null;

    }

    //update user profile
    public boolean Updateuser(String name, String email, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBMaster.Users.COLUMN_NAME_NAME,name);
        values.put(DBMaster.Users.COLUMN_NAME_MOBILE,mobile);

        long count=db.update(DBMaster.Users.TABLE_NAME1,values,DBMaster.Users.COLUMN_NAME_EMAIL+" = ?",new String[]{ email });

        if (count==-1)
            return false;
        else
            return true;
    }


    public User getSingleUser(String i) {
        User user;
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME1,new String[]{DBMaster.Users.COLUMN_NAME_NAME,DBMaster.Users.COLUMN_NAME_EMAIL,DBMaster.Users.COLUMN_NAME_MOBILE}, DBMaster.Users.COLUMN_NAME_EMAIL + " =?",new String[]{String.valueOf(i)},null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
            user = new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            return user;

        }
        return null;
    }
}
