package com.unistrong.employmentservice.main;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.databinding.ActivityHouseInfoBinding;
import com.unistrong.employmentservice.main.fragment.MainFragment;
import com.unistrong.employmentservice.main.fragment.ManageFragment;
import com.unistrong.employmentservice.main.fragment.MineFragment;

/**
 * 就业信息首页
 */
public class MainActivity extends BaseActivity {
    public static final String TAG = "MainActivity";
    public static final String TASK_INFO = "taskInfo";
    public static final String TASK_STATUS = "status";

    private FragmentManager fragmentManager;
    private ActivityHouseInfoBinding binding;
    private MainActivityViewModel viewModel;
    private MainActivityPresenter presenter;
    private String status;

    @Override
    protected int getStatusBarColor() {
        return STATUS_BLUE;
    }

    @Override
    protected void initMvp() {
        initIntent();
        fragmentManager = getSupportFragmentManager();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_house_info);
        viewModel = new MainActivityViewModel(binding);
        presenter = new MainActivityPresenter();

        viewModel.setActivityStyle("喀什就业服务管理系统", STATUS_BLUE);
        showFragmentAt(binding.tvBasicInfo);
    }

    private void initIntent() {
        status = getIntent().getStringExtra(TASK_STATUS);
    }

    private void showFragmentAt(View view) {
        clickMain(view);
    }

    public void clickMain(View view) {
        viewModel.clearBlue();
        viewModel.makeBlue(view, true);
        viewModel.switchTo(MainFragment.TAG, fragmentManager);
    }

    public void clickManage(View view) {
        viewModel.clearBlue();
        viewModel.makeBlue(view, true);
        viewModel.switchTo(ManageFragment.TAG, fragmentManager);
    }

    public void clickMine(View view) {
        viewModel.clearBlue();
        viewModel.makeBlue(view, true);
        viewModel.switchTo(MineFragment.TAG, fragmentManager);
    }

    //刷新
    public void refreshHouseImageFragment() {
     //   viewModel.refreshHouseImageFragment(fragmentManager);
    }
}
