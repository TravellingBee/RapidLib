package com.marno.mbasiclib.module.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.marno.mbasiclib.interfaces.ILoadView;

/**
 * Created by Marno on 2016/10/26/10:25
 * Function：通过RecyclerView实现下拉刷新和上拉加载的Activity
 * Desc：
 */
public abstract class RapidRefreshAndLoadFragment extends RapidRefreshFragment implements ILoadView {
    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter mAdapter;

    protected abstract void _initView(View view, Bundle savedInsanceState);

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);

        mRecyclerView = getRecyclerView();
        RecyclerView.LayoutManager layoutManager = getLayoutManager();

        if (null != layoutManager && null != mRecyclerView)
            mRecyclerView.setLayoutManager(layoutManager);

        setAdapter();
        _initView(view, savedInstanceState);
    }

    private void setAdapter() {
        mAdapter = getAdapter();
        if (null != mAdapter) {
            mAdapter.setOnLoadMoreListener(this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
