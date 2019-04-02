package com.moviesapp.project.dm_project.util;

import com.moviesapp.project.dm_project.interfaces.ConnectSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpUtil {
    public static String loginSessionID = "0";

    private static OkHttpUtil okHttpUtil = null;
    private OkHttpClient okHttpClient = null;

    private OkHttpUtil(){
        if (okHttpClient == null){
            CreateOkHttpClient();
        }
    }

    public static OkHttpUtil getInstance() {
        if (okHttpUtil == null){
            synchronized (OkHttpUtil.class){
                if (okHttpUtil == null){
                    okHttpUtil = new OkHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }

    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

    private void CreateOkHttpClient(){
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url, cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url);
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Request customization: add request headers
                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader("cookie", loginSessionID);
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    public void connect(List<String> key, List<String> value, String url,final ConnectSuccessListener connectSuccessListener){
        if(key.size()!=value.size()) {
            connectSuccessListener.onFailure("匹配失败");
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        for(int i=0;i<key.size();i++){
            builder.addFormDataPart(key.get(i), value.get(i));
        }

        final RequestBody requestBody = builder.build();
        final Request request = new Request.Builder()
                .url(ConstansUtil.SERVER_IP + url)
                .addHeader("Set-Cookie", OkHttpUtil.loginSessionID)
                .post(requestBody)
                .build();
        getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                connectSuccessListener.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if(response.code() == ConstansUtil.connectSuccess){
                    ResponseBody body = response.body();
                    connectSuccessListener.onResponse(body != null ? body.string(): "");
                }
            }
        });
    }
}
