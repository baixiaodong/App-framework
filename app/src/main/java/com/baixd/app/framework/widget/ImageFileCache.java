package com.baixd.app.framework.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Created by kk on 2015/2/28.
 */
public class ImageFileCache {
    private static final String TAG = "ImageFileCache";

    private static final String CACHDIR = "ImgCache";
    private static final String WHOLESALE_CONV = ".cach";

    private static final int MB = 1024 * 1024;
    private static final int CACHE_SIZE = 10;
    private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 10;

    public ImageFileCache(){
        removeCache(getDirectory());
    }

    /**
     * 从缓存中获取图片
     */
    public Bitmap getImage(final String url){
        final String path = getDirectory() + "/" + converUrlToFileName(url);
        File file = new File(path);
        if(file.exists()){
            Bitmap bmp = BitmapFactory.decodeFile(path);
            if(bmp == null){
                file.delete();
            }else{
                updateFileTime(path);
                return bmp;
            }
        }
        return null;
    }

    /**
     * 将图片存储文件缓存
     * @param bmp
     * @param url
     */
    public void saveBitmap(Bitmap bmp, String url){
        if(bmp == null)
            return;

        //判断sdcadr上的空间
        if(FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()){
            //SDCard空间不足
            return;
        }

        String fileName = converUrlToFileName(url);
        String dir = getDirectory();
        File dirFile = new File(dir);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File file = new File(dir + "/" + fileName);

        try {
            file.createNewFile();
            OutputStream os = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新文件的最后修改时间
     * @param path
     */
    private void updateFileTime(String path) {
        File file = new File(path);
        long newModifedTime = System.currentTimeMillis();
        file.setLastModified(newModifedTime);
    }


    /**
     * 获取缓存目录
     */
    private String getDirectory(){
        String dir = getSDPath() + "/" + CACHDIR;
        return dir;
    }

    /**
     * 获取SD卡路径
     */
    private String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if(sdCardExist){
            sdDir = Environment.getExternalStorageDirectory();//获取根目录
        }
        if(sdDir != null){
            Log.i(TAG, "sdDir --> " + sdDir.toString());
            return sdDir.toString();
        }else{
            return "";
        }
    }

    /**
     * 将url转成文件名
     */
    private String converUrlToFileName(String url){
        String[] strs = url.split("/");
        String fileName = strs[strs.length - 1] + WHOLESALE_CONV;
        Log.i(TAG, "converUrlToFileName --> " + fileName);
        return fileName;
    }

    /**
     * 计算存储目录下的文件大小，
     * 当文件总大小大于规定的CACHE_SIZE或者sdcard剩余空间小于FREE_SD_SPACE_NEEDED_TO_CACHE的规定
     * 那么删除40%最近没有被使用的文件
     */
    private boolean removeCache(String dirPath){
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if(files == null){
            return true;
        }

        if(!android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return false;
        }

        int dirSize = 0;
        for (int i = 0; i < files.length; i++){
            if(files[i].getName().contains(WHOLESALE_CONV)){
                dirSize += files[i].length();
            }
        }

        if(dirSize > CACHE_SIZE * MB || FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()){
            int removeFactor = (int)((0.4 * files.length) + 1);
            Arrays.sort(files, new FileLastModifSort());
            for(int i = 0 ; i < removeFactor; i++){
                if(files[i].getName().contains(WHOLESALE_CONV)){
                    files[i].delete();
                }
            }
        }

        if(freeSpaceOnSd() <= CACHE_SIZE){
            return false;
        }

        return true;
    }

    /**
     * 计算sdCard上的剩余空间
     * @return
     */
    private int freeSpaceOnSd() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdFreeMB = stat.getAvailableBlocks() * stat.getBlockSize() / MB;
        return (int) sdFreeMB;
    }


    /**
     * 根据文件的最后修改时间进行排序
     */
    private class FileLastModifSort implements java.util.Comparator<File> {
        @Override
        public int compare(File file0, File file1) {
            if (file0.lastModified() > file1.lastModified()) {
                return 1;
            } else if (file0.lastModified() == file1.lastModified()) {
                return 0;
            } else {
                return -1;
            }

        }
    }
}






























