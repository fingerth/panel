package com.fingerth.panel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PanelLayout.OnPanelListener {

    @BindView(R.id.leftPanel1)
    PanelLayout leftPanel1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        leftPanel1.setOnPanelListener(this);
    }


    @Override
    public void onPanelClosed(PanelLayout panel) {
        //Toast.makeText(this, "Closed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPanelOpened(PanelLayout panel) {
        //Toast.makeText(this, "Opened", Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9, R.id.tv10, R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                break;
            case R.id.tv2:
            case R.id.tv3:
            case R.id.tv4:
            case R.id.tv5:
            case R.id.tv6:
            case R.id.tv7:
            case R.id.tv8:
            case R.id.tv9:
            case R.id.tv10:
                startActivity(new Intent(this, Main2Activity.class));
                //Toast.makeText(this, "666", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv1:
            case R.id.iv2:
            case R.id.iv3:
            case R.id.iv4:
            case R.id.iv5:
                //Toast.makeText(this, "555", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
