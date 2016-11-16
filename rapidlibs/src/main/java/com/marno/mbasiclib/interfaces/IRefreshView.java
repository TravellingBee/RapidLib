package com.marno.mbasiclib.interfaces;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by marno on 2016/10/26/22:39.
 */

public interface IRefreshView extends PtrHandler {
    /**
     * 获取下拉刷新头布局，如果返回空将不会有下拉刷新动画
     * @return 下拉刷新头
     */
    PtrUIHandler getRefreshHeader();

    /**
     * 获取下拉刷新布局
     * @return 下拉刷新布局对象
     */
    PtrFrameLayout getPtrView();
}
