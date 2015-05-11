package com.baixd.app.framework.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baixd.app.framework.R;

import java.util.List;

/**
 * Created by kk on 2015/5/9 009.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mDatas;

    public CommonAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder holder = ViewHolder.getViewHolder(mContext, convertView, parent, R.layout
                        .listview_item,
                position);

        setData(holder, getItem(position));

        return holder.getConvertView();
    }

    public abstract void setData(ViewHolder viewHolder, T item);
}
