package com.baixd.app.framework.assistant.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kk on 2015/4/1.
 */
public class ToolHelper {

    public static String formatData(long data) {
        Date d = new Date(data);
        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format2.format(d);
        return str;
    }
}
