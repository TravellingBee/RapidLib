package com.marno.mbasiclib.module;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.marno.mbasiclib.R;
import com.marno.mbasiclib.basic.MBasicFragment;
import com.marno.mbasiclib.interfaces.IRefreshAndLoadView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Marno on 2016/10/26/10:25
 * Function：带下拉刷新和上拉加载的RecyclerView的Activity
 * Desc：
 */
public abstract class RapidRefreshAndLoadFragment extends MBasicFragment implements IRefreshAndLoadView {
    protected RecyclerView mRecyclerView;
    protected PtrFrameLayout mPtrLayout;
    protected BaseQuickAdapter mAdapter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_content);
        mPtrLayout = (PtrFrameLayout) view.findViewById(R.id.ptr_layout);
        mPtrLayout.setPtrHandler(this);

        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (null != layoutManager) mRecyclerView.setLayoutManager(layoutManager);

        //解决与viewpager横向滑动冲突
        mPtrLayout.disableWhenHorizontalMove(true);
        /**
         * 如果直接使用PtrFragmeLayout的时候就需要在代码中去配置刷新头，
         * 但是建议直接继承PtrFrameLayout，然后根据项目修改刷新头，类似PtrClassicFrameLayout的做法
         */
        // mPtrLayout.setHeaderView(header);
        // mPtrLayout.addPtrUIHandler(header);

        setAdapter();
    }

    private void setAdapter() {
        mAdapter = getAdapter();
        if (null != mAdapter) {
            mAdapter.setOnLoadMoreListener(this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }


    /**
     * 并不是所有页面都需要上拉加载，需要的时候重写该方法即可
     */
    @Override
    public void onLoadMoreRequested() {

    }

    /**
     * 检测是否可以进行下拉刷新
     *
     * @param frame   下拉刷新控件
     * @param content 需要刷新的View
     * @param header  下拉刷新头
     * @return true=可以下拉刷新，false=不能下拉刷新
     */
    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }
}
