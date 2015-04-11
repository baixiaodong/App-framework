package com.baixd.app.framework.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.baixd.app.framework.R;

public class StaticLoadFragment extends Fragment {

    private TextView mTextView;
    private Button mButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_static_load, container, false);

        mTextView = (TextView) view.findViewById(R.id.frag_tv_content);
        mButton = (Button) view.findViewById(R.id.frag_btn_change_content);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText("内容改变了吆！");
            }
        });

        return view;
    }


}
