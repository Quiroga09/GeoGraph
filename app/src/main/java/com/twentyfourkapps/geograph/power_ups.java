package com.twentyfourkapps.geograph;

import android.content.Context;
import android.os.Bundle;
import android.view.View;


public class power_ups extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_ups);
    }

    public void home_pressed(View view){
        super.onBackPressed();
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }
}