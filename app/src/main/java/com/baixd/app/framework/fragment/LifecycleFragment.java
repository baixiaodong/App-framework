package com.baixd.app.framework.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baixd.app.framework.R;

import javax.security.auth.login.LoginException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LifecycleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LifecycleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LifecycleFragment extends Fragment {

    /**
     * 1)加载Fragment onAttach --> onCrate --> onCreateView --> onActivityCreated --> onStart --> onResume
     * 2)按返回键 onPause --> onStop --> onDestroyView --> onDestroy --> onDetach
     * 3)在加载了Fragment的页面内 锁屏 onPause --> onStop
     * 4)在加载了Fragment的页面内 解锁 onStart --> onResume
     * 5)在加载了Fragment的页面内 HOME onPause --> onStop
     * 6)销毁activity onPause --> onStop --> onDestroyView --> onDestroy --> onDetach
     */

    public static final String TAG = "LifecycleFragment";

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "exec onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "exec onCreateView");
        return inflater.inflate(R.layout.fragment_lifecycle, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, "exec onAttach");
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "exec onDetach");
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        Log.i(TAG, "exec onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "exec onResume");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "exec onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "exec onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "exec onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        Log.i(TAG, "exec onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "exec onStop");
        super.onStop();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
