package com.utouu.test.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.marno.easyutilcode.IntentUtil;
import com.marno.mbasiclib.manager.GlideManager;
import com.utouu.test.R;
import com.utouu.test.data.entity.TestEntity;
import com.utouu.test.module.second.SecondBackActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<TestEntity, BaseViewHolder> {

    public HomeAdapter(Context context) {
        super(R.layout.item_textview, new ArrayList<>());
    }

    public HomeAdapter(List<TestEntity> data) {
        super(data);
    }

    public HomeAdapter(int layoutResId, List<TestEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestEntity item) {
        helper.setText(R.id.tv_name, item.name);
        ImageView ivAvater = helper.getView(R.id.iv_shape);

        int position = helper.getLayoutPosition();
        if (position % 2 == 0) {
            GlideManager.loadRoundImg(item.img, ivAvater);
        } else {
            GlideManager.loadImg(item.img, ivAvater);
        }
        helper.getConvertView().setOnClickListener(v ->
                IntentUtil.to(mContext, SecondBackActivity.class));
    }
}
