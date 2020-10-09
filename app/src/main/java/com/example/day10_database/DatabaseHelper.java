package com.example.day10_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "mydb";
    public static int DATABASE_VERSION = 1;
    public static String TABLE = "tb_nama";
    public static String key_id = "id";
    public static String key_firstname = "name";

    public static String sql = "CREATE TABLE "+ TABLE + "(" + key_id +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ key_firstname+" TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", sql);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE + "'");
        onCreate(sqLiteDatabase);
    }

    public long insertData(String nama){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_firstname, nama);
        long insert = sqLiteDatabase.insert(TABLE, null, values);
        return insert;
    }

    public ArrayList<String> getAllData(){
        ArrayList<String> alldata = new ArrayList<String>();
        String nama;
        String query = "SELECT * FROM "+ TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                nama = cursor.getString(cursor.getColumnIndex(key_firstname));
                alldata.add(nama);
            }
            while (cursor.moveToNext());
                Log.d("array", alldata.toString());

        }
        return alldata;
    }

}
