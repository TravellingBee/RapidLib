package com.marno.mbasiclib.module.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.marno.mbasiclib.interfaces.ILoadView;

/**
 * Created by Marno on 2016/10/26/10:25
 * Function：带下拉刷新和上拉加载的RecyclerView的Activity
 * Desc：
 */

public abstract class RapidRefreshAndLoadActivity extends RapidRefreshActivity implements ILoadView {
    protected RecyclerView mRecyclerView;

    protected BaseQuickAdapter mAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mRecyclerView = getRecyclerView();
        RecyclerView.LayoutManager layoutManager = getLayoutManager();

        if (null != layoutManager && null != mRecyclerView)
            mRecyclerView.setLayoutManager(layoutManager);

        setAdapter();
    }

    private void setAdapter() {
        mAdapter = getAdapter();
        if (null != mAdapter) {
            mAdapter.setOnLoadMoreListener(this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
