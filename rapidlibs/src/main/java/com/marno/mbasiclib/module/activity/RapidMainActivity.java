package com.marno.mbasiclib.module.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.marno.mbasiclib.R;
import com.marno.mbasiclib.basic.MBasicActivity;
import com.marno.mbasiclib.entity.TabEntity;
import com.marno.mbasiclib.interfaces.IMainView;

import java.util.ArrayList;

/**
 * Created by marno on 2016/8/23/15:20.
 * 快速创建主页布局
 */
public abstract class RapidMainActivity extends MBasicActivity implements IMainView {
    
    public CommonTabLayout mTabLayout;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.rapid_activity_main;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mTabLayout = (CommonTabLayout) findViewById(R.id.tablayout);

        String[] tabNameArray = getTabNameArray();
        int[] tabSelectedIcon = getTabSelectedIcon();
        int[] tabUnselectedIcon = getTabUnselectedIcon();
        final ArrayList<Fragment> fragments = initFragments();

        if (tabNameArray == null) {
            mTabLayout.setTextsize(0);
            mTabLayout.setIconHeight(26);
            mTabLayout.setIconWidth(26);
        }
        setTab(mTabLayout);

        for (int i = 0, size = fragments.size(); i < size; i++) {
            String title = tabNameArray == null ? "" : tabNameArray[i];
            int selectedIcon = tabSelectedIcon[i];
            int unSelectedIcon = tabUnselectedIcon[i];
            mTabEntities.add(new TabEntity(title, selectedIcon, unSelectedIcon));
        }

        mTabLayout.setTabData(mTabEntities, this, R.id.fLayout_container, fragments);
    }

    @Override
    public void onBackPressed() {
        quitApp();
    }
}
