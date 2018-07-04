package com.unistrong.requestlibs.request;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.unistrong.requestlibs.gson.GsonProvider;
import com.unistrong.requestlibs.inter.IRequest;
import com.unistrong.requestlibs.response.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 请求基类
 */
public class BaseRequest extends BasePolicy {
    private static final String TAG = "BaseRequest";

    //public static final String HOST = "http://10.66.5.220:8080/employment-service";//lm
    //public static final String HOST = "http://10.66.5.232:8080/employment-service";//zg
    //public static final String HOST = "http://10.66.5.237:8089/employment-service";//sb
    public static final String HOST = "http://10.66.2.82:8084/employment-service";//开发服务内网

    private Call call;

    protected void request_(final String action, int method, Object params, String token, int timeout, long tag, final Handler handler, final ResponseBody listener) {
        if (listener == null) return;

        setConnectTimeout(timeout);
        Request newRequest = buildRequestBody(HOST + action, method, params, token, tag);
        call = newClient().newCall(newRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String failureString = e.toString();
                Log.e(actionTag(action), failureString);
                MainThread.runOnUIThread(handler, () -> listener.onFailure(failureString));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e(actionTag(action), response.toString());
                    MainThread.runOnUIThread(handler, () -> listener.onFailure("Response Failed!"));
                    return;
                }
                String respString = response.body().string();
                if (!TextUtils.isEmpty(respString)) {
                    //有效字符串
                    Log.e(actionTag(action), respString);
                    MainThread.runOnUIThread(handler, () -> listener.onSuccess(respString));
                    //send json
                    try {
                        Object o = GsonProvider.instance().fromJson(respString, listener.clazz);
                        MainThread.runOnUIThread(handler, () -> listener.onSuccess(o));
                    } catch (Exception e) {
                        Log.e(actionTag(action), e.toString());
                        MainThread.runOnUIThread(handler, () -> listener.onJsonFormateError(e.toString()));
                    }
                } else {
                    Log.e(actionTag(action), "null!");
                    MainThread.runOnUIThread(handler, () -> listener.onFailure("null!"));
                }
            }
        });
    }

    protected void deleteCall() {
        if (call != null && !call.isExecuted() && !call.isCanceled()) {
            //在队列&未执行&未取消
            call.cancel();
        }
    }

    /**
     * 构建请求
     *
     * @param url    地址
     * @param method 方法
     * @param params 参数
     * @param token
     * @param tag    请求标记
     * @return
     */
    private Request buildRequestBody(String url, int method, Object params, String token, long tag) {
        switch (method) {
            case IRequest.Method.GET:
                //get请求
                return buildGetRequest(url, params, token, tag);
            case IRequest.Method.POST_AS_FORM:
                //post_form请求
                return buildFormRequest(url, params, token, tag);
            case IRequest.Method.POST_AS_JSON:
                //post_json请求
                return buildJsonRequest(url, params, token, tag);
        }
        return new Request.Builder().url(url).tag(tag).build();//空参
    }

    /**
     * 构建get请求
     *
     * @param url
     * @param params
     * @param token
     * @param tag
     * @return
     */
    private Request buildGetRequest(String url, Object params, String token, long tag) {
        Request.Builder requestBuilder = new Request.Builder().get().tag(tag);
        try {
            requestBuilder.addHeader("token", token);
            richAppInfo(requestBuilder);
            String urlEncodedParam = getUrlEncodedParam(url, params);
            requestBuilder.url(url = url + "?" + urlEncodedParam);
        } catch (UnsupportedEncodingException e) {
            requestBuilder.url(url);
        }
        Log.e("【GET】", url);
        return requestBuilder.build();
    }

    /**
     * 构建表单请求
     *
     * @param url
     * @param params
     * @param token
     * @param tag
     * @return
     */
    private Request buildFormRequest(String url, Object params, String token, long tag) {
        FormBody formBody = getFormBody(url, params);
        Request.Builder builder = new Request.Builder().url(url).tag(tag).post(formBody);
        builder.addHeader("token", token);
        richAppInfo(builder);
        return builder.build();
    }

    /**
     * 构建json请求
     *
     * @param url
     * @param params
     * @param token
     * @param tag
     * @return
     */
    private Request buildJsonRequest(String url, Object params, String token, long tag) {
        String content = GsonProvider.instance().toJson(params);
        Log.e(actionRequest(url), content);
        Request.Builder builder = new Request.Builder();
        builder.addHeader("Connection", "close");
        builder.addHeader("token", token);
        richAppInfo(builder);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                content);
        return builder.url(url).tag(tag).post(requestBody).build();
    }

    private String getUrlEncodedParam(String url, Object params) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        if (params != null && params instanceof Map) {
            Map<String, String> map = (Map<String, String>) params;
            int index = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) continue;
                if (index != 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append(String.format("%s=%s",
                        key, URLEncoder.encode(value, "utf-8")));
                index++;
            }
        }
        return stringBuilder.toString();
    }

    private FormBody getFormBody(String url, Object params) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null && params instanceof Map) {
            Map<String, String> map = (Map<String, String>) params;
            Log.e(actionRequest(url), map.toString());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) continue;
                formBuilder.addEncoded(key, value);
            }
        }
        return formBuilder.build();
    }


    private String actionTag(String action) {
        return "【" + action + "】_RESULT";
    }

    private String actionRequest(String action) {
        return "【" + action + "】_PARAMS";
    }

}
