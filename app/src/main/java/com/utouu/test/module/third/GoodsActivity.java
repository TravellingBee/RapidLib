package com.utouu.test.module.third;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.marno.easystatelibrary.EasyStatusView;
import com.marno.easyutilcode.ToastUtil;
import com.marno.rapidlib.enums.RxLifeEvent;
import com.marno.rapidlib.module.activity.RapidRefreshLoadActivity;
import com.utouu.test.R;
import com.utouu.test.adapter.GoodsGridRecyclerAdapter;
import com.utouu.test.data.entity.GoodsEntity;
import com.utouu.test.data.entity.GoodsList;
import com.utouu.test.data.entity.GoodsListEntity;
import com.utouu.test.data.repository.GoodsRepository;
import com.utouu.test.data.retrofit.DefaultSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class GoodsActivity extends RapidRefreshLoadActivity {

    @BindView(R.id.esv_layout) EasyStatusView mEsvLayout;
    @BindView(R.id.rv_content) RecyclerView mRecyclerView;
    @BindView(R.id.ptr_layout) PtrFrameLayout mPtrLayout;

    private int mPageNum = 1;
    private int mAllPages;
    private HashMap<String, String> mParam_goodsList;
    private GoodsGridRecyclerAdapter mGoodsAdapter;

    @Override
    protected int getLayout() {
        return R.layout.layout_esv_recyclerview;
    }

    @Override
    protected boolean isNeedSwipeBack() {
        return true;
    }

    @Override
    protected void loadData() {
        initGoodsData(mPageNum);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mEsvLayout.loading();
        mParam_goodsList = new HashMap<>();
    }


    //加载商品列表
    private void initGoodsData(int pageNum) {
        mParam_goodsList.put("page", String.valueOf(pageNum));
        GoodsRepository.getIns().getGoodsList(mParam_goodsList)
                .compose(bindUntilEvent(RxLifeEvent.DESTROY))
                .subscribe(new DefaultSubscriber<GoodsListEntity>() {
                    @Override
                    public void _onNext(GoodsListEntity entity) {
                        GoodsList goodsList = entity.goods;
                        mAllPages = goodsList.all_page;
                        //商品列表
                        List<GoodsEntity> goodsEntityList = goodsList.data;
                        if (goodsEntityList.isEmpty()) ToastUtil.show("暂无该分类产品");
                        else {
                            if (mPtrLayout.isRefreshing())
                                mGoodsAdapter.setNewData(new ArrayList<>());
                            mGoodsAdapter.addData(goodsEntityList);
                        }
                        mEsvLayout.content();
                        mPtrLayout.refreshComplete();
                    }
                });
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        mGoodsAdapter = new GoodsGridRecyclerAdapter(mContext);
        return mGoodsAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(mContext, 2);
    }


    @Override
    public void onLoadMoreRequested() {
        mPageNum++;
        if (mPageNum > mAllPages) {
            ToastUtil.show("没有更多了");
            mGoodsAdapter.loadMoreComplete();
            return;
        }
        initGoodsData(mPageNum);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        mPageNum = 1;
        loadData();
    }

}
