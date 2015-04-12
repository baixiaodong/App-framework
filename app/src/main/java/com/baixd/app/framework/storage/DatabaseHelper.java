package com.baixd.app.framework.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kk on 2015/4/12 012.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_table";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sbq = new StringBuilder();
        sbq.append(" create table ").append(TABLE_NAME);
        sbq.append(" (title text not null,");
        sbq.append(" body text not null)");
        Log.i("create table ", sbq.toString());

        db.execSQL(sbq.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(DBEntity entity){
        SQLiteDatabase db = this.getWritableDatabase();
        StringBuilder sqb = new StringBuilder();
        sqb.append(" insert into ").append(TABLE_NAME);
        sqb.append(" (title, body)");
        sqb.append(" values(").append("'").append(entity.getName()).append("',");
        sqb.append("'").append(entity.getValue()).append("')");

        Log.i("inert table ", sqb.toString());

        //使用ContentValues来设置sql的值，而无需写sql语句

        ContentValues contentValues = new ContentValues();

        db.execSQL(sqb.toString());
    }

    public DBEntity query(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{"title", "body"}, null, null, null, null, null);

        DBEntity entity = new DBEntity();
        while(cursor.moveToNext()){
            entity.setName(cursor.getString(0));
            entity.setValue(cursor.getString(1));
        }
        return entity;
    }
}
