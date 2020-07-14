package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    private TabLayout mTabLayout;

    private ViewPager mViewPager;
    private PageAdapter mPageAdapter;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tedPermission();


        mContext = getApplicationContext();

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.phone));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.photo));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.quiz));
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mPageAdapter = new PageAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(mPageAdapter);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
            }
        };


        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
                .check();

    }


}