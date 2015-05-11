package com.baixd.app.framework.adapter;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baixd.app.framework.R;

import java.util.ArrayList;
import java.util.List;

import static com.baixd.app.framework.R.id.mem_photo_img_id;
import static com.baixd.app.framework.R.id.tv_title;

public class MyCommonAdapterActivity extends ActionBarActivity {

    private ListView mListView;

    private List<Bean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_common_adapter);

        mListView = (ListView) findViewById(R.id.listview);

        initData();

        mListView.setAdapter(new MyCommonAdapter(this, datas));

    }

    private class MyCommonAdapter extends CommonAdapter<Bean>{
        public MyCommonAdapter(Context context, List<Bean> datas) {
            super(context, datas);
        }

        @Override
        public void setData(ViewHolder viewHolder, Bean item) {
//            ((TextView)viewHolder.getView(R.id.tv_title)).setText(item.getTitle());
//            ((TextView)viewHolder.getView(R.id.tv_desc)).setText(item.getDesc());
            viewHolder.setText(R.id.tv_title,item.getTitle());
            viewHolder.setText(R.id.tv_desc,item.getDesc());
        }

    }


    private void initData(){
        datas = new ArrayList<Bean>();
        Bean bean = new Bean();
        bean.setTitle("通用型Adapter 1");
        bean.setDesc("通过定义ViewHolder以及Adapter实现一个通用型的Adapter");
        datas.add(bean);
        bean = new Bean();
        bean.setTitle("通用型Adapter 2");
        bean.setDesc("通过定义ViewHolder以及Adapter实现一个通用型的Adapter");
        datas.add(bean);
        bean = new Bean();
        bean.setTitle("通用型Adapter 3");
        bean.setDesc("通过定义ViewHolder以及Adapter实现一个通用型的Adapter");
        datas.add(bean);
        bean = new Bean();
        bean.setTitle("通用型Adapter 4");
        bean.setDesc("通过定义ViewHolder以及Adapter实现一个通用型的Adapter");
        datas.add(bean);
        bean = new Bean();
        bean.setTitle("通用型Adapter 5");
        bean.setDesc("通过定义ViewHolder以及Adapter实现一个通用型的Adapter");
        datas.add(bean);
        bean = new Bean();
        bean.setTitle("通用型Adapter 6");
        bean.setDesc("通过定义ViewHolder以及Adapter实现一个通用型的Adapter");
        datas.add(bean);
    }

    class Bean {
        private String title;
        private String desc;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    private class MyListAdapter extends BaseAdapter {

        private Context mContext;
        private List<Bean> mDatas;

        public MyListAdapter(Context context, List<Bean> datas){
            this.mContext = context;
            this.mDatas = datas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = ViewHolder.getViewHolder(mContext, convertView, parent, R.layout
                            .listview_item,
                    position);

            ((TextView)holder.getView(R.id.tv_title)).setText(mDatas.get(position).getTitle());
            ((TextView)holder.getView(R.id.tv_desc)).setText(mDatas.get(position).getDesc());

            return holder.getConvertView();
        }

        //        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            ViewHolder viewHolder = null;
//
//            if(convertView == null){
//                convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item ,parent,
//                        false);
//                TextView title = (TextView)convertView.findViewById(R.id.tv_title);
//                TextView desc = (TextView) convertView.findViewById(R.id.tv_desc);
//
//                viewHolder = new ViewHolder();
//                viewHolder.mTitle = title;
//                viewHolder.mDesc = desc;
//
//                convertView.setTag(viewHolder);
//
//            }else{
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//
//            viewHolder.mTitle.setText(mDatas.get(position).getTitle());
//            viewHolder.mDesc.setText(mDatas.get(position).getDesc());
//
//            return convertView;
//
//        }
    }

//    class ViewHolder{
//        TextView mTitle;
//        TextView mDesc;
//    }






}
