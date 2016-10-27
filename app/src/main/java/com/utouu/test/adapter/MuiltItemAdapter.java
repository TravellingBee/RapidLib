package com.utouu.test.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.marno.mbasiclib.adapter.BaseMultiQuickAdapter;
import com.marno.mbasiclib.manager.MLog;
import com.utouu.test.R;
import com.utouu.test.data.entity.MessageEntity;

import java.util.List;

/**
 * Created by Marno on 2016/10/27/11:18
 * Function：
 * Desc：
 */

public class MuiltItemAdapter extends BaseMultiQuickAdapter<MessageEntity> {


    public static final int TYPE_RECEIVE = 1;
    public static final int TYPE_SEND = 0;
    public static final int TYPE_NULL = -1;

    public MuiltItemAdapter(Context context, int... layoutResIds) {
        super(context, layoutResIds);
    }

    @Override
    public int getItemViewType(int position) {
        List<MessageEntity> allDatas = getData();
        if (position >= allDatas.size()) return TYPE_NULL;
        MessageEntity entity = allDatas.get(position);
        switch (entity.type) {
            case TYPE_RECEIVE:
                return TYPE_RECEIVE;
            case TYPE_SEND:
                return TYPE_SEND;
            default:
                return TYPE_NULL;
        }
    }

    @Override
    public int getLayoutResId(int viewType) {
        switch (viewType) {
            case TYPE_RECEIVE:
                return R.layout.item_left;
            case TYPE_SEND:
                return R.layout.item_right;
            default:
                return 0;
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEntity item) {
        int adapterPosition = helper.getAdapterPosition();
        int layoutPosition = helper.getLayoutPosition();
        int oldPosition = helper.getOldPosition();
        MLog.i("ligang>>", "adapterPosition = " + adapterPosition + "\tlayoutPosition = "
                + layoutPosition + "\toldPosition  = " + oldPosition);
        int viewType = getItemViewType(layoutPosition - getHeaderLayoutCount());
        switch (viewType) {
            case TYPE_RECEIVE:
                helper.setText(R.id.tv_left, "哈哈哈");
                break;
            case TYPE_SEND:
                helper.setText(R.id.tv_right, "asdfasdf");
                break;
        }
    }
}
