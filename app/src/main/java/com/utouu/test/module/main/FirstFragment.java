package com.utouu.test.module.main;


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
import com.utouu.test.R;
import com.utouu.test.adapter.HomeAdapter;
import com.utouu.test.base.BaseCustomRefreshHeaderFragment;
import com.utouu.test.data.entity.TestEntity;
import com.utouu.test.data.retrofit.DefaultSubscriber;
import com.utouu.test.module.third.ThirdActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by marno on 2016/8/23/15:20.
 */
public class FirstFragment extends BaseCustomRefreshHeaderFragment {

    @BindView(R.id.esv_layout) EasyStatusView mEsvLayout;
    @BindView(R.id.rv_content) RecyclerView mRecyclerView;
    @BindView(R.id.ptr_layout) PtrFrameLayout mPtrLayout;
    private BGABanner mBanner;

    public static FirstFragment newIns() {
        return new FirstFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_esv_recyclerview;
    }


    @Override
    protected void loadData() {
        ArrayList<TestEntity> entities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entities.add(new TestEntity(i + "测试数据", R.drawable.avater));
        }

        Observable.just(entities)
                .delay(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<ArrayList<TestEntity>>() {
                    @Override
                    public void _onNext(ArrayList<TestEntity> entity) {
                        mEsvLayout.content();
                        if (mPtrLayout.isRefreshing()) mAdapter.setNewData(new ArrayList<>());
                        mAdapter.addData(entity);
                    }

                    @Override
                    public void onCompleted() {
                        mPtrLayout.refreshComplete();
                    }
                });
    }

    @Override
    protected void _initView(View view, Bundle savedInsanceState) {
        mEsvLayout.loading();
        setBanner();
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
    public BaseQuickAdapter getAdapter() {
        return new HomeAdapter(mContext);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        loadData();
    }

    @Override
    public void onLoadMoreRequested() {
        loadData();
    }

}
