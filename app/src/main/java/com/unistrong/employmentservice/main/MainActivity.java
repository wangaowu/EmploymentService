package com.unistrong.employmentservice.main;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.databinding.ActivityMainBinding;
import com.unistrong.employmentservice.main.fragment.MainFragment;
import com.unistrong.employmentservice.main.fragment.ManageFragment;
import com.unistrong.employmentservice.main.fragment.MineFragment;
import com.unistrong.framwork.utils.DynamicDictUtils;
import com.unistrong.framwork.utils.UpdateHelper;

/**
 * 就业信息首页
 */
public class MainActivity extends BaseActivity {
    public static final String TAG = "MainActivity";

    private FragmentManager fragmentManager;
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private double firstTime;

    @Override
    protected int getStatusBarColor() {
        return STATUS_BLUE;
    }

    @Override
    protected void initMvp() {
        DynamicDictUtils.init();
        fragmentManager = getSupportFragmentManager();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainActivityViewModel(binding);

        viewModel.setActivityStyle("喀什就业服务管理系统", STATUS_BLUE);
        showFragmentAt(binding.tvTabMain);
        UpdateHelper.checkVersion(this);
    }

    private void showFragmentAt(View view) {
        clickMain(view);
    }

    public void clickMain(View view) {
        viewModel.clearBlue();
        viewModel.makeOrange(view, true);
        viewModel.switchTo(MainFragment.TAG, fragmentManager);
    }

    public void clickManage(View view) {
        viewModel.clearBlue();
        viewModel.makeOrange(view, true);
        viewModel.switchTo(ManageFragment.TAG, fragmentManager);
    }

    public void clickMine(View view) {
        viewModel.clearBlue();
        viewModel.makeOrange(view, true);
        viewModel.switchTo(MineFragment.TAG, fragmentManager);
    }

    public void exitApp() {
        if (System.currentTimeMillis() - firstTime > 2000) {
            //如果两次按键时间间隔大于2000毫秒，则不退出
            IToast.toast("再按一次退出程序...");
            firstTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        //退到首页逻辑
        Fragment fragment = fragmentManager.findFragmentByTag(MainFragment.TAG);
        if (!fragment.isVisible()) {
            showFragmentAt(binding.tvTabMain);
            return;
        }
        exitApp();
    }

    public void refreshManageFragmentList() {
        viewModel.refreshManageFragment(fragmentManager);
    }
}
