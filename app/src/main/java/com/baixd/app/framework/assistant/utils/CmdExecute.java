package com.baixd.app.framework.assistant.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Baixd on 2015/3/31.
 */
public class CmdExecute {

    private static final String TAG = "CmdExecute";

    public synchronized String run(String[] cmd, String workDirectory) throws IOException {

        String result = "";

        ProcessBuilder builder = new ProcessBuilder(cmd);
        if(workDirectory != null) {
            builder.directory(new File(workDirectory));
        }

        builder.redirectErrorStream(true);

        Process process = builder.start();
        InputStream in = process.getInputStream();
        byte[] bs = new byte[1024];
        while(in.read(bs) != -1){
            System.out.println(new String(bs));
            result = result + new String(bs);
        }
        in.close();

        return result;

    }
}
