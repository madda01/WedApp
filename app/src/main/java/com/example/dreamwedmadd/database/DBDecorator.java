package com.example.dreamwedmadd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dreamwedmadd.models.Decorator;

import java.util.ArrayList;
import java.util.List;

public class DBDecorator extends SQLiteOpenHelper {

    private static final int VERSION=9;

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
                " TEXT,"+LNAME+" TEXT,"+EMAIL+" TEXT,"+MOBILE+" TEXT,"+CNAME+" TEXT,"+ADDRESS+" TEXT,"+DESCRIPTION+" TEXT,"+PRICE+" REAL,"+" avatar blob);";

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
        contentValues.put("avatar",decorator.getImage());

        db.insert(TABLE_NAME,null,contentValues);
        db.close();

    }

    public List<Decorator> getAllDeco(){

        List<Decorator> decorators=new ArrayList();
        SQLiteDatabase db=getReadableDatabase();

        String sql="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                Decorator decorator=new Decorator();
                decorator.setId(cursor.getInt(0));
                decorator.setfName(cursor.getString(1));
                decorator.setlName(cursor.getString(2));
                decorator.setEmail(cursor.getString(3));
                decorator.setMobile(cursor.getString(4));
                decorator.setcName(cursor.getString(5));
                decorator.setAddress(cursor.getString(6));
                decorator.setDescription(cursor.getString(7));
                decorator.setPrice(cursor.getDouble(8));
                decorator.setImage(cursor.getBlob(9));

                decorators.add(decorator);


            }while (cursor.moveToNext());
        }


        return decorators;
    }

    public int DecoCount(){
        SQLiteDatabase db=getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=db.rawQuery(sql,null);

        return cursor.getCount();
    }

    public void deleteDeco(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME,ID+" =?",new String[]{String.valueOf(id)});
        db.close();
    }
    public Decorator getSingleDeco(int id){
        Decorator decorator;
        SQLiteDatabase db=getReadableDatabase();

        Cursor cursor=db.query(TABLE_NAME,new String[]{ID,FNAME,LNAME,EMAIL,MOBILE,CNAME,DESCRIPTION,ADDRESS,PRICE},ID+" =?",new String[]{String.valueOf(id)},null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
            decorator=new Decorator(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getDouble(8)
                    );
            return decorator;

        }
        return null;

    }

    public int UpdateDeco(Decorator decorator){
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

        int status=db.update(TABLE_NAME,contentValues,ID+" =?",new String[]{String.valueOf(decorator.getId())});

        db.close();
        return status;
    }




}



//    public int UpdateSngleToDo(ToDo toDo){
//        SQLiteDatabase db=getWritableDatabase();
//
//        ContentValues contentValues=new ContentValues();
//
//
//        contentValues.put(TITLE,toDo.getTitle());
//        contentValues.put(DESCRIPTION,toDo.getDescription());
//        contentValues.put(STARED,toDo.getStarted());
//        contentValues.put(FINISHED,toDo.getFinished());
//
//        int status=db.update(TABLE_NAME,contentValues,ID+ "=?",new String[]{String.valueOf(toDo.getId())});
//
//        db.close();
//
//        return status;
//    }



