package com.baixd.app.framework.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.baixd.app.framework.R;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GridViewActivity extends ActionBarActivity {

    private static final String TAG = "GridViewActivity";

    private String[] imageThumbUrls = Images.IMAGE_THUMB_URLS;

    private GridView mGridView;

    private int width;

    private ArrayList<SoftReference<Bitmap>> bitmapList = new ArrayList<SoftReference<Bitmap>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        mGridView = (GridView) findViewById(R.id.gv1);
//        mGridView.setAdapter(new ImageAdapter(this));
        mGridView.setAdapter(new ImageAdapter(this, mGridView, imageThumbUrls));

//        new LoadImageThread().start();

        width = this.getWindowManager().getDefaultDisplay().getWidth();
    }

    private int[] thumbs = {
            R.drawable.image_gridview1, R.drawable.image_gridview2,
            R.drawable.image_gridview3, R.drawable.image_gridview4,
            R.drawable.image_gridview5, R.drawable.image_gridview6,
            R.drawable.image_gridview7, R.drawable.image_gridview8,
            R.drawable.image_gridview9, R.drawable.image_gridview10,
            R.drawable.image_gridview11, R.drawable.image_gridview12,
            R.drawable.image_gridview13, R.drawable.image_gridview14,
            R.drawable.image_gridview15, R.drawable.image_gridview16
    };


    class LoadImageThread extends Thread{

        @Override
        public void run() {
            for (int i : thumbs) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), i);
                SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(bitmap);
                bitmapList.add(softReference);
            }
        }
    }

    /**
     * 实现BaseAdapter
     */
    public class ImageAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

        private Context mContext;
        private GridView mGridView;
        private String [] imageThumbUrls;
        private int mFirstVisibleItem;
        private int mVisibleItemCount;
        private boolean isFirstEnter = true;

        private ImageMemoryCache memoryCache;
        private ImageFileCache fileCache;

        public ImageAdapter(Context context){
            this.mContext = context;
        }

        public ImageAdapter(Context context, GridView gridView, String[] imageThumbUrls){
            this.mContext = context;
            this.mGridView = gridView;
            this.imageThumbUrls = imageThumbUrls;

            memoryCache = new ImageMemoryCache(context);
            fileCache = new ImageFileCache();

            mGridView.setOnScrollListener(this);
        }

        @Override
        public int getCount() {
//            return thumbs.length;
            return imageThumbUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return imageThumbUrls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ImageView imageView;
            final String imageUrl = imageThumbUrls[position];

            if (convertView == null) {
                imageView = new ImageView(mContext);
            } else {
                imageView = (ImageView) convertView;
            }

            int wh = (width-20) / 3 ;
            imageView.setLayoutParams(new GridView.LayoutParams(wh, wh));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(10, 10, 10, 10);

//            imageView.setImageResource(thumbs[position]);
//            imageView.setTag(imageUrl);
            getBitmap(imageUrl, new OnImageLoaderListener() {
                @Override
                public void onImageLoader(Bitmap bmp) {
                    imageView.setImageBitmap(bmp);
                }
            });
//            imageView.setImageBitmap(getBitmap(imageUrl));

            return imageView;
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            mFirstVisibleItem = firstVisibleItem;
            mVisibleItemCount = visibleItemCount;
//            if(isFirstEnter && mVisibleItemCount > 0){
//                showImage(mFirstVisibleItem, mVisibleItemCount);
//                isFirstEnter = false;
//            }

        }

        private void showImage(int mFirstVisibleItem, int mVisibleItemCount) {

        }

        public Bitmap getBitmap(final String url, final OnImageLoaderListener onImageLoaderListener) {
            Bitmap result = null;

            final Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Bitmap result = (Bitmap)msg.obj;
                    onImageLoaderListener.onImageLoader(result);
                }
            };



            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // 从内存缓存中获取图片
                    Bitmap result = memoryCache.getBitmapFromCache(url);
                    if (result == null) {
                        // 文件缓存中获取
                        result = fileCache.getImage(url);
                        Log.i(TAG, "file image result --> " + result);
                        if (result == null) {
                            // 从网络获取
                            result = ImageGetFromHttp.downloadBitmap(url);
                            Log.i(TAG, "net image result --> " + result);
                            if (result != null) {
                                fileCache.saveBitmap(result, url);
                                memoryCache.addBitmapToCache(url, result);
                            }
                        } else {
                            // 添加到内存缓存
                            memoryCache.addBitmapToCache(url, result);
                        }
                    }
                    if(result != null){
                        Message msg = new Message();
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }

                }
            });

            Log.i(TAG, "image result --> " + result);
            return result;
        }
    }

    public interface OnImageLoaderListener{
        public void onImageLoader(Bitmap bmp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
