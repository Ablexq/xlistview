package com.example.administrator.myxlistview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myxlistview.R;
import com.example.administrator.myxlistview.XListView.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityI extends AppCompatActivity {

    @Bind(R.id.id_nodataview)
    View nodataView;
    @Bind(R.id.id_listview)
    XListView listView;


    private List list_lv;//设置数据源
    private List list = new ArrayList<>();//设置adapter的总数据源
    int j;
    boolean isLoadData;
    boolean isRefresh;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maini);
        ButterKnife.bind(this);

        setData();

        nodataView.setVisibility(View.INVISIBLE);

        listView.setDivider(null);
        listView.setPullRefreshEnable(true);//可刷新
        listView.setPullLoadEnable(true);//可加载

//        nodataView.setOnClickListener(clickListener);
        listView.setXListViewListener(iXListViewListener);
//        listView.setOnItemClickListener(onItemClickListener);

        // 构建一个适配器
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
    }

    private void setData() {
        list_lv = new ArrayList<>();

        if (isLoadData) {
            isLoadData = false;
            for (int i = 0; i < 9; i++) {
                list_lv.add("加载数据" + (i + 1) + "===" + (new Random().nextInt(9) + 1));//1-9
            }
        } else if (isRefresh) {
            isRefresh = false;
            for (int i = 0; i < 9; i++) {
                list_lv.add("刷新数据" + (i + 1) + "===" + (new Random().nextInt(9) + 1));//1-9
            }
        } else {
            for (int i = 0; i < 9; i++) {
                list_lv.add("原来数据" + (i + 1) + "===" + (new Random().nextInt(9) + 1));//1-9
            }
        }

        list.addAll(list_lv);
    }

    /**
     * 格式化毫秒值 *  * @return
     */
    private String dateFormat() {
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = dateFormat.format(currentTimeMillis);
        return format;
    }

    private XListView.IXListViewListener iXListViewListener = new XListView.IXListViewListener() {
        @Override // 下拉刷新的回调方法
        public void onRefresh() {
            isRefresh = true;
            // 下拉刷新,应该展示最新数据,所以清空集合
            list.clear();
            setData();

            // 设置刷新时间
            listView.setRefreshTime(dateFormat());
            arrayAdapter.notifyDataSetChanged();
            // 停止加载更多
            listView.stopLoadMore();
            // 停止刷新
            listView.stopRefresh();
        }

        // @Override // //上拉加载的回调方法
        public void onLoadMore() {
            isLoadData = true;
            setData();

            arrayAdapter.notifyDataSetChanged(); //停止加载更多
            listView.stopLoadMore(); //停止刷新
            listView.stopRefresh();
        }
    };
}
