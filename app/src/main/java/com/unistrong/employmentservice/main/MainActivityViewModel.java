package com.unistrong.employmentservice.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.databinding.ActivityMainBinding;
import com.unistrong.employmentservice.main.fragment.MainFragment;
import com.unistrong.employmentservice.main.fragment.ManageFragment;
import com.unistrong.employmentservice.main.fragment.MineFragment;

/**
 * 首页viewModel
 */
public class MainActivityViewModel {

    private ActivityMainBinding binding;
    private static final int UNSELECT_COLOR = 0xFFAAAAAA;
    private static final int SELECT_COLOR = 0xFFFDA535;

    public MainActivityViewModel(ActivityMainBinding binding) {
        this.binding = binding;
    }

    public void setActivityStyle(String title, int statusColor) {
        binding.layoutTitle.ivLeft.setVisibility(View.GONE);
        binding.layoutTitle.tvTitle.setText(title);
        ViewGroup parent = (ViewGroup) binding.layoutTitle.tvTitle.getParent();
        parent.setBackgroundColor(statusColor);
    }

    public void clearBlue() {
        int childCount = binding.llCheckParent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = binding.llCheckParent.getChildAt(i);
            makeOrange(child, false);
        }
    }

    public void makeOrange(View view, boolean isBlue) {
        TextView tv = (TextView) view;
        String viewText = tv.getText().toString();
        int resId;
        if (viewText.equals("首页")) {
            resId = isBlue ? R.drawable.main_pressed : R.drawable.main_blur;
        } else if (viewText.equals("管辖人员")) {
            resId = isBlue ? R.drawable.manage_pressed : R.drawable.manage_blur;
        } else {
            resId = isBlue ? R.drawable.mine_pressed : R.drawable.mine_blur;
        }
        tv.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
        tv.setTextColor(isBlue ? SELECT_COLOR : UNSELECT_COLOR);
        tv.setScaleX(isBlue ? 1.1f : 1);
        tv.setScaleY(isBlue ? 1.1f : 1);
    }

    private void hideFragment(FragmentManager fragmentManager) {
        if (0 == fragmentManager.getFragments().size()) return;
        for (Fragment fragment : fragmentManager.getFragments()) {
            fragmentManager.beginTransaction().hide(fragment).commitNowAllowingStateLoss();
        }
    }

    private Fragment newFragment(String tag) {
        Bundle bundle = new Bundle();

        if (MainFragment.TAG.equals(tag)) {
            MainFragment mainFragment = new MainFragment();
            mainFragment.setArguments(bundle);
            return mainFragment;
        }
        if (ManageFragment.TAG.equals(tag)) {
            ManageFragment manageFragment = new ManageFragment();
            manageFragment.setArguments(bundle);
            return manageFragment;
        }
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(bundle);
        return mineFragment;
    }

    private Fragment getFragment(String tag, FragmentManager fragmentManager) {
        Fragment existFragment = fragmentManager.findFragmentByTag(tag);
        if (existFragment == null) {
            existFragment = newFragment(tag);
            fragmentManager.beginTransaction()
                    .add(binding.flContainer.getId(), existFragment, tag)
                    .commitNowAllowingStateLoss();
        }
        return existFragment;
    }

    public void switchTo(String tag, FragmentManager fragmentManager) {
        hideFragment(fragmentManager);
        Fragment fragment = getFragment(tag, fragmentManager);
        fragmentManager.beginTransaction().show(fragment).commitNowAllowingStateLoss();
    }

    public void refreshManageFragment(FragmentManager fragmentManager) {
        ManageFragment fragment = (ManageFragment) getFragment(ManageFragment.TAG, fragmentManager);
        fragment.onRefresh();
    }
}
