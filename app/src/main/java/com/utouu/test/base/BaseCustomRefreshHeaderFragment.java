package com.utouu.test.base;

import android.graphics.Color;

import com.marno.easyutilcode.DimensUtil;
import com.marno.mbasiclib.module.fragment.RapidRefreshAndLoadFragment;

import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by marno on 2016/10/26/22:22.
 */

public abstract class BaseCustomRefreshHeaderFragment extends RapidRefreshAndLoadFragment {

    @Override
    public PtrUIHandler getRefreshHeader() {
        StoreHouseHeader storeHouseHeader = new StoreHouseHeader(mContext);
        int padding = DimensUtil.dp2px(20, mContext);
        storeHouseHeader.setPadding(padding, padding, padding, padding);
        storeHouseHeader.initWithString("RapidLibs");
        storeHouseHeader.setTextColor(Color.parseColor("#333333"));
        return storeHouseHeader;
    }
}
