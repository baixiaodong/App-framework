package com.baixd.app.framework.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kk on 2015/4/21 021.
 * http://blog.csdn.net/liuhe688/article/details/6584143#
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    private static CrashHandler instance;

    private static Thread.UncaughtExceptionHandler mDefaultHandler;

    private Context mContext;

    //存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String,String>();

    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private CrashHandler(){

    }

    public static CrashHandler getInstance(){
        instance = new CrashHandler();
        return instance;
    }

    public void init(Context context){
        this.mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(!handleException(ex) && mDefaultHandler != null){
            mDefaultHandler.uncaughtException(thread, ex);
        }else{
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                Log.e(TAG, "error: ", e);
            }
        }

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private boolean handleException(Throwable ex){
        if(ex == null){
            return false;
        }

        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        collectDeviceInfo(mContext);
        saveCrashInfoToFile(ex);

        return true;
    }

    //记录设备信息
    public void collectDeviceInfo(Context context){
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if(info != null){
                String versionName = info.versionName == null? "null" : info.versionName;
                String versionCode = info.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "an error occured when collect package info", e);
        }

        Field[] fields = Build.class.getDeclaredFields();
        for(Field field:fields){
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Log.e(TAG, "an error occured when collect crash info", e);
            }

        }
    }

    //记录日志，写入文件
    public String saveCrashInfoToFile(Throwable ex){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> entry:infos.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        Throwable cause = ex.getCause();
        while(cause != null){
            cause.printStackTrace(pw);
            cause = cause.getCause();
        }

        pw.close();
        String result = writer.toString();
        sb.append(result);

        try{
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                String path = "/sdcard/crash/";
                File dir = new File(path);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path+fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        }catch(Exception e){
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }
}
