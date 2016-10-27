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
    protected RecyclerView _mRecyclerView;

    protected BaseQuickAdapter mAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        _mRecyclerView = getRecyclerView();
        RecyclerView.LayoutManager layoutManager = getLayoutManager();

        if (null != layoutManager && null != _mRecyclerView)
            _mRecyclerView.setLayoutManager(layoutManager);

        setAdapter();
    }

    private void setAdapter() {
        mAdapter = getAdapter();
        if (null != mAdapter && null != _mRecyclerView) {
            mAdapter.setOnLoadMoreListener(this);
            _mRecyclerView.setAdapter(mAdapter);
        }
    }
}
