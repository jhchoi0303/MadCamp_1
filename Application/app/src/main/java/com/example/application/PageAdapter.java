package com.example.application;

import android.content.res.AssetManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;


    public PageAdapter(FragmentManager fm, int pageCount){
        super(fm);
        this.mPageCount = pageCount;

    }


    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                BlankFragment1 b_fragment1 = new BlankFragment1();
                return b_fragment1;
            case 1:
                BlankFragment2 b_fragment2 = new BlankFragment2();
                return b_fragment2;
            case 2:
                BlankFragment3 b_fragment3 = new BlankFragment3();
                return b_fragment3;
            default:
                return null;

        }

    }

    public int getCount(){
        return mPageCount;
    }

}
