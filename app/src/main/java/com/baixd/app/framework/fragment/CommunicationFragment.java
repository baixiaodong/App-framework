package com.baixd.app.framework.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baixd.app.framework.R;


public class CommunicationFragment extends Fragment {

    private View view;

    private TextView mTextView;

    private CharSequence msg;

    private static final CharSequence RESP_MSG = "成功接收消息！";

    private OnFragmentInteractionListener mInteractionListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_communication, container, false);

        Bundle bundle = this.getArguments();
        msg = bundle.getCharSequence(CommunicationActivity.MSG_KEY);

        initView();

        Toast.makeText(getActivity(), "接收到了来自Fragment的消息：" + msg, Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        mInteractionListener = (OnFragmentInteractionListener) activity;
        mInteractionListener.onFragmentInteraction(RESP_MSG);
        super.onAttach(activity);
    }

    private void initView() {
        mTextView = (TextView) view.findViewById(R.id.tv_communiation_text);
        mTextView.setText(msg);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(CharSequence c);
    }


}
