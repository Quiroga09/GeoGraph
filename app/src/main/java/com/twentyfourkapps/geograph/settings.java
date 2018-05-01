package com.twentyfourkapps.geograph;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;


public class settings extends BaseActivity implements MyEventClassListener {
    private String LANG_CURRENT = "kk";
    private int VIBRATION = 1;
    private SQLiteDatabase db;
    public SQLiteDatabase getDb() {
        return db;
    }
    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    // Purchase
    MyBilling myB = new MyBilling(this);
    private int isAdsDisabled;
    private int ads_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_settings);

        Button english = findViewById(R.id.english);
        Button espanol = findViewById(R.id.español);
        Button remove_ads = findViewById(R.id.remove_ads_button);
        ImageView ads_new_settings = findViewById(R.id.ads_new_settings);

        SwitchCompat vibration_switch = findViewById(R.id.vibration_id);



        if (VIBRATION == 0){
            vibration_switch.setChecked(false);
        }else{
            vibration_switch.setChecked(true);
        }

        if (LANG_CURRENT.equals("es")) {
            espanol.setSelected(true);
            english.setSelected(false);
            ads_new_settings.setImageDrawable(getResources().getDrawable(R.drawable.nuevo_settings));
        }
        if (LANG_CURRENT.equals("en")) {
            espanol.setSelected(false);
            english.setSelected(true);
            ads_new_settings.setImageDrawable(getResources().getDrawable(R.drawable.new_settings));
        }
        if(ads_new ==1){
            ads_new_settings.setVisibility(View.INVISIBLE);
        }


        if(isAdsDisabled == 0){
            remove_ads.setSelected(true);
            remove_ads.setClickable(true);
        }
        if(isAdsDisabled == 1){
            remove_ads.setSelected(false);
            remove_ads.setClickable(false);
        }

        myB.onCreate();

        vibration_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked) {
                    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    int vibrate_time = 100;
                    if(vib != null) {
                        vib.vibrate(vibrate_time);
                    }
                    changeVib(settings.this,1);
                }else {
                    changeVib(settings.this, 0);
                }
            }
        });
    }

    public void home_pressed(View view) {
        if (ads_new!=1) {
            ads_new = 1;
            adsNew(this);
        }
        myB.onDestroy();
        onBackPressed();
        Intent intent = new Intent(settings.this, MainActivity.class);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed(){
        if (ads_new!=1) {
            ads_new = 1;
            adsNew(this);
        }
        myB.onDestroy();
        Intent intent = new Intent(settings.this, MainActivity.class);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);

    }

    public void language_english(View view){
        if (LANG_CURRENT.equals("es")) {
            changeLang(settings.this, "en");
            Intent intent = new Intent(settings.this, settings.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }

    }
    public void language_espanol(View view){
        if (LANG_CURRENT.equals("en")) {
            changeLang(settings.this, "es");
            Intent intent = new Intent(settings.this, settings.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

    public void remove_ads(View view){
        if(isAdsDisabled == 0) {
            myB.purchaseRemoveAds();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    myB.addListener(settings.this);
                    myB.notifyListeners();
                }
            }, 500);
        }
    }
    public void somethingHappened(){
       if(myB.isAdsDisabled == 1){
           isAdsDisabled = 1;
           changeAds(settings.this);
       }
    }

    public void changeAds(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("isAdsDisabled", isAdsDisabled);
        editor.apply();
    }
    public void adsNew(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("ads_new", ads_new);
        editor.apply();
    }


    public void onClick(View v){

    }

    public void changeLang(Context context, String lang) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Language", lang);
        editor.apply();
    }

    public void changeVib(Context context, int vib) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Vibration", vib);
        editor.apply();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        LANG_CURRENT = preferences.getString("Language", "en");
        VIBRATION = preferences.getInt("Vibration",1);
        isAdsDisabled = preferences.getInt("isAdsDisabled",99);
        ads_new = preferences.getInt("ads_new",99);

        super.attachBaseContext(LocaleManager.setNewLocale(newBase,LANG_CURRENT));
    }

}