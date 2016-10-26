package com.example.administrator.myxlistview.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myxlistview.fragment.MyFragment;
import com.example.administrator.myxlistview.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private MyAdapter adapter;
    private String[] tabs;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //右侧listview
        tabs = getResources().getStringArray(R.array.companies);
        fragments = new ArrayList<Fragment>();                //fragment是个list集合
        for (int i = 0; i < tabs.length; i++) {
            MyFragment myFragment = new MyFragment(tabs[i]);
            fragments.add(myFragment);
        }

//中间tablayout和viewpager
        //viewpager里面可以加载Fragment
        adapter = new MyAdapter(getSupportFragmentManager(), tabs, fragments);
        viewPager.setAdapter(adapter);

        tabLayout = ((TabLayout) findViewById(R.id.tabLayout));

        tabLayout.setupWithViewPager(viewPager);       // TabLayout和viewPager连接在一起
    }

    class MyAdapter extends FragmentPagerAdapter {

        private final String[] tabs;
        private final ArrayList<Fragment> fragments;

        public MyAdapter(FragmentManager fm, String[] tabs, ArrayList<Fragment> fragments) {
            super(fm);
            this.tabs = tabs;
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }


    }
}
