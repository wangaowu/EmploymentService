package com.unistrong.employmentservice.login;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.baselibs.utils.SPUtils;
import com.unistrong.employmentservice.databinding.ActivityLoginBinding;
import com.unistrong.employmentservice.main.MainActivity;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.HttpRequestImpl;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;

public class LoginPresenter {

    private ActivityLoginBinding binding;
    private static final float INIT_SCALE = 0;

    public LoginPresenter(BaseActivity context, ActivityLoginBinding binding) {
        this.binding = binding;
    }

    public String getSpUserName(Context ctx) {
        return SPUtils.getString(ctx, Constant.SP_KEY.USER_ACCOUNT);
    }

    public boolean isAvailed(String... ele1) {
        for (String ele : ele1) {
            if (TextUtils.isEmpty(ele) || "000".equals(ele))
                //检查表单项自动忽略edittext输入的000
                return false;
        }
        return true;
    }

    public boolean isOk(LoginViewModel viewModel) {
        String userName = viewModel.getUserName();
        String password = viewModel.getPassword();
        if (TextUtils.isEmpty(userName)) {
            IToast.toast("用户名为空!");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            IToast.toast("密码为空!");
            return false;
        }
        return true;
    }

    public void offsetLayout() {
        binding.llEditLay.setScaleY(INIT_SCALE);
    }

    public void resetLayout() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(800);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            binding.llEditLay.setScaleY(animatedValue);
        });
        valueAnimator.start();
    }

    /**
     * 登陆
     */
    public void login(String account, String pwd,  ResponseBody listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("account", account);
        params.put("password", pwd);
        HttpRequestImpl.getInstance().requestPost(Constant.Action.LOGIN, params, listener);
    }

    public void startMainActivity(BaseActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
