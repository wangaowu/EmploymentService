package com.unistrong.employmentservice.main.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unistrong.baselibs.ui.LoadMoreListView;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.databinding.FragmentManageBinding;
import com.unistrong.employmentservice.main.MainActivity;
import com.unistrong.employmentservice.main.MainActivityPresenter;
import com.unistrong.employmentservice.main.ManagePersonAdapter;
import com.unistrong.framwork.resp.ChangeListResp;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 管辖人员
 */
public class ManageFragment extends Fragment implements LoadMoreListView.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "ManageFragment";
    private static final int START_INDEX = 1;

    private FragmentManageBinding binding;
    private MainActivity activity;
    private MainActivityPresenter presenter;
    private ManagePersonAdapter adapter;
    private List<ChangeListResp.ResultBean> datas = new ArrayList<>();
    private int currentIndex = START_INDEX;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manage, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.refreshLayout.setOnRefreshListener(this);
        binding.lvList.setOnLoadMoreListener(this);
        binding.lvList.setAdapter(adapter = new ManagePersonAdapter(getContext()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        presenter = new MainActivityPresenter();
        requestChangeList();
    }

    private void requestChangeList() {
        activity.createLoadingDialog();
        String param = "";// binding.etSearch.getText().toString();
        presenter.requestChangeList(param, param, currentIndex, new ResponseBody<ChangeListResp>(ChangeListResp.class) {
            @Override
            public void onFailure(String message) {
                activity.closeLoadingDialog();
                IToast.toast(message);
                activity.setRefreshComplete(binding.refreshLayout);
                loadComplete();
                boolean isBegining = currentIndex == START_INDEX;
                currentIndex = isBegining ? START_INDEX : --currentIndex;
            }

            @Override
            public void onSuccess(ChangeListResp resp) {
                activity.closeLoadingDialog();
                loadComplete();
                activity.setRefreshComplete(binding.refreshLayout);
                if (isFailure(resp.getCode())) {
                    IToast.toast(resp.getMsg());
                    return;
                }
                if (resp.getResult().isEmpty()) {
                    boolean isBegining = currentIndex == START_INDEX;
                    currentIndex = isBegining ? START_INDEX : --currentIndex;
                    return;
                }
                datas.addAll(resp.getResult());
                adapter.setDatas(datas);
            }
        });
    }

    private void loadComplete() {
        binding.lvList.setLoadCompleted();
    }

    @Override
    public void onloadMore() {
        currentIndex++;
        requestChangeList();
    }

    @Override
    public void onRefresh() {
        datas.clear();
        currentIndex = START_INDEX;
        requestChangeList();
    }
}
