package com.example.administrator.myxlistview.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myxlistview.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2016/10/22.
 * <p/>
 * <p/>
 * 1、onAttach(): 当该Fragment被添加到Activity时被回调。该方法只会被调用一次；
 * 2、onCreate(): 当创建Fragment时被回调。该方法只会被调用一次；
 * 3、onCreateView()：每次创建、绘制该Fragment的View组件时回调该方法，Fragment将会显示该方法返回的View 组件；
 * 4、onActivityCreated(): 当Fragment的宿主Activity被启动完成后回调该方法；
 * 5、onStart(): 启动Fragment时被回调；
 * 6、onResume(): onStart()方法后一定会回调onResume()方法；
 */
public class MyFragment extends Fragment {

    private String name;//标题名字
    private TextView tvName;
    private String htmlStr;//请求结果
    public static String URL_KNOWLEDGE_LIST = "http://183.56.132.189:9090/njb-mobile/"
            + "knowledgeService/getKnowledgeList.do";

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                Log.e("MyFragment", "htmlStr===" + htmlStr);
                tvName.setText(htmlStr);
//                textView.setText(htmlStr);
            }
        }

        ;
    };


    public MyFragment() {
    }

    public MyFragment(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_myfragment, container, false);
        tvName = (TextView) view.findViewById(R.id.tvName);
//        tvName.setText(name);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();

//        {"type":"0",
//                "pageNo":"0",
//                "pageSize":"20",
//                "token":"dGg6MTQ3ODA0ODkyOTMyNTpjZTgxMDNmYzJiN2Q4MWQ5NjcyMjEwYWIyMzI2MDZjYzpsb2dpbl91c2Vy",
//                "account":"th",
//                "iccid":"89860040191473587192",
//                "imei":"865982022395670",
//                "imsi":"460029928579195",
//                "model":"MI NOTE LTE",
//                "version":"3.3.0"}
        RequestBody requestBody = new FormEncodingBuilder()
                .add("type", "0")
                .add("pageNo", "0")
                .add("pageSize", "10")
                .add("token", "dGg6MTQ3ODA0ODkyOTMyNTpjZTgxMDNmYzJiN2Q4MWQ5NjcyMjEwYWIyMzI2MDZjYzpsb2dpbl91c2Vy")
                .add("account", "th")
                .add("iccid", "89860040191473587192")
                .add("imei", "865982022395670")
                .add("imsi", "460029928579195")
                .add("model", "MI NOTE LTE")
                .add("version", "3.3.0")
                .build();

//        // 创建一个Request
        final Request request = new Request
                .Builder()
                .url(URL_KNOWLEDGE_LIST)
                .post(requestBody)
                .build();

        // new call
        Call call = mOkHttpClient.newCall(request);
        // 请求加入调度
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Response response) throws IOException {
                htmlStr = response.body().string();
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {

            }
        });
    }
}
