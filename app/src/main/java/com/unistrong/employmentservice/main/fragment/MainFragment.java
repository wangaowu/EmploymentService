package com.unistrong.employmentservice.main.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unistrong.baselibs.utils.IToast;
import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.databinding.FragmentMainBinding;
import com.unistrong.employmentservice.main.MainActivity;
import com.unistrong.employmentservice.main.MainActivityPresenter;
import com.unistrong.framwork.resp.ChartResp;
import com.unistrong.framwork.resp.SummaryInfoResp;
import com.unistrong.requestlibs.response.ResponseBody;

/**
 * 首页
 */
public class MainFragment extends Fragment {

    public static final String TAG = "MainFragment";
    private MainActivity activity;
    private MainActivityPresenter presenter;
    private FragmentMainBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        presenter = new MainActivityPresenter();
        requestSummaryData();
        requestChartData();
    }

    private void setViewsData(SummaryInfoResp.ResultBean result) {
        setViewText(binding.tvDidInMonth, "" + result.getAllChangeNum());
        setViewText(binding.tvUndoInMonth, "" + result.getAllChangeNum());
    }

    private void setViewText(TextView textView, String text) {
        text = TextUtils.isEmpty(text) ? "-" : text;
        textView.setText(text);
    }

    /**
     * 请求表格数据
     */
    private void requestChartData() {
        presenter.requestChartData(new ResponseBody<ChartResp>(ChartResp.class) {
            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onSuccess(ChartResp json) {

            }
        });
    }

    /**
     * 请求首页数量
     */
    private void requestSummaryData() {
        presenter.requestSummary(new ResponseBody<SummaryInfoResp>(SummaryInfoResp.class) {
            @Override
            public void onFailure(String message) {
                IToast.toast(message);
            }

            @Override
            public void onSuccess(SummaryInfoResp resp) {
                if (isFailure(resp.getCode())) {
                    IToast.toast(resp.getMsg());
                    return;
                }
                setViewsData(resp.getResult());
            }
        });
    }

}
