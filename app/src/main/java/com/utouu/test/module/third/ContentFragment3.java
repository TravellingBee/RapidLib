package com.utouu.test.module.third;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.marno.mbasiclib.basic.BasicFragment;
import com.utouu.test.R;

import butterknife.BindView;


/**
 * Created by marno on 2016/8/23/15:20.
 */
public class ContentFragment3 extends BasicFragment {
    @BindView(R.id.rlayout) RelativeLayout mRlayout;

    public static ContentFragment3 newIns() {
        return new ContentFragment3();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRlayout.setBackgroundColor(Color.parseColor("#c866e9"));
    }

    @Override
    protected void loadData() {

    }

}
