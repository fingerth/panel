package com.fingerth.panel.app;

import android.support.multidex.MultiDexApplication;

/**
 * ======================================================
 * Created by Administrator able_fingerth on 2017/12/8.
 * <p/>
 * 版权所有，违者必究！
 * <详情描述/>
 */
public class PanelApplication extends MultiDexApplication {

    private static PanelApplication instances;


    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
    }

    public static PanelApplication getInstances() {
        return instances;
    }



}
