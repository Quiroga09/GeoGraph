package com.twentyfourkapps.geograph;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;


public class settings extends BaseActivity {
    private String LANG_CURRENT = "kk";
    private int VIBRATION = 1;
    private SQLiteDatabase db;
    public SQLiteDatabase getDb() {
        return db;
    }
    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_settings);

        Button english = findViewById(R.id.english);
        Button español = findViewById(R.id.español);
        SwitchCompat vibration_switch = findViewById(R.id.vibration_id);

        if (VIBRATION == 0){
            vibration_switch.setChecked(false);
        }else{
            vibration_switch.setChecked(true);
        }

        if (LANG_CURRENT.equals("es")) {
            español.setSelected(true);
            english.setSelected(false);
        }
        if (LANG_CURRENT.equals("en")) {
            español.setSelected(false);
            english.setSelected(true);
        }

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
        onBackPressed();
        Intent intent = new Intent(settings.this, MainActivity.class);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed(){
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
    public void language_español(View view){
        if (LANG_CURRENT.equals("en")) {
            changeLang(settings.this, "es");
            Intent intent = new Intent(settings.this, settings.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }

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


        super.attachBaseContext(LocaleManager.setNewLocale(newBase,LANG_CURRENT));
    }

}