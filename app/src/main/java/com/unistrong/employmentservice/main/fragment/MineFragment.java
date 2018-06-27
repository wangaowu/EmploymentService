package com.unistrong.employmentservice.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unistrong.employmentservice.main.MainActivity;
import com.unistrong.employmentservice.main.MainActivityPresenter;

/**
 * 我的
 */
public class MineFragment extends Fragment {

    public static final String TAG = "MineFragment";
    private MainActivity activity;
    private MainActivityPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText(getClass().getSimpleName());
        return textView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

}
