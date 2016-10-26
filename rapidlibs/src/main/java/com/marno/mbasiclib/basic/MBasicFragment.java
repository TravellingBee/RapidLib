package com.marno.mbasiclib.basic;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 李刚 on 2016/3/14/13:53.
 * 所有Fragment的基类
 */
public abstract class MBasicFragment extends RxFragment {

    protected String TAG = this.getClass().getSimpleName();

    protected Activity mContext;
    protected boolean mIsFirstShow = true;
    private Unbinder mUnbinder;

    protected abstract int getLayout();

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void loadData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (Activity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    /**
     * 不在iewpager中Fragment懒加载
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) lazyLoad();
    }

    /**
     * 在viewpager中的Fragment懒加载
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) lazyLoad();
    }

    private void lazyLoad() {
        if (mIsFirstShow) {
            mIsFirstShow = false;
            loadData();
        }
    }
}
