package com.example.administrator.myxlistview.okhttputils;

import android.os.Handler;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import java.io.IOException;

/**
 * Created by idea on 2016/6/25.
 */
public class HttpUtil {

    private static OkHttpClient okHttpClient;
    static {
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

    public static void call(final Object tag, final Handler handler, final HttpUiCallback uiCallback) {
        ThreadUtil.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                		//实际使用将URL换成全局常量
                        .url("http://img2.3lian.com/2014/f6/173/d/51.jpg")
                        .tag(tag)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(final Request request, final IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //失败的业务回调应交给外部自己实现
                                uiCallback.onFailure(request,e);
                            }
                        });
                    }

                    @Override
                    public void onResponse(final Response response) throws IOException {
                        if(response.isSuccessful()){
                            final String json = response.body().string();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //成功的业务回调应交给外部自己实现
                                    uiCallback.onResponse(json);
                                }
                            });
                        }else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //失败的业务回调应交给外部自己实现
                                    uiCallback.onResponseNotSuccessful();
                                }
                            });
                        }
                    }
                });
            }
        });

    }

    public interface HttpUiCallback {
        void onResponse(String json);
        void onFailure(Request request, IOException e);
        void onResponseNotSuccessful();
    }

}
