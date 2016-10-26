package com.utouu.test.module.third;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marno.easystatelibrary.EasyStatusView;
import com.marno.mbasiclib.module.fragment.RapidRefreshFragment;
import com.utouu.test.R;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;


/**
 * Created by marno on 2016/8/23/15:20.
 */
public class ContentFragment2 extends RapidRefreshFragment {


    @BindView(R.id.rv_content) RecyclerView mRvContent;
    @BindView(R.id.ptr_layout) PtrFrameLayout mPtrLayout;
    @BindView(R.id.esv_layout) EasyStatusView mEsvLayout;

    public static ContentFragment2 newIns() {
        return new ContentFragment2();
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_esv_recyclerview;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public PtrUIHandler getRefreshHeader() {
        return new PtrClassicDefaultHeader(mContext);
    }

    @Override
    public PtrFrameLayout getPtrView() {
        return mPtrLayout;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }
}
