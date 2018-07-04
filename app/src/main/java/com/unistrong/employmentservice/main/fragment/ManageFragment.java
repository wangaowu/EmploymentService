package com.unistrong.employmentservice.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.unistrong.baselibs.ui.LoadMoreListView;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.change.EmployeeEditActivity;
import com.unistrong.employmentservice.databinding.FragmentManageBinding;
import com.unistrong.employmentservice.detail.EmployeeDetailActivity;
import com.unistrong.employmentservice.history.EmployeeHistoryActivity;
import com.unistrong.employmentservice.main.MainActivity;
import com.unistrong.employmentservice.main.MainActivityPresenter;
import com.unistrong.employmentservice.main.ManageEmployeeAdapter;
import com.unistrong.framwork.resp.ChangeListResp;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 管辖人员
 */
public class ManageFragment extends Fragment implements LoadMoreListView.OnLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener, ManageEmployeeAdapter.OnItemClickListener {
    public static final String TAG = "ManageFragment";
    public static final int MAX_LENGTH_SEARCH = 18; //最长搜索内容
    private static final int START_INDEX = 1;//起始页码

    private FragmentManageBinding binding;
    private MainActivity activity;
    private MainActivityPresenter presenter;
    private ManageEmployeeAdapter adapter;

    private List<ChangeListResp.ResultBean> datas = new ArrayList<>();
    private int currentIndex = START_INDEX;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manage, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.etSearch.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LENGTH_SEARCH)});
        binding.etSearch.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.refreshLayout.setOnRefreshListener(this);
        binding.lvList.setOnLoadMoreListener(this);
        binding.lvList.setAdapter(adapter = new ManageEmployeeAdapter(getContext(), this));
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                datas.clear();
                adapter.setDatas(datas);
                currentIndex = START_INDEX;
                binding.refreshLayout.setRefreshing(true);
                String[] searchInfo = getSearchInfo(s.toString());
                requestChangeList(searchInfo[0], searchInfo[1]);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
        presenter = new MainActivityPresenter();
        requestChangeList(null, null);
    }

    /**
     * @return 搜索信息 身份证=0/idcard-1
     */
    private String[] getSearchInfo(String searchString) {
        String[] infos = new String[2];
        if (!TextUtils.isEmpty(searchString)) {
            if (TextUtils.isDigitsOnly(searchString)) {
                //仅数字，代表idcard
                infos[1] = searchString;
            } else {
                //姓名
                infos[0] = searchString;
            }
        }
        return infos;
    }

    private void showEmpty(boolean show) {
        binding.tvNotification.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void requestChangeList(String name, String idcard) {
        presenter.requestChangeList(name, idcard, getContext(), currentIndex, new ResponseBody<ChangeListResp>(ChangeListResp.class) {
            @Override
            public void onFailure(String message) {
                IToast.toast(message);
                activity.setRefreshComplete(binding.refreshLayout);
                loadComplete();
                boolean isBegining = currentIndex == START_INDEX;
                currentIndex = isBegining ? START_INDEX : --currentIndex;
            }

            @Override
            public void onSuccess(ChangeListResp resp) {
                loadComplete();
                activity.setRefreshComplete(binding.refreshLayout);
                if (isFailure(resp.getCode())) {
                    IToast.toast(resp.getMsg());
                    return;
                }
                if (resp.getResult().isEmpty()) {
                    boolean isBegining = currentIndex == START_INDEX;
                    currentIndex = isBegining ? START_INDEX : --currentIndex;
                    if (isBegining) showEmpty(true);
                    return;
                }
                showEmpty(false);
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
        String[] searchInfo = getSearchInfo(binding.etSearch.getText().toString());
        requestChangeList(searchInfo[0], searchInfo[1]);
    }

    @Override
    public void onRefresh() {
        binding.etSearch.setText("");
    }

    @Override
    public void onItemClick(ChangeListResp.ResultBean itemBean) {
        Intent intent = new Intent(getContext(), EmployeeDetailActivity.class);
        intent.putExtra(EmployeeDetailActivity.INTENT_KEY, itemBean);
        intent.putExtra(EmployeeDetailActivity.SHOW_TYPE, EmployeeDetailActivity.TYPE_INIT_DETAIL);
        getActivity().startActivity(intent);
    }

    @Override
    public void onHistoryClick(ChangeListResp.ResultBean itemBean) {
        Intent intent = new Intent(getContext(), EmployeeHistoryActivity.class);
        intent.putExtra(EmployeeDetailActivity.INTENT_KEY, itemBean);
        getActivity().startActivity(intent);
    }

    @Override
    public void onEditClick(ChangeListResp.ResultBean itemBean) {
        Intent intent = new Intent(getContext(), EmployeeEditActivity.class);
        intent.putExtra(EmployeeEditActivity.INTENT_KEY, itemBean);
        intent.putExtra(EmployeeEditActivity.SHOW_TYPE, EmployeeEditActivity.TYPE_EDIT);
        getActivity().startActivity(intent);
    }

    @Override
    public void onChangeClick(ChangeListResp.ResultBean itemBean) {
        Intent intent = new Intent(getContext(), EmployeeEditActivity.class);
        intent.putExtra(EmployeeEditActivity.INTENT_KEY, itemBean);
        intent.putExtra(EmployeeEditActivity.SHOW_TYPE, EmployeeEditActivity.TYPE_CHANGE);
        getActivity().startActivity(intent);
    }
}
