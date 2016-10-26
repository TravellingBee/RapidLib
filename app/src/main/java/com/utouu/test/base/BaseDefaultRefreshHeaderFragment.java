package com.utouu.test.base;

import com.marno.mbasiclib.module.fragment.RapidRefreshAndLoadFragment;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by marno on 2016/10/26/22:20.
 */

public abstract class BaseDefaultRefreshHeaderFragment extends RapidRefreshAndLoadFragment {

    @Override
    public PtrUIHandler getRefreshHeader() {
        return new PtrClassicDefaultHeader(mContext);
    }
}
