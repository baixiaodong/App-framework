package com.baixd.app.framework.downloader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kk on 2015/4/19 019.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "my_downloader_db.db";
    private static final int VERSION = 1;

    private static final String SQL_CREATE =
            "create table download_thread_info (_id integer primary key autoincrement," +
            " thread_id integer, url text, start integer, end integer, finished integer)";

    private static final String SQL_DROP =
            "drop table if exists download_thread_info ";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }
}
