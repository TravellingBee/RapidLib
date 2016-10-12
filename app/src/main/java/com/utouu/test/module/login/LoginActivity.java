package com.utouu.test.module.login;

import android.graphics.Color;
import android.os.Bundle;

import com.utouu.test.R;
import com.utouu.test.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected int getStatusBarColor() {
        return Color.parseColor("#101013");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


}
