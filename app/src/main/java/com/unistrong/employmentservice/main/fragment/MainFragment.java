package com.unistrong.employmentservice.main.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unistrong.baselibs.ui.chart.BaseMeasure;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.databinding.FragmentMainBinding;
import com.unistrong.employmentservice.main.MainActivity;
import com.unistrong.employmentservice.main.MainActivityPresenter;
import com.unistrong.framwork.resp.ChartResp;
import com.unistrong.framwork.resp.SummaryInfoResp;
import com.unistrong.requestlibs.response.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 首页
 */
public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
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
        binding.refreshLayout.setOnRefreshListener(this);
        binding.tvRefresh.setOnClickListener(v -> {
            binding.refreshLayout.setRefreshing(true);
            onRefresh();
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        presenter = new MainActivityPresenter();
        onRefresh();
    }

    /**
     * 设置统计数量
     */
    private void setSummaryViewData(SummaryInfoResp.ResultBean result) {
        String time = new SimpleDateFormat("MM-dd HH:mm").format(new Date());
        binding.tvRefresh.setText("更新于" + time);
        if (result == null) {
            setViewText(binding.tvDidInMonth, null);
            setViewText(binding.tvUndoInMonth, null);
        } else {
            setViewText(binding.tvDidInMonth, String.valueOf(result.getCurrentChangedCount()));
            setViewText(binding.tvUndoInMonth, String.valueOf(result.getCurrentUnchangeCount()));
        }
    }

    private void setViewText(TextView textView, String text) {
        text = TextUtils.isEmpty(text) ? "-" : text;
        textView.setText(text);
    }

    private void setChartViewVisible(boolean visible) {
        if (visible) {
            binding.chartView.setVisibility(View.VISIBLE);
            binding.viewEmpty.setVisibility(View.INVISIBLE);
        } else {
            binding.chartView.setVisibility(View.INVISIBLE);
            binding.viewEmpty.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置图表数据
     */
    private void setChartViewData(List<ChartResp.ResultBean> chartList) {
        if (chartList == null || chartList.isEmpty()) return;
        setChartViewVisible(true);
        binding.chartView.setData(getMaxValue(chartList), composeChartData(chartList));
        binding.tvCountYear.setText("总变更数:" + getTotal(chartList));
    }

    private int getTotal(List<ChartResp.ResultBean> chartList) {
        int count = 0;
        for (ChartResp.ResultBean bean : chartList) {
            count += Integer.valueOf(bean.getCurrentDayCount());
        }
        return count;
    }

    private int getMaxValue(List<ChartResp.ResultBean> changeList) {
        List<ChartResp.ResultBean> copyList = new ArrayList<>(changeList);
        if (copyList != null) {
            Collections.sort(copyList, (o1, o2) -> {
                Integer l = Integer.valueOf(o1.getCurrentDayCount());
                Integer r = Integer.valueOf(o2.getCurrentDayCount());
                return r - l; //降序
            });
        }
        return Integer.valueOf(copyList.get(0).getCurrentDayCount());
    }

    private ArrayList<BaseMeasure.BindData> composeChartData(List<ChartResp.ResultBean> chartList) {
        ArrayList<BaseMeasure.BindData> bindDatas = new ArrayList<>();
        for (ChartResp.ResultBean bean : chartList) {
            float currentDayCount = Float.valueOf(bean.getCurrentDayCount());
            String xFlag = bean.getDay();
            bindDatas.add(new BaseMeasure.BindData(currentDayCount, xFlag));
        }
        return bindDatas;
    }

    /**
     * 请求表格数据
     */
    private void requestChartData() {
        presenter.requestChartData(new ResponseBody<ChartResp>(ChartResp.class) {
            @Override
            public void onFailure(String message) {
                setChartViewVisible(false);
                IToast.toast(message);
            }

            @Override
            public void onSuccess(ChartResp resp) {
                if (isFailure(resp.getCode())) {
                    setChartViewVisible(false);
                    IToast.toast(resp.getMsg());
                    return;
                }
                setChartViewData(resp.getResult());
            }
        });
    }

    /**
     * 请求统计数量
     */
    private void requestSummaryData() {
        presenter.requestSummary(new ResponseBody<SummaryInfoResp>(SummaryInfoResp.class) {
            @Override
            public void onFailure(String message) {
                activity.setRefreshComplete(binding.refreshLayout);
                IToast.toast(message);
            }

            @Override
            public void onSuccess(SummaryInfoResp resp) {
                activity.setRefreshComplete(binding.refreshLayout);
                if (isFailure(resp.getCode())) {
                    IToast.toast(resp.getMsg());
                    return;
                }
                setSummaryViewData(resp.getResult());
            }
        });
    }

    @Override
    public void onRefresh() {
        requestSummaryData();
        requestChartData();
    }
}
