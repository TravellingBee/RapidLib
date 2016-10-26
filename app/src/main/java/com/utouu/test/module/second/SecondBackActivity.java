package com.utouu.test.module.second;

import android.os.Bundle;
import android.view.View;

import com.marno.easyutilcode.IntentUtil;
import com.marno.mbasiclib.basic.MBasicActivity;
import com.utouu.test.R;

/**
 * Created by marno on 2016/8/23/15:20.
 * 支持滑动返回的Activity，需要在清单中设置透明主题
 */
public class SecondBackActivity extends MBasicActivity {

    //重写此方法开启滑动返回
    @Override
    protected boolean isNeedSwipeBack() {
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_second;
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    public void onClick(View view) {
        IntentUtil.to(this, SecondBackActivity.class);
    }

}
