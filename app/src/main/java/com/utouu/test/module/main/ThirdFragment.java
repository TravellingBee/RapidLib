package com.utouu.test.module.main;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.marno.easystatelibrary.EasyStatusView;
import com.marno.mbasiclib.adapter.RecyclerAdapter;
import com.marno.mbasiclib.basic.fragment.MBasicPagerFragment;
import com.marno.mbasiclib.utils.ToastUtil;
import com.marno.mbasiclib.widgets.xrecyclerview.XRecyclerView;
import com.utouu.test.R;
import com.utouu.test.adapter.GoodsGridRecyclerAdapter;
import com.utouu.test.data.entity.GoodsEntity;
import com.utouu.test.data.entity.GoodsList;
import com.utouu.test.data.entity.GoodsListEntity;
import com.utouu.test.data.repository.GoodsRepository;
import com.utouu.test.data.retrofit.DefaultSubscriber;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by marno on 2016/8/26/11:01.
 */
public class ThirdFragment extends MBasicPagerFragment {

    @BindView(R.id.content_view)
    XRecyclerView mRecyclerView;
    @BindView(R.id.esvLayout)
    EasyStatusView mEsvLayout;

    private int mPageNum = 1;
    private int mAllPages;
    private HashMap<String, String> mParam_goodsList;

    private RecyclerAdapter<GoodsEntity> mAdapter;


    public static ThirdFragment newIns() {
        ThirdFragment instance = new ThirdFragment();
        return instance;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mParam_goodsList = new HashMap<>();

        mEsvLayout.loading();
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setLoadingListener(this);

        setAdapter();
    }


    // 加载数据
    protected void loadData() {
        initGoodsData(mPageNum);
    }


    //设置商品适配器
    private void setAdapter() {
        mAdapter = new GoodsGridRecyclerAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }


    //加载商品列表
    private void initGoodsData(int pageNum) {
        mParam_goodsList.put("page", String.valueOf(pageNum));
        GoodsRepository.getIns().getGoodsList(mParam_goodsList)
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new DefaultSubscriber<GoodsListEntity>() {
                    @Override
                    public void _onNext(GoodsListEntity entity) {
                        GoodsList goodsList = entity.goods;
                        mAllPages = goodsList.all_page;
                        //商品列表
                        List<GoodsEntity> goodsEntityList = goodsList.data;
                        if (goodsEntityList.isEmpty()) ToastUtil.remind("暂无该分类产品");
                        else {
                            if (mIsRefresh) mAdapter.clear();
                            mAdapter.addAll(goodsEntityList);
                            mIsRefresh = false;
                        }
                        mEsvLayout.content();
                        mRecyclerView.refreshComplete();
                        mRecyclerView.loadMoreComplete();
                    }
                });
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mRecyclerView.setNoMore(false);
        mPageNum = 1;
        loadData();
    }

    @Override
    public void onLoadMore() {
        mPageNum++;
        if (mPageNum > mAllPages) {
            ToastUtil.common("没有更多了");
            mRecyclerView.loadMoreComplete();
            mRecyclerView.setNoMore(true);
            return;
        }
        initGoodsData(mPageNum);
    }
}
