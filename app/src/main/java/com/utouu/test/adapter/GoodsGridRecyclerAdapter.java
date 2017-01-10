package com.utouu.test.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.marno.easyutilcode.ToastUtil;
import com.marno.rapidlib.manager.GlideManager;
import com.utouu.test.R;
import com.utouu.test.data.entity.GoodsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marno on 2016/6/26/15:47.
 * 商品的适配器-grid排列
 */
public class GoodsGridRecyclerAdapter extends BaseQuickAdapter<GoodsEntity, BaseViewHolder> {
    private Context mContext;

    public GoodsGridRecyclerAdapter(Context context) {
        super(R.layout.item_goods_grid, new ArrayList<>());
        this.mContext = context;
    }

    public GoodsGridRecyclerAdapter(int layoutResId, List<GoodsEntity> data) {
        super(R.layout.item_goods_grid, data);
    }

    public GoodsGridRecyclerAdapter(List<GoodsEntity> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsEntity item) {
        helper.setText(R.id.tv_goodsName_goodsFragment, item.goods_name);
        GlideManager.loadImg(item.goods_thumb, helper.getView(R.id.iv_goodsLogo_goodsFragment));
        helper.getConvertView().setOnClickListener(v -> ToastUtil.show("1234"));
    }
}
