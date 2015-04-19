package com.baixd.app.framework.downloader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kk on 2015/4/19 019.
 */
public class ThreadDAOImpl implements ThreadDAO {

    private DatabaseHelper mDBHelper = null;

    public ThreadDAOImpl(Context context) {
        mDBHelper = new DatabaseHelper(context);
    }

    @Override
    public void insertThread(ThreadInfo threadInfo) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(" insert into download_thread_info(thread_id, url, start, end, " +
                "finished) values(?,?,?,?,?)", new Object[]{threadInfo.getId(),
                threadInfo.getUrl(), threadInfo.getStart(), threadInfo.getEnd(), threadInfo.getFinished()});
        db.close();
    }

    @Override
    public void deleteThread(String url, int threadId) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(" delete from  download_thread_info where url = ? and thread_id = ?",
                new Object[]{url, threadId});
        db.close();
    }

    @Override
    public void updateThread(String url, int threadId, int finished) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(" update download_thread_info set finished = ? where url = ? and thread_id = ?",
                new Object[]{finished, url, threadId});
        db.close();
    }

    @Override
    public List<ThreadInfo> getThreads(String url) {

        List<ThreadInfo> threadInfos = new ArrayList<ThreadInfo>();

        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from download_thread_info where url = ?",
                new String[]{url});
        while (cursor.moveToNext()) {
            ThreadInfo threadInfo = new ThreadInfo();
            threadInfo.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            threadInfo.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            threadInfo.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            threadInfo.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
            threadInfo.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));

            threadInfos.add(threadInfo);
        }
        cursor.close();
        db.close();
        return threadInfos;
    }

    @Override
    public boolean isExistsThread(String url, int threadId) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from download_thread_info where url = ? and " +
                "thread_id  = ?", new String[]{url, threadId + ""});
        boolean exists = cursor.moveToNext();
        cursor.close();
        db.close();
        return exists;
    }
}
