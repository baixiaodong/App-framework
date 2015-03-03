package com.baixd.app.framework.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kk on 2015/3/2.
 */
public class ImageGetFromHttp {
    private static final String TAG = "ImageGetFromHttp";

    public static Bitmap downloadBitmap(String url){
        final HttpClient client = new DefaultHttpClient();
        final HttpGet getRequest = new HttpGet(url);

        try {
            HttpResponse response = client.execute(getRequest);
            final int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
                Log.e(TAG, "Error " + statusCode + " while retrieving bitmap from " + url);
                return null;
            }

            final HttpEntity entity = response.getEntity();
            if(entity != null){
                InputStream inputStream = null;

                try {
                    inputStream = entity.getContent();
                    FilterInputStream fit = new FlushedInputStream(inputStream);
                    return BitmapFactory.decodeStream(fit);
                } finally{
                    if(inputStream != null){
                        inputStream.close();
                        inputStream = null;
                    }
                    entity.consumeContent();
                }
            }
        } catch (IOException e) {
            getRequest.abort();
            Log.e(TAG, "I/O error while retrieving bitmap from " + url, e);
        } finally{
            client.getConnectionManager().shutdown();
        }

        return null;

    }

    private static class FlushedInputStream extends FilterInputStream {
        public FlushedInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public long skip(long byteCount) throws IOException {
            long totalBytesSkipped = 0L;
            while(totalBytesSkipped < byteCount){
                long bytesSkipped = in.skip(byteCount - totalBytesSkipped);
                if(bytesSkipped == 0L){
                    int b = read();
                    if(b < 0){
                        break;
                    } else{
                        bytesSkipped = 1;
                    }
                }
                totalBytesSkipped += bytesSkipped;
            }
            return totalBytesSkipped;
        }
    }




























}
