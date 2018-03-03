package com.twentyfourkapps.geograph;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.twentyfourkapps.geograph.data.ActivitySwipeDetector;
import com.twentyfourkapps.geograph.data.SwipeInterface;

public class MainActivity extends AppCompatActivity implements SwipeInterface  {
    private Boolean exit = false;
    public int game_mode = 0;
    public int game_diff = 0;
    public int practice_mode = 0;
    public int ad_count=0;
    private GoogleSignInAccount signedInAccount;
    private static final int RC_ACHIEVEMENT_UI = 9003;
    private int user_logged_in = 0;

    private Boolean medium_flagCountry = false;
    private Boolean medium_countryFlag= false;
    private Boolean medium_capitals= false;
    private Boolean medium_countryShape= false;
    private Boolean hard_flagCountry= false;
    private Boolean hard_countryFlag= false;
    private Boolean hard_capitals= false;
    private Boolean hard_countryShape= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AdView mAdView;
        FirebaseAnalytics mFirebaseAnalytics;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_white);

        AppRater.app_launched(this);

        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signedInAccount != null) {
            user_logged_in = 1;
        }else {
            user_logged_in = 0;
        }

        ImageButton log_button = findViewById(R.id.log_icon);
        if(user_logged_in==1){
            log_button.setImageDrawable(getResources().getDrawable(R.drawable.logout));
        }else{
            log_button.setImageDrawable(getResources().getDrawable(R.drawable.login));
        }

        //Use custom font for a TEXTVIEW
        String customFont = "Baloo-Regular.ttf";
        Typeface typeface = Typeface.createFromAsset(getAssets(), customFont);
        TextView textView =  findViewById(R.id.game_mode_title);
        textView.setTypeface(typeface);

        //Swipe detecting
        ActivitySwipeDetector swipe = new ActivitySwipeDetector(this);
        RelativeLayout swipe_layout = findViewById(R.id.swipe_layout);
        swipe_layout.setOnTouchListener(swipe);

        if(getIntent().hasExtra("game_mode")){
            game_mode = getIntent().getIntExtra("game_mode",0);
        }
        if(getIntent().hasExtra("game_difficulty")) {
            game_diff =getIntent().getIntExtra("game_difficulty",0);
        }
        if(getIntent().hasExtra("ad_count")) {
            ad_count =getIntent().getIntExtra("ad_count",0);
        }
        setGame_mode();

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
        mAdView =  findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("646C33D6AA1F9A8EE024EA44C5D34086")  // My Galaxy Nexus test phone
                .build();
        adRequest.isTestDevice(this);
        mAdView.loadAd(adRequest);

        switch (game_diff){
            case 0:
                diff(findViewById(R.id.easy));
                break;
            case 1:
                diff(findViewById(R.id.medium));
                break;
            case 2:
                diff(findViewById(R.id.hard));
                break;
        }

    }

    public void diff(View v) {
        Button easy_button = findViewById(R.id.easy);
        Button medium_button = findViewById(R.id.medium);
        Button hard_button = findViewById(R.id.hard);
        switch(v.getId()) {
            case R.id.easy:
                easy_button.setSelected(true);
                medium_button.setSelected(false);
                hard_button.setSelected(false);
                easy_button.getBackground().setAlpha(255);
                medium_button.getBackground().setAlpha(211);
                hard_button.getBackground().setAlpha(211);
                medium_button.setTextColor(getResources().getColor(R.color.white));
                hard_button.setTextColor(getResources().getColor(R.color.white));
                game_diff=0;
                switch (game_mode) {
                    case 0:
                        easy_button.setTextColor(getResources().getColor(R.color.yellow_diff));
                        break;
                    case 1:
                        easy_button.setTextColor(getResources().getColor(R.color.blue_diff));
                        break;
                    case 2:
                        easy_button.setTextColor(getResources().getColor(R.color.pink_dark));
                        break;
                    case 3:
                        easy_button.setTextColor(getResources().getColor(R.color.green_diff));
                        break;
                }
                break;
            case R.id.medium:
                switch (game_mode) {
                    case 0:
                        if(!medium_flagCountry){
                            alertdialog();
                        }else{
                            game_diff=1;
                            easy_button.setSelected(false);
                            medium_button.setSelected(true);
                            hard_button.setSelected(false);
                            easy_button.getBackground().setAlpha(211);
                            medium_button.getBackground().setAlpha(255);
                            hard_button.getBackground().setAlpha(211);
                            easy_button.setTextColor(getResources().getColor(R.color.white));
                            hard_button.setTextColor(getResources().getColor(R.color.white));
                            medium_button.setTextColor(getResources().getColor(R.color.yellow_diff));
                        }
                        break;
                    case 1:
                        if(!medium_countryFlag){
                            alertdialog();
                        }else{
                            game_diff=1;
                            easy_button.setSelected(false);
                            medium_button.setSelected(true);
                            hard_button.setSelected(false);
                            easy_button.getBackground().setAlpha(211);
                            medium_button.getBackground().setAlpha(255);
                            hard_button.getBackground().setAlpha(211);
                            easy_button.setTextColor(getResources().getColor(R.color.white));
                            hard_button.setTextColor(getResources().getColor(R.color.white));
                            medium_button.setTextColor(getResources().getColor(R.color.blue_diff));
                        }
                        break;
                    case 2:
                        if(!medium_capitals){
                            alertdialog();
                        }else{
                            game_diff=1;
                            easy_button.setSelected(false);
                            medium_button.setSelected(true);
                            hard_button.setSelected(false);
                            easy_button.getBackground().setAlpha(211);
                            medium_button.getBackground().setAlpha(255);
                            hard_button.getBackground().setAlpha(211);
                            easy_button.setTextColor(getResources().getColor(R.color.white));
                            hard_button.setTextColor(getResources().getColor(R.color.white));
                            medium_button.setTextColor(getResources().getColor(R.color.pink_dark));
                        }
                        break;
                    case 3:
                        if(!medium_countryShape){
                            alertdialog();
                        }else{
                            game_diff=1;
                            easy_button.setSelected(false);
                            medium_button.setSelected(true);
                            hard_button.setSelected(false);
                            easy_button.getBackground().setAlpha(211);
                            medium_button.getBackground().setAlpha(255);
                            hard_button.getBackground().setAlpha(211);
                            easy_button.setTextColor(getResources().getColor(R.color.white));
                            hard_button.setTextColor(getResources().getColor(R.color.white));
                            medium_button.setTextColor(getResources().getColor(R.color.green_diff));
                        }
                        break;
                }
                break;
            case R.id.hard:
                switch (game_mode) {
                    case 0:
                        if(!hard_flagCountry){
                            alertdialog();
                        }else{
                            game_diff=2;
                            easy_button.setSelected(false);
                            medium_button.setSelected(false);
                            hard_button.setSelected(true);
                            easy_button.getBackground().setAlpha(211);
                            medium_button.getBackground().setAlpha(211);
                            hard_button.getBackground().setAlpha(255);
                            easy_button.setTextColor(getResources().getColor(R.color.white));
                            medium_button.setTextColor(getResources().getColor(R.color.white));
                            hard_button.setTextColor(getResources().getColor(R.color.yellow_diff));
                        }
                        break;
                    case 1:

                        if(!hard_countryFlag){
                            alertdialog();
                        }else{
                            game_diff=2;
                            easy_button.setSelected(false);
                            medium_button.setSelected(false);
                            hard_button.setSelected(true);
                            easy_button.getBackground().setAlpha(211);
                            medium_button.getBackground().setAlpha(211);
                            hard_button.getBackground().setAlpha(255);
                            easy_button.setTextColor(getResources().getColor(R.color.white));
                            medium_button.setTextColor(getResources().getColor(R.color.white));
                            hard_button.setTextColor(getResources().getColor(R.color.blue_diff));
                        }
                        break;
                    case 2:
                        if(!hard_capitals){
                            alertdialog();
                        }else{
                            game_diff=2;
                            easy_button.setSelected(false);
                            medium_button.setSelected(false);
                            hard_button.setSelected(true);
                            easy_button.getBackground().setAlpha(211);
                            medium_button.getBackground().setAlpha(211);
                            hard_button.getBackground().setAlpha(255);
                            easy_button.setTextColor(getResources().getColor(R.color.white));
                            medium_button.setTextColor(getResources().getColor(R.color.white));
                            hard_button.setTextColor(getResources().getColor(R.color.pink_dark));
                        }
                        break;
                    case 3:
                        if(!hard_countryShape){
                            alertdialog();
                        }else{
                            game_diff=2; easy_button.setSelected(false);
                            medium_button.setSelected(false);
                            hard_button.setSelected(true);
                            easy_button.getBackground().setAlpha(211);
                            medium_button.getBackground().setAlpha(211);
                            hard_button.getBackground().setAlpha(255);
                            easy_button.setTextColor(getResources().getColor(R.color.white));
                            medium_button.setTextColor(getResources().getColor(R.color.white));
                            hard_button.setTextColor(getResources().getColor(R.color.green_diff));
                        }
                        break;
                }
                break;
        }
    }


    public void setGame_mode(){
        TextView mode_title;
        Button play_button;
        ImageView background_main;

        Button easy_button = findViewById(R.id.easy);
        Button medium_button = findViewById(R.id.medium);
        Button hard_button = findViewById(R.id.hard);

        if(game_mode >3){
            game_mode = 0;
        }
        if(game_mode<0){
            game_mode = 3;
        }

        game_diff=0;
        diff(findViewById(R.id.easy));

        background_main = findViewById(R.id.background_main);
        play_button = findViewById(R.id.play_button);
        switch (game_mode){
            case 3:
                mode_title =  findViewById(R.id.game_mode_title);
                mode_title.setText(getResources().getString(R.string.game0_title));

                background_main.setImageDrawable(getResources().getDrawable(R.drawable.green));
                play_button.setBackgroundColor(getResources().getColor(R.color.green_play));

                switch(game_diff){
                    case 0:
                        easy_button.setTextColor(getResources().getColor(R.color.green_diff));
                        break;
                    case 1:
                        medium_button.setTextColor(getResources().getColor(R.color.green_diff));
                        break;
                    case 2:
                        hard_button.setTextColor(getResources().getColor(R.color.green_diff));
                        break;
                }

                if(!medium_countryShape){
                    medium_button.setActivated(false);
                    medium_button.setAlpha(0.5f);
                    medium_button.setTypeface(medium_button.getTypeface(),Typeface.BOLD_ITALIC);
                }else{
                    medium_button.setActivated(true);
                    medium_button.setAlpha(1);
                    medium_button.setTypeface(medium_button.getTypeface(),Typeface.BOLD);
                }
                if(!hard_countryShape){
                    hard_button.setActivated(false);
                    hard_button.setAlpha(0.5f);
                    hard_button.setTypeface(hard_button.getTypeface(),Typeface.BOLD_ITALIC);
                }else{
                    hard_button.setActivated(true);
                    hard_button.setAlpha(1);
                    hard_button.setTypeface(hard_button.getTypeface(),Typeface.BOLD);
                }

                break;

            case 0:
                mode_title = findViewById(R.id.game_mode_title);
                mode_title.setText(getResources().getString(R.string.game1_title));

                background_main.setImageDrawable(getResources().getDrawable(R.drawable.yellow));
                play_button.setBackgroundColor(getResources().getColor(R.color.yellow_button));

                switch(game_diff){
                    case 0:
                        easy_button.setTextColor(getResources().getColor(R.color.yellow_diff));
                        break;
                    case 1:
                        medium_button.setTextColor(getResources().getColor(R.color.yellow_diff));
                        break;
                    case 2:
                        hard_button.setTextColor(getResources().getColor(R.color.yellow_diff));
                        break;
                }

                if(!medium_flagCountry){
                    medium_button.setActivated(false);
                    medium_button.setAlpha(0.5f);
                    medium_button.setTypeface(medium_button.getTypeface(),Typeface.BOLD_ITALIC);
                }else{
                    medium_button.setActivated(true);
                    medium_button.setAlpha(1);
                    medium_button.setTypeface(medium_button.getTypeface(),Typeface.BOLD);
                }
                if(!hard_flagCountry){
                    hard_button.setActivated(false);
                    hard_button.setAlpha(0.5f);
                    hard_button.setTypeface(hard_button.getTypeface(),Typeface.BOLD_ITALIC);
                }else{
                    hard_button.setActivated(true);
                    hard_button.setAlpha(1);
                    hard_button.setTypeface(hard_button.getTypeface(),Typeface.BOLD);

                }
                break;

            case 1:
                mode_title =  findViewById(R.id.game_mode_title);
                mode_title.setText(getResources().getString(R.string.game2_title));

                background_main.setImageDrawable(getResources().getDrawable(R.drawable.blue));
                play_button.setBackgroundColor(getResources().getColor(R.color.blue_button));

                switch(game_diff){
                    case 0:
                        easy_button.setTextColor(getResources().getColor(R.color.blue_diff));
                        break;
                    case 1:
                        medium_button.setTextColor(getResources().getColor(R.color.blue_diff));
                        break;
                    case 2:
                        hard_button.setTextColor(getResources().getColor(R.color.blue_diff));
                        break;
                }
                if(!medium_countryFlag){
                    medium_button.setActivated(false);
                    medium_button.setAlpha(0.5f);
                    medium_button.setTypeface(medium_button.getTypeface(),Typeface.BOLD_ITALIC);
                }else{
                    medium_button.setActivated(true);
                    medium_button.setAlpha(1);
                    medium_button.setTypeface(medium_button.getTypeface(),Typeface.BOLD);
                }
                if(!hard_countryFlag){
                    hard_button.setActivated(false);
                    hard_button.setAlpha(0.5f);
                    hard_button.setTypeface(hard_button.getTypeface(),Typeface.BOLD_ITALIC);
                }else{
                    hard_button.setActivated(true);
                    hard_button.setAlpha(1);
                    hard_button.setTypeface(hard_button.getTypeface(),Typeface.BOLD);
                }
                break;

            case 2:
                mode_title =  findViewById(R.id.game_mode_title);
                mode_title.setText(getResources().getString(R.string.game3_title));

                background_main.setImageDrawable(getResources().getDrawable(R.drawable.pink));
                play_button.setBackgroundColor(getResources().getColor(R.color.dark_red_button));

                switch(game_diff){
                    case 0:
                        easy_button.setTextColor(getResources().getColor(R.color.pink_dark));
                        break;
                    case 1:
                        medium_button.setTextColor(getResources().getColor(R.color.pink_dark));
                        break;
                    case 2:
                        hard_button.setTextColor(getResources().getColor(R.color.pink_dark));
                        break;
                }

                if(!medium_capitals){
                    medium_button.setActivated(false);
                    medium_button.setAlpha(0.5f);
                    medium_button.setTypeface(medium_button.getTypeface(),Typeface.BOLD_ITALIC);
                }else{
                    medium_button.setActivated(true);
                    medium_button.setAlpha(1);
                    medium_button.setTypeface(medium_button.getTypeface(),Typeface.BOLD);
                }
                if(!hard_capitals){
                    hard_button.setActivated(false);
                    hard_button.setAlpha(0.5f);
                    hard_button.setTypeface(hard_button.getTypeface(),Typeface.BOLD_ITALIC);
                }else{
                    hard_button.setActivated(true);
                    hard_button.setAlpha(1);
                    hard_button.setTypeface(hard_button.getTypeface(),Typeface.BOLD);
                }
                break;
            default:
        }

    }

    public void log(View view){
        if(user_logged_in == 1){
            signOut(view);
        }
        else{
            Intent intent = new Intent(MainActivity.this, Init.class);
            startActivity(intent);
        }

    }

    public void showAchievements(View view) {
        if(user_logged_in == 0) {
            Intent intent = new Intent(MainActivity.this, achievements.class);
            startActivity(intent);
            overridePendingTransition(R.anim.popupin, R.anim.popupout);
        }else{
            Games.getAchievementsClient(this, signedInAccount)
                    .getAchievementsIntent()
                    .addOnSuccessListener(new OnSuccessListener<Intent>() {
                        @Override
                        public void onSuccess(Intent intent) {
                            startActivityForResult(intent, RC_ACHIEVEMENT_UI);
                        }
                    });
        }
    }

    public void settings(View view){
        Intent intent = new Intent(MainActivity.this, settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.popupin, R.anim.popupout);
    }

    public void signOut(View view) {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        signInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // at this point, the user is signed out.
                        Intent intent = new Intent(MainActivity.this, Init.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    /** Called when the user clicks the PLAY button */
    public void start(View view) {
        // Do something in response to button

        Intent intent = new Intent(MainActivity.this, start.class);
        intent.putExtra("game_mode", game_mode);
        intent.putExtra("ad_count",ad_count);
        intent.putExtra("practice_mode",practice_mode);
        intent.putExtra("game_difficulty", game_diff);
        startActivity(intent);
        //finish();
    }

    public void practice(View view){
        practice_mode=1;
        start(view);
    }

    /** Called when the user clicks the Send button */
    public void left(View view) {
        // Do something in response to button
        game_mode--;
        setGame_mode();
    }

    /** Called when the user clicks the Send button */
    public void right(View view) {
        // Do something in response to button
        game_mode++;
        setGame_mode();
    }

    public void scores(View view){
        Intent intent = new Intent(MainActivity.this, scores.class);
        startActivity(intent);
        overridePendingTransition(R.anim.popupin, R.anim.popupout);

    }
    public void powerups(View view){
        Intent intent = new Intent(MainActivity.this, power_ups.class);
        startActivity(intent);
        overridePendingTransition(R.anim.popupin, R.anim.popupout);
    }

    public void home_pressed(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        String LANG_CURRENT ="en";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        LANG_CURRENT = preferences.getString("Language", "en");

        medium_flagCountry = preferences.getBoolean("medium_flagCountry", false);
        medium_countryFlag= preferences.getBoolean("medium_countryFlag", false);
        medium_capitals=  preferences.getBoolean("medium_capitals", false);
        medium_countryShape= preferences.getBoolean("medium_countryShape", false);
        hard_flagCountry= preferences.getBoolean("hard_flagCountry", false);
        hard_countryFlag= preferences.getBoolean("hard_countryFlag", false);
        hard_capitals= preferences.getBoolean("hard_capitals", false);
        hard_countryShape= preferences.getBoolean("hard_countryShape", false);

        super.attachBaseContext(LocaleManager.setNewLocale(newBase,LANG_CURRENT));
    }


    @Override
    public void onBackPressed() {
        if (exit) {
            // finish activity
            Intent myintent = new Intent(Intent.ACTION_MAIN);
            myintent.addCategory(Intent.CATEGORY_HOME);
            myintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(myintent);
            finish();
            System.exit(0);
        } else {
            Toast.makeText(this, R.string.backpressed_main,
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2 * 1000);

        }

    }

    public void alertdialog() {
        if(user_logged_in==1){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.locked)
                    .setCancelable(true);

            AlertDialog alert = builder.create();
            alert.show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.locked_nologed)
                    .setCancelable(true)
                    .setPositiveButton(R.string.sign_in, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                            Intent intent = new Intent(MainActivity.this,  Init.class);
                            startActivity(intent);
                            finish();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }

        //   game_diff = 0;
        // diff(findViewById(R.id.easy));
    }

    @Override
    public void left2right(View v) {
        switch(v.getId()){
            case R.id.swipe_layout:
                game_mode--;
                setGame_mode();
                break;
        }
    }

    @Override
    public void right2left(View v) {
        switch(v.getId()){
            case R.id.swipe_layout:
                game_mode++;
                setGame_mode();
                break;
        }
    }

    @Override
    public void top2bottom(View v) {
        switch(v.getId()){
            case R.id.swipe_layout:
                // do your stuff here
                break;
        }
    }

    @Override
    public void bottom2top(View v) {
        switch(v.getId()){
            case R.id.swipe_layout:
                // do your stuff here
                break;
        }
    }

}