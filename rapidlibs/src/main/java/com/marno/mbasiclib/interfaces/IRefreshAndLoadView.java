package com.marno.mbasiclib.interfaces;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Marno on 2016/10/26/11:25
 * Function：
 * Desc：
 */

public interface IRefreshAndLoadView extends BaseQuickAdapter.RequestLoadMoreListener, PtrHandler {
      BaseQuickAdapter getAdapter();

      RecyclerView.LayoutManager getLayoutManager();

}
