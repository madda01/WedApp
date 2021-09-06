package com.example.dreamwedmadd.database;

import android.provider.BaseColumns;

public final class DBMaster {
    private DBMaster(){}

    //USERS CLASS TO STORE USER TABLE DATA
    public static class Users implements BaseColumns{
        public static final String TABLE_NAME1="user";
        //COLUMN NAMES FOR USER
        public static final String COLUMN_NAME_NAME="name";
        public static final String COLUMN_NAME_EMAIL="email";
        public static final String COLUMN_NAME_MOBILE="mobile";
        public static final String COLUMN_NAME_PASSWORD="password";

    }

    //COSTUMES CLASS TO STORE COSTUME TABLE DATA
    public static class Costumes implements BaseColumns{
        public static final String TABLE_NAME2="costume";
        //COLUMN NAMES FOR COSTUME
        public static final String COLUMN_NAME_TITLE="title";
        public static final String COLUMN_NAME_SHOP="shop";
        public static final String COLUMN_NAME_PRICE="price";
        public static final String COLUMN_NAME_DISCOUNT="discount";
        public static final String COLUMN_NAME_PHONE="phone";
        public static final String COLUMN_NAME_SIZE="size";
        public static final String COLUMN_NAME_DESCRIPTION="description";
    }

}
