package com.utouu.test.module.main;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.marno.easystatelibrary.EasyStatusView;
import com.marno.mbasiclib.adapter.RecyclerAdapter;
import com.marno.mbasiclib.adapter.RecyclerAdapterHelper;
import com.marno.mbasiclib.basic.fragment.MBasicPagerFragment;
import com.marno.mbasiclib.manager.GlideManager;
import com.marno.mbasiclib.widgets.xrecyclerview.ProgressStyle;
import com.marno.mbasiclib.widgets.xrecyclerview.XRecyclerView;
import com.utouu.test.R;
import com.utouu.test.data.entity.TestEntity;
import com.utouu.test.module.second.SecondActivity;
import com.utouu.test.module.third.ThirdActivity;
import com.utouu.test.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by marno on 2016/8/23/15:20.
 */
public class FirstFragment extends MBasicPagerFragment {

    @BindView(R.id.content_view)
    XRecyclerView mRecyclerView;
    @BindView(R.id.esvLayout)
    EasyStatusView mEsvLayout;

    private RecyclerAdapter<TestEntity> mAdapter;
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
        return R.layout.fragment_first;
    }


    @Override
    protected void loadData() {
        ArrayList<TestEntity> entities = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            entities.add(new TestEntity(i + "测试数据", R.drawable.avater));
        }

        Observable.just(entities)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<TestEntity>>() {
                    @Override
                    public void call(ArrayList<TestEntity> entityArrayList) {
                        mEsvLayout.content();
                        if (mIsRefresh) mAdapter.clear();
                        mAdapter.addAll(entityArrayList);
                        mIsRefresh = false;
                    }
                });
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mEsvLayout.loading();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingListener(this);

        setBanner();
        setAdapter();
    }

    private void setAdapter() {
        mAdapter = new RecyclerAdapter<TestEntity>(mContext, R.layout.item_textview) {
            @Override
            protected void convert(RecyclerAdapterHelper helper, TestEntity item) {
                helper.setText(R.id.tv_name, item.name);
                ImageView ivAvater = (ImageView) helper.getItemView().findViewById(R.id.iv_shape);
                int position = helper.getLayoutPosition();
                if (position % 2 == 0) {
                    GlideManager.loadRoundImg(item.img, ivAvater);
                } else {
                    GlideManager.loadImg(item.img, ivAvater);
                }
                helper.getItemView().setOnClickListener(v ->
                        ActivityUtil.to(mContext, SecondActivity.class));
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    //设置banner
    private void setBanner() {
        View bannerView = LayoutInflater.from(mContext).inflate(R.layout.layout_banner, null);
        mBanner = (BGABanner) bannerView.findViewById(R.id.banner);

        //mBanner.setPageIndicator(new int[]{
        //        R.drawable.shape_indicator, R.drawable.shape_indicator_selected})
        //        .setPageIndicatorAlign(
        //                ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //BannerManager.showBanner(mBanner, images);
        List<Integer> images = Arrays.asList(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3);
//        List<Integer> images = Arrays.asList(R.drawable.banner1);
        List<String> titles = Arrays.asList("点击跳转", "点击跳转", "点击跳转");

        mBanner.setAdapter((banner, view, model, position) -> {
            GlideManager.loadImg(model, (ImageView) view);
        });

//        mBanner.setData(images, null);
        mBanner.setData(images, titles);

        mRecyclerView.addHeaderView(bannerView);

        mBanner.setOnItemClickListener((banner, view, model, position) -> {
            ActivityUtil.to(mContext, ThirdActivity.class);
        });
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        Observable.just("1")
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    loadData();
                    mRecyclerView.refreshComplete();
                });
    }

    @Override
    public void onLoadMore() {
        Observable.just("1")
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    ArrayList<TestEntity> entities = new ArrayList<>();
                    for (int i = 0; i < 30; i++) {
                        entities.add(new TestEntity("加载更多" + i + "测试数据", R.drawable.avater));
                    }
                    mAdapter.addAll(entities);
                    mRecyclerView.loadMoreComplete();
                });
    }

}
