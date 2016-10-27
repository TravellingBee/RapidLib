package com.utouu.test.module.third;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.marno.easystatelibrary.EasyStatusView;
import com.marno.easyutilcode.IntentUtil;
import com.marno.mbasiclib.manager.GlideManager;
import com.marno.mbasiclib.module.fragment.RapidRefreshAndLoadFragment;
import com.utouu.test.R;
import com.utouu.test.adapter.MuiltItemAdapter;
import com.utouu.test.data.entity.MessageEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;


/**
 * Created by marno on 2016/8/23/15:20.
 */
public class ContentFragment2 extends RapidRefreshAndLoadFragment {


    @BindView(R.id.rv_content) RecyclerView mRecyclerView;
    @BindView(R.id.ptr_layout) PtrFrameLayout mPtrLayout;
    @BindView(R.id.esv_layout) EasyStatusView mEsvLayout;
    private MuiltItemAdapter mAdapter;
    private BGABanner mBanner;

    public static ContentFragment2 newIns() {
        return new ContentFragment2();
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_esv_recyclerview;
    }

    @Override
    protected void _initView(View view, Bundle savedInsanceState) {
//        setBanner();
    }

    @Override
    protected void loadData() {
        ArrayList<MessageEntity> entities = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            int type = i % 2;
            entities.add(new MessageEntity(type, "当前类型：" + String.valueOf(type)));
        }
        mAdapter.addData(entities);
    }

    //设置banner
    private void setBanner() {
        View bannerView = LayoutInflater.from(mContext).inflate(R.layout.layout_banner, null);
        mBanner = (BGABanner) bannerView.findViewById(R.id.banner);

        List<Integer> images = Arrays.asList(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3);
        List<String> titles = Arrays.asList("点击跳转1", "点击跳转2", "点击跳转3");

        mBanner.setAdapter((banner, view, model, position) -> {
            GlideManager.loadImg(model, (ImageView) view);
        });

        mBanner.setData(images, titles);
        mBanner.setOnItemClickListener((banner, view, model, position) -> {
            IntentUtil.to(mContext, ThirdActivity.class);
        });

        mAdapter.addHeaderView(bannerView);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onLoadMoreRequested() {
        loadData();
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
    public BaseQuickAdapter getAdapter() {
        mAdapter = new MuiltItemAdapter(mContext, R.layout.item_left, R.layout.item_right);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
