package com.utouu.test.module.main;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.marno.rapidlib.entity.TabEntity;
import com.marno.rapidlib.module.activity.RapidMainActivity;
import com.utouu.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marno on 2016/8/23/15:20.
 * 主页面
 */
public class MainActivity extends RapidMainActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    //返回true表示支持以ViewPager方式进行滑动，返回false则不
    @Override
    public boolean isPager() {
        return true;
    }

    @Override
    public List<TabEntity> getTabEntities() {
        ArrayList<TabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("新闻", R.drawable.ic_news, R.drawable.ic_news_selected, FirstFragment.newIns()));
        tabEntities.add(new TabEntity("图片", R.drawable.ic_image, R.drawable.ic_image_selected, GoodsFragment.newIns()));
        tabEntities.add(new TabEntity("我的", R.drawable.ic_me, R.drawable.ic_me_selected, FirstFragment.newIns()));
        return tabEntities;
    }

    //如果需要tab其他属性，需要实现该方法
    @Override
    public void setTab(CommonTabLayout tabLayout) {
    }

    @Override
    protected int getStatusBarColor() {
        return 0;
    }

}
