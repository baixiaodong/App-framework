package com.baixd.app.framework.assistant.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * Created by kk on 2015/3/31.
 */
public class FetchData {

    private static final String TAG = "FetchData";

    /**
     * 获取操作系统版本信息
     * @return
     */
    public static String fetchVersionInfo() {

        String result = null;
        CmdExecute cmdExec = new CmdExecute();
        String[] args = {"/system/bin/cat", "/proc/version"};
        try {
            result = cmdExec.run(args, "/system/bin/");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static StringBuilder builder = null;

    private static String initProperty(String description, String propertyStr){
        if(builder == null){
            builder = new StringBuilder();
        }

        builder.append(description).append(":");
        builder.append(System.getProperty(propertyStr)).append("\n");
        return builder.toString();
    }

    /**
     * 获取系统信息
     *
     * @return
     */
    public static String getSystemProperty(){
        builder = new StringBuilder();
        initProperty("java.vendor.url", "java.vendor.url");
        initProperty("java.class.path", "java.class.path");
        initProperty("user.home", "user.home");
        initProperty("java.class.version", "java.class.version");
        initProperty("os.version", "os.version");
        initProperty("java.vendor", "java.vendor");
        initProperty("user.dir", "user.dir");
        initProperty("user.timezone", "user.timezone");
        initProperty("path.separator", "path.separator");
        initProperty(" os.name", " os.name");
        initProperty("os.arch", "os.arch");
        initProperty("line.separator", "line.separator");
        initProperty("file.separator", "file.separator");
        initProperty("user.name", "user.name");
        initProperty("java.version", "java.version");
        initProperty("java.home", "java.home");

        return builder.toString();
    }

    /**
     * 运营商信息
     * @param ctx
     * @return
     */
    public static String fetchTelStatus(Context ctx){
        String result = null;
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);

        String str = "";
        str += "DeviceId(IMEI) = " + tm.getDeviceId() + "\n";
        str += "DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + "\n";
        str += "Line1Number = " + tm.getLine1Number() + "\n";
        str += "NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n";
        str += "NetworkOperator = " + tm.getNetworkOperator() + "\n";
        str += "NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n";
        str += "NetworkType = " + tm.getNetworkType() + "\n";
        str += "PhoneType = " + tm.getPhoneType() + "\n";
        str += "SimCountryIso = " + tm.getSimCountryIso() + "\n";
        str += "SimOperator = " + tm.getSimOperator() + "\n";
        str += "SimOperatorName = " + tm.getSimOperatorName() + "\n";
        str += "SimSerialNumber = " + tm.getSimSerialNumber() + "\n";
        str += "SimState = " + tm.getSimState() + "\n";
        str += "SubscriberId(IMSI) = " + tm.getSubscriberId() + "\n";
        str += "VoiceMailNumber = " + tm.getVoiceMailNumber() + "\n";

        int mcc = ctx.getResources().getConfiguration().mcc;
        int mnc = ctx.getResources().getConfiguration().mnc;
        str += "IMSI MCC (Mobile Country Code):" + String.valueOf(mcc) + "\n";
        str += "IMSI MNC (Mobile Network Code):" + String.valueOf(mnc) + "\n";
        result = str;
        return result;
    }

    /**
     * 获取CPU信息
     * @return
     */
    public static String fetchCpuInfo(){
        String result = null;
        CmdExecute cmdExe = new CmdExecute();
        String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
        try {
            result = cmdExe.run(args, "/system/bin/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取内存信息
     * @param ctx
     * @return
     */
    public static String getMemoryInfo(Context ctx){
        StringBuilder sb = new StringBuilder();
        final ActivityManager activityManager
                = (ActivityManager) ctx.getSystemService(ctx.ACTIVITY_SERVICE);

        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        sb.append("\n total Available Memory : ").append(memoryInfo.availMem >> 10).append("k");
        sb.append("\n total Available Memory : ").append(memoryInfo.availMem >> 20).append("M");
        sb.append("\n In low memory situation: ").append(memoryInfo.lowMemory);

        String result = null;
        CmdExecute cmdExe = new CmdExecute();
        String[] args = {"/system/bin/cat", "/proc/meminfo"};
        try {
            result = cmdExe.run(args, "/system/bin");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString() + "\n\n" + result;
    }

    /**
     * 获取磁盘信息
     * @return
     */
    public static String fetchDiskInfo(){
        String result = null;
        CmdExecute cmdExe = new CmdExecute();
        String[] args = {"/system/bin/df","/system/bin/"};
        try {
            result = cmdExe.run(args, "/system/bin/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取网络信息
     * @return
     */
    public static String fetchNetCfgInfo(){
        Log.i(TAG, "fetch netCfg info start...");
        String result = null;
        CmdExecute cmdExe = new CmdExecute();
        String [] args = {"/system/bin/netcfg"};
        try {
            result = cmdExe.run(args, "/system/bin/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取显示屏信息
     * 宽度 widthPixels
     * 高度 heightPixels
     * 密度 density
     * 尺寸 xdpi ydpi
     * @return
     */
    public static String getDisplayMetrics(Context ctx){
        String str = "";
        DisplayMetrics dm = new DisplayMetrics();
        dm = ctx.getApplicationContext().getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        float density = dm.density; //密度
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        str += "The absolute width : " + String.valueOf(screenWidth) + " pixels\n";
        str += "The absolute height: " + String.valueOf(screenHeight) + " pixels\n";
        str += "The logical density of the display.:" + String.valueOf(density) + "\n";
        str += "X dimension : " + String.valueOf(xdpi) + " pixels per inch\n";
        str += "Y dimension : " + String.valueOf(ydpi) + " pixels per inch\n";
        return str;
    }


    public static String fetchProcessInfo(){
        String result = null;
        CmdExecute cmdExe = new CmdExecute();
        String[] args = {"/system/bin/top", "-n", "1"};
        try {
            result = cmdExe.run(args, "/system/bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static String getRunningServiceInfo(Context ctx){
        StringBuilder sb = new StringBuilder();
        final ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services= manager.getRunningServices(100);

        for(ActivityManager.RunningServiceInfo s:services){

            sb.append("pid: ").append(s.pid);
            sb.append("\nprocess: ").append(s.process);
            sb.append("\nservice: ").append(s.service);
            sb.append("\ncrashCount: ").append(s.crashCount);
            sb.append("\nclientCount: ").append(s.clientCount);
            sb.append("\nactiveSince: ").append(ToolHelper.formatData(s.activeSince));
            sb.append("nlastActivityTime: ").append(ToolHelper.formatData(s.lastActivityTime));
        }

        return sb.toString();
    }

    public static String getRunningTaskInfo(Context ctx){
        StringBuilder sb = new StringBuilder();

        final ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = manager.getRunningTasks(100);

        for(ActivityManager.RunningTaskInfo t:tasks){
            sb.append("id: ").append(t.id);
            sb.append("\nbaseActivity: ").append(t.baseActivity.flattenToString());
            sb.append("\nnumActivities: ").append(t.numActivities);
            sb.append("\nnumRunning: ").append(t.numRunning);
            sb.append("\ndescription: ").append(t.description);
            sb.append("\n\n");
        }

        return sb.toString();
    }































}
