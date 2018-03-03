package com.twentyfourkapps.geograph;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.twentyfourkapps.geograph.data.PlayerContract;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class finish extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private SQLiteDatabase db;
    public void setDb(SQLiteDatabase db){this.db = db;}
    public int ad_count;
    public int game_mode;
    public ArrayList<Integer> achievement;
    public int game_difficulty;
    public long score;
    private String LANG_CURRENT ="en";
    private  GoogleSignInAccount signedInAccount;
    int user_logged_in=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        game_mode = getIntent().getIntExtra("game_mode",0);
        score = getIntent().getLongExtra("score",0);
        game_difficulty = getIntent().getIntExtra("game_difficulty",0);
        achievement = getIntent().getIntegerArrayListExtra("achievement");
        ad_count = getIntent().getIntExtra("ad_count",0);

        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signedInAccount != null){
            RelativeLayout rel = findViewById(R.id.rel_finish);
            Context con = rel.getContext();
            GamesClient gamesClient = Games.getGamesClient(con, signedInAccount);
            gamesClient.setViewForPopups(this.findViewById(R.id.rel_finish));
            gamesClient.setGravityForPopups(Gravity.CENTER_HORIZONTAL);
            user_logged_in = 1;
        }else{
            user_logged_in = 0;
        }

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        TextView score_text =  findViewById(R.id.score);
        TextView points_text =  findViewById(R.id.points);
        Button button_back =  findViewById(R.id.play_button);

      /*  AdView mAdView;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("646C33D6AA1F9A8EE024EA44C5D34086").build();
        mAdView.loadAd(adRequest);*/


        switch (game_mode){
            case 3:
                points_text.setTextColor(getResources().getColor(R.color.green_diff));
                button_back.setBackgroundColor(getResources().getColor(R.color.green_diff));
                break;
            case 0:
                points_text.setTextColor(getResources().getColor(R.color.yellow_diff));
                button_back.setBackgroundColor(getResources().getColor(R.color.yellow_diff));
                break;
            case 1:
                points_text.setTextColor(getResources().getColor(R.color.blue_diff));
                button_back.setBackgroundColor(getResources().getColor(R.color.blue_diff));
                break;
            case 2:
                points_text.setTextColor(getResources().getColor(R.color.dark_pink));
                button_back.setBackgroundColor(getResources().getColor(R.color.dark_pink));
                break;

        }


        getDatabase();
        updateScore();
        score_text.setText((String.valueOf(score)));

        display_ad();
        ad_count++;

        if(achievement != null) {
            if (achievement.size() > 0 && signedInAccount != null) {
                for (int i = 0; i < achievement.size(); i++) {
                    unlock_achievement(achievement.get(i));
                }
                unlock_achievement_inc(R.string.achievement_rookie);
                unlock_achievement_inc(R.string.achievement_gamer);
                unlock_achievement_inc(R.string.achievement_addict);
            }
        }

    }


    public void unlock_achievement(int ach){
        final int un_ach = ach;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Games.getAchievementsClient(finish.this, signedInAccount)
                        .unlock(getString(un_ach));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms

                    }
                }, 1500);
            }
        });
    }

    public void unlock_achievement_inc(int ach){
        final int un_ach = ach;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Games.getAchievementsClient(finish.this,signedInAccount)
                        .increment(getString(un_ach), 1);
            }
        });
    }



    public void display_ad(){
        final AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        adRequestBuilder.addTestDevice("646C33D6AA1F9A8EE024EA44C5D34086");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if(ad_count >= 3) {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                        ad_count =0;
                    }
                }
            }
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(adRequestBuilder.build());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i3 = new Intent(finish.this, MainActivity.class);
        i3.putExtra("ad_count",ad_count);
        i3.putExtra("game_mode", game_mode);
        i3.putExtra("game_difficulty", game_difficulty);
        i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i3);
        finish();
    }

    public void getDatabase(){
        CountriesDbHelper countries_db = new CountriesDbHelper(this);
        setDb(countries_db.getWritableDatabase());

    }
    public String getBestScore(){
        Cursor best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_0_hard +" FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1" ,  null);
        switch (game_mode){
            case 3:
                best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_3_hard +" FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1" ,  null);
                break;
            case 0:
                best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_0_hard +" FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1" ,  null);
                break;
            case 1:
                best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_1_hard +" FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1" ,  null);
                break;
            case 2:
                best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_2_hard +" FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1" ,  null);
                break;

        }


        best.moveToFirst();
        String ret =  best.getString(0);
        best.close();
        return ret;
    }

    public void restart(View view){

        //  overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up );

        Intent i3 = new Intent(finish.this, start.class);
        i3.putExtra("ad_count",ad_count);
        i3.putExtra("game_mode", game_mode);
        i3.putExtra("game_difficulty", game_difficulty);
        i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i3);
        finish();

    }

    public void home_pressed_finish(View view){
        Intent i3 = new Intent(finish.this, MainActivity.class);
        i3.putExtra("ad_count",ad_count);
        i3.putExtra("game_mode", game_mode);
        i3.putExtra("game_difficulty", game_difficulty);
        i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i3);
        finish();
    }

    public void updateScore(){
        String bestScore = getBestScore();
        if(score > parseInt(bestScore)){
            ContentValues initialValues = new ContentValues();
            initialValues.put("id", 1); // the execution is different if _id is 2

            switch (game_mode) {
                case 3:
                    switch (game_difficulty) {
                        case 0:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_3_easy, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_country__shape_easy), score);
                            }
                            break;
                        case 1:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_3_medium, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_country__shape_medium), score);
                            }
                            break;
                        case 2:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_3_hard, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_country__shape_hard), score);
                            }
                            break;

                    }

                    break;
                case 0:
                    switch (game_difficulty) {
                        case 0:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_0_easy, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_flag__country_easy), score);
                            }
                            break;
                        case 1:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_0_medium, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_flag__country_medium), score);
                            }
                            break;
                        case 2:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_0_hard, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_flag__country_hard), score);
                            }
                            break;

                    }
                    break;
                case 1:
                    switch (game_difficulty) {
                        case 0:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_1_easy, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_country__flag_easy), score);
                            }

                            break;
                        case 1:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_1_medium, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_country__flag_medium), score);
                            }

                            break;
                        case 2:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_1_hard, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_country__flag_hard), score);
                            }

                            break;

                    }
                    break;
                case 2:
                    switch (game_difficulty) {
                        case 0:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_2_easy, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_capitals_easy), score);
                            }

                            break;
                        case 1:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_2_medium, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_capitals_medium), score);
                            }

                            break;
                        case 2:
                            initialValues.put(PlayerContract.PlayerEntry.BEST_SCORE_2_hard, score);
                            if(signedInAccount != null) {
                                Games.getLeaderboardsClient(this, signedInAccount)
                                        .submitScore(getString(R.string.leaderboard_capitals_hard), score);
                            }

                            break;
                    }
                    break;
            }


            initialValues.put(PlayerContract.PlayerEntry.PLAYER_NAME, "a");

            int id = (int) db.insertWithOnConflict(PlayerContract.PlayerEntry.TABLE_NAME, null, initialValues, SQLiteDatabase.CONFLICT_IGNORE);
            if (id == -1) {
                db.update(PlayerContract.PlayerEntry.TABLE_NAME, initialValues, "_id=?", new String[] {"1"});  // number 1 is the _id here, update to variable for your code
            }

        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        LANG_CURRENT = preferences.getString("Language", "en");

        super.attachBaseContext(LocaleManager.setNewLocale(newBase,LANG_CURRENT));
    }

}
