package com.marno.mbasiclib.interfaces;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by marno on 2016/10/26/22:39.
 */

public interface IRefreshView extends PtrHandler {
    PtrUIHandler getRefreshHeader();

    PtrFrameLayout getPtrView();
}
