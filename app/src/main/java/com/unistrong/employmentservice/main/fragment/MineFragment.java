package com.unistrong.employmentservice.main.fragment;

import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.unistrong.baselibs.utils.DensityUtils;
import com.unistrong.baselibs.utils.SPUtils;
import com.unistrong.employmentservice.R;
import com.unistrong.employmentservice.databinding.FragmentMineBinding;
import com.unistrong.employmentservice.main.MainActivity;
import com.unistrong.framwork.utils.Constant;

/**
 * 我的
 */
public class MineFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "MineFragment";

    private FragmentMineBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        binding.tvLogout.setOnClickListener(this);
        initData();
        return binding.getRoot();
    }

    private void initData() {
        binding.tvName.setText(SPUtils.getString(getContext(), Constant.SP_KEY.USER_NAME));
        binding.tvOffice.setText(SPUtils.getString(getContext(), Constant.SP_KEY.USER_OFFICE));
    }

    @Override
    public void onResume() {
        super.onResume();
        int animatorHeight = DensityUtils.dp2px(getContext(), 150);
        binding.rlTopAnimator.setPadding(0, 0, 0, animatorHeight);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(animatorHeight, 0);
        valueAnimator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            binding.rlTopAnimator.setPadding(0, 0, 0, animatedValue);
            binding.rlTopAnimator.invalidate();
        });
        valueAnimator.setDuration(800);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();
    }

    @Override
    public void onClick(View v) {
        MainActivity a = (MainActivity) getActivity();
        a.exitApp();
    }
}
