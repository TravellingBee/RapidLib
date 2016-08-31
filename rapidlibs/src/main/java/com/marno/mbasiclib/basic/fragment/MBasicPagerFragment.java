package com.marno.mbasiclib.basic.fragment;

/**
 * Created by Marno on 2016/8/31/16:57.
 * 功能：在viewpager中的fragment中继承该类
 * 描述：
 */
public abstract class MBasicPagerFragment extends MBasicFragment {

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mIsFirstShow) {
            mIsFirstShow = false;
            initData();
        }
    }
}
