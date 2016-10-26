package com.marno.mbasiclib.basic;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.marno.easyutilcode.StackUtil;
import com.marno.easyutilcode.ToastUtil;
import com.marno.mbasiclib.widgets.TintStatusBar;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Marno on 2016/7/18/10:24.
 * All Activity base class
 */
public abstract class MBasicActivity extends RxActivity {

    protected String TAG = this.getClass().getSimpleName();

    protected Activity mContext;
    private Unbinder mUnbinder;
    protected int isFirstBack;
    protected boolean mIsFirstShow = true;

    @LayoutRes
    protected abstract int getLayout();

    @ColorInt
    protected int getStatusBarColor() {
        return 0;
    }

    /**
     * 是否开启透明状态栏
     */
    protected boolean isTransparentStatusBar() {
        return false;
    }

    /**
     * 是否开启滑动返回
     */
    protected boolean isNeedSwipeBack() {
        return false;
    }

    /**
     * 需要加载数据时重写此方法
     */
    protected void loadData() {
    }

    protected abstract void initView(Bundle savedInstanceState);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        StackUtil.getIns().push(this);

        if (!isTransparentStatusBar() && getStatusBarColor() != 0) {
            TintStatusBar.setStatusBarColor(this, getStatusBarColor());
        } else if (isTransparentStatusBar()) {
            TintStatusBar.translucentStatusBar(this);
        }

        if (isNeedSwipeBack()) {
            SwipeBackHelper.onCreate(this);
            SwipeBackHelper.getCurrentPage(this)//get current instance
                    .setSwipeBackEnable(true)//on-off
                    .setSwipeEdge(100)
                    .setSwipeSensitivity(0.15f)//sensitiveness of the gesture。0:slow  1:sensitive
                    .setSwipeRelateEnable(true)//if should move together with the following Activity
                    .setSwipeRelateOffset(500);//the Offset of following Activity when setSwipeRelateEnable(true)
//                .setDisallowInterceptTouchEvent(true)//your view can hand the events first.default false;
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (isNeedSwipeBack()) SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onResume() {
        if (mIsFirstShow) {
            mIsFirstShow = false;
            loadData();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isNeedSwipeBack()) SwipeBackHelper.onDestroy(this);
        StackUtil.getIns().pop(this);
        mUnbinder.unbind();
    }

    protected void quitApp() {
        if (isFirstBack == 0) {
            ToastUtil.show("再按一次退出程序");
            isFirstBack = 1;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isFirstBack = 0;
                }
            }, 1000);
        } else if (isFirstBack == 1) {
            StackUtil.getIns().popAll();
            finish();
            System.exit(0);
        }
    }
}
