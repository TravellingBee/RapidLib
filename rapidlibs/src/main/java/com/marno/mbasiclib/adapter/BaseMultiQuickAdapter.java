package com.marno.mbasiclib.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Marno on 2016/10/27/10:41
 * Function：
 * Desc：
 */
@Deprecated
public abstract class BaseMultiQuickAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    protected final int[] mLayoutResIds;

    public BaseMultiQuickAdapter(Context context, int... layoutResIds) {
        super(null);
        mLayoutResIds = layoutResIds;
    }

    public BaseMultiQuickAdapter(int[] layoutResIds) {
        this(null, layoutResIds);
    }

    @Override
    public int getItemViewType(int position) {
        if (getViewTypeCount() == 1) {
            return super.getItemViewType(position);
        }
        throw new RuntimeException("Required method getItemViewType was not overridden");
    }

    public int getViewTypeCount() {
        return mLayoutResIds.length;
    }

    public int getLayoutResId(int viewType) {
        throw new RuntimeException("Required method getLayoutResId was not overridden");
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, getLayoutResId(viewType));
    }

    //    @Override
//    public BaseViewHolder oncrede(ViewGroup parent, int viewType) {
//        int layoutResId;
//        if (getViewTypeCount() > 1) {
//            layoutResId = getLayoutResId(getItemViewType(viewType));
//        } else {
//            layoutResId = layoutResIds[0];
//        }
//        return new ViewHolder(layoutInflater.inflate(layoutResId, parent, false)) {
//        };
//    }

//    @Override
//    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        H helper = getAdapterHelper(holder);
//        T item = get(position);
//        convert(helper, item);
//    }

}
