package com.utouu.test.module.main;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.marno.mbasiclib.module.RapidPagerMainActivity;
import com.utouu.test.R;

import java.util.ArrayList;

/**
 * Created by marno on 2016/8/23/15:20.
 * 主页面
 */
public class MainActivity extends RapidPagerMainActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public String[] getTabNameArray() {
        return new String[]{"新闻", "图片", "我的"};
    }

    @NonNull
    @Override
    public int[] getTabUnselectedIcon() {
        return new int[]{R.drawable.ic_news,
                R.drawable.ic_image, R.drawable.ic_me};
    }

    @NonNull
    @Override
    public int[] getTabSelectedIcon() {
        return new int[]{
                R.drawable.ic_news_selected,
                R.drawable.ic_image_selected, R.drawable.ic_me_selected};
    }

    @NonNull
    @Override
    public ArrayList<Fragment> initFragments() {
        mFragments.add(FirstFragment.newIns());
        mFragments.add(ThirdFragment.newIns());
        mFragments.add(FirstFragment.newIns());
        return mFragments;
    }

    //如果需要tab其他属性，需要实现该方法
    @Override
    public void setTab(CommonTabLayout tabLayout) {

    }

    @Override
    protected int getStatusBarColor() {
        return 0;
    }

    @Override
    protected void loadData() {

    }

}
