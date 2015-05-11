package com.baixd.app.framework.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baixd.app.framework.R;

/**
 * 通用型ViewHolder
 * Created by kk on 2015/5/9 009.
 */
public class ViewHolder {

    private View mConvertView;
    private SparseArray<View> mViews;
    private int mPosition;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();

        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int
            layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            return (ViewHolder) convertView.getTag();
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }
    
    public void setText(int viewId, CharSequence text){
        TextView textView = this.getView(viewId);
        textView.setText(text);
    }

}
