package com.utouu.test.module.third;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.marno.mbasiclib.entity.TabEntity;
import com.marno.mbasiclib.module.activity.RapidMainActivity;
import com.utouu.test.R;
import com.utouu.test.module.main.GoodsFragment;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends RapidMainActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected boolean isTransparentStatusBar() {
        return true;
    }

    @Override
    protected boolean isNeedSwipeBack() {
        return true;
    }

    @Override
    public boolean isPager() {
        return false;
    }


    @Override
    public List<TabEntity> getTabEntities() {
        ArrayList<TabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity(null, R.drawable.ic_news, R.drawable.ic_news_selected, GoodsFragment.newIns()));
        tabEntities.add(new TabEntity(null, R.drawable.ic_video, R.drawable.ic_video_selected, ContentFragment3.newIns()));
        tabEntities.add(new TabEntity("", R.drawable.ic_image, R.drawable.ic_image_selected, GoodsFragment.newIns()));
        tabEntities.add(new TabEntity("", R.drawable.ic_me, R.drawable.ic_me_selected, ContentFragment3.newIns()));
        return tabEntities;
    }

    //如果需要tab其他属性，需要实现该方法
    @Override
    public void setTab(CommonTabLayout tabLayout) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
