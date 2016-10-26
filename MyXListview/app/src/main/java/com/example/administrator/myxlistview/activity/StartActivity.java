package com.example.administrator.myxlistview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.myxlistview.R;

/**
 * Created by Administrator on 2016/10/13.
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }

    public void btn(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_listview:
                intent.setClass(StartActivity.this, MainActivity.class);
                break;
            case R.id.btn_gridview:
                intent.setClass(StartActivity.this, MainActivityI.class);
                break;
        }
        startActivity(intent);
    }
}
