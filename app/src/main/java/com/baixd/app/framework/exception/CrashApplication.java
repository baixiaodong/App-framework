package com.baixd.app.framework.exception;

import android.app.Application;

/**
 * Created by kk on 2015/4/22 022.
 */
public class CrashApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
