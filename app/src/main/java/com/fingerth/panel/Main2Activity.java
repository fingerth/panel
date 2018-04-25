package com.fingerth.panel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.panelContent)
    LinearLayout panelContent;
    @BindView(R.id.panelHandle)
    Button panelHandle;
    @BindView(R.id.panelLayout)
    LinearLayout panelLayout;

    private int panelContentWith = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        panelContentWith = StaticUtils.getSysWidth(this) - StaticUtils.dp2px(this, 40);

    }
}
