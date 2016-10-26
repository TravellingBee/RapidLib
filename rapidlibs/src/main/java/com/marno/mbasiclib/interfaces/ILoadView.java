package com.marno.mbasiclib.interfaces;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by Marno on 2016/10/26/11:25
 * Function：
 * Desc：
 */
public interface ILoadView extends BaseQuickAdapter.RequestLoadMoreListener {
    BaseQuickAdapter getAdapter();

    RecyclerView.LayoutManager getLayoutManager();

    RecyclerView getRecyclerView();
}
