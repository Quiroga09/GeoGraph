package com.twentyfourkapps.geograph;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.twentyfourkapps.geograph.data.PlayerContract;

public class Init extends AppCompatActivity implements View.OnClickListener{

    private static final int RC_SIGN_IN = 9000;
    private String LANG_CURRENT ="en";
    private SQLiteDatabase db;
    public void setDb(SQLiteDatabase db){this.db = db;}

    private Boolean medium_flagCountry = false;
    private Boolean medium_countryFlag= false;
    private Boolean medium_capitals= false;
    private Boolean medium_countryShape= false;
    private Boolean hard_flagCountry= false;
    private Boolean hard_countryFlag= false;
    private Boolean hard_capitals= false;
    private Boolean hard_countryShape= false;
    private GoogleSignInAccount signedInAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_init);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.no_signin).setOnClickListener(this);

        getDatabase();
        Cursor login = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.NO_LOGIN +" FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id = 1" ,  null);
        login.moveToFirst();
        String ret =  login.getString(0);
        login.close();

        RelativeLayout rel = findViewById(R.id.rel_init);
        Context con = rel.getContext();
        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signedInAccount != null) {
            GamesClient gamesClient = Games.getGamesClient(con, signedInAccount);
            gamesClient.setViewForPopups(findViewById(R.id.popup));
            Intent intent = new Intent(Init.this,  MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                startSignInIntent();
                break;
            case R.id.no_signin:
                alertdialog();
                break;
        }
    }

    public void getDatabase(){
        CountriesDbHelper countries_db = new CountriesDbHelper(this);
        setDb(countries_db.getWritableDatabase());
    }

    public void alertdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.login_popup)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setPositiveButton(R.string.OK_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        ContentValues initialValues = new ContentValues();
                        initialValues.put("id", 1); // the execution is different if _id is 2
                        initialValues.put(PlayerContract.PlayerEntry.NO_LOGIN,"1");

                        int id1 = (int) db.insertWithOnConflict(PlayerContract.PlayerEntry.TABLE_NAME, null, initialValues, SQLiteDatabase.CONFLICT_IGNORE);
                        if (id1 == -1) {
                            db.update(PlayerContract.PlayerEntry.TABLE_NAME, initialValues, "_id=?", new String[] {"1"});  // number 1 is the _id here, update to variable for your code
                        }

                        Intent intent = new Intent(Init.this,  MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    private void startSignInIntent() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // The signed in account is stored in the result.
                signedInAccount = result.getSignInAccount();
                if (signedInAccount != null) {
                    achievement_local_to_google();
                    achievement_status_to_shared_preferences();
                    RelativeLayout rel = findViewById(R.id.rel_init);
                    Context con = rel.getContext();
                    GamesClient gamesClient = Games.getGamesClient(con,signedInAccount);
                    gamesClient.setViewForPopups(findViewById(R.id.rel_init));
                    update_scores();
                }

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        Intent intent = new Intent(Init.this,  MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1500);

            } else {
                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    message = getString(R.string.signin_other_error);
                }
                new AlertDialog.Builder(this).setMessage(message)
                        .setNeutralButton(android.R.string.ok, null).show();
            }
        }
    }

    public void achievement_local_to_google(){
        if(medium_flagCountry){
            unlock_achievement(R.string.achievement_medium_unlocked_flagcountry);
        }
        if(medium_countryFlag){
            unlock_achievement(R.string.achievement_medium_unlocked_countryflag);
        }
        if(medium_capitals){
            unlock_achievement(R.string.achievement_medium_unlocked_capitals);
        }
        if(medium_countryShape){
            unlock_achievement(R.string.achievement_medium_unlocked_countryshape);
        }
        if(hard_flagCountry){
            unlock_achievement(R.string.achievement_hard_unlocked_flagcountry);
        }
        if(hard_countryFlag){
            unlock_achievement(R.string.achievement_hard_unlocked_countryflag);
        }
        if(hard_capitals){
            unlock_achievement(R.string.achievement_hard_unlocked_capitals);
        }
        if(hard_countryShape){
            unlock_achievement(R.string.achievement_hard_unlocked_countryshape);
        }

    }

    public void unlock_achievement(int ach){
        final int un_ach = ach;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Games.getAchievementsClient(Init.this, signedInAccount)
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

    public void achievement_status_to_shared_preferences() {
        Games.getAchievementsClient(this, signedInAccount).load(false).addOnSuccessListener(new OnSuccessListener<AnnotatedData<AchievementBuffer>>() {
            @Override
            public void onSuccess(AnnotatedData<AchievementBuffer> achievementBufferAnnotatedData) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Init.this);
                SharedPreferences.Editor editor = preferences.edit();
                AchievementBuffer buffer = achievementBufferAnnotatedData.get();
                if (buffer != null) {
                    for (Achievement elem : buffer) {
                        String achiv = elem.getAchievementId();
                        if (achiv.equals(getResources().getString(R.string.achievement_medium_unlocked_flagcountry))) {
                            if(elem.getState() == Achievement.STATE_UNLOCKED) {
                                medium_flagCountry = true;
                            }
                            editor.putBoolean("medium_flagCountry", medium_flagCountry);
                            editor.apply();
                        } else if (achiv.equals(getResources().getString(R.string.achievement_medium_unlocked_countryflag))) {
                            if (elem.getState() == Achievement.STATE_UNLOCKED) {
                                medium_countryFlag = true;
                            }
                            editor.putBoolean("medium_countryFlag", medium_countryFlag);
                            editor.apply();
                        } else if (achiv.equals(getResources().getString(R.string.achievement_medium_unlocked_capitals))) {
                            if (elem.getState() == Achievement.STATE_UNLOCKED) {
                                medium_capitals = true;
                            }
                            editor.putBoolean("medium_capitals", medium_capitals);
                            editor.apply();
                        } else if (achiv.equals(getResources().getString(R.string.achievement_medium_unlocked_countryshape))) {
                            if (elem.getState() == Achievement.STATE_UNLOCKED) {
                                medium_countryShape = true;
                            }
                            editor.putBoolean("medium_countryShape", medium_countryShape);
                            editor.apply();
                        } else if (achiv.equals(getResources().getString(R.string.achievement_hard_unlocked_flagcountry))) {
                            if (elem.getState() == Achievement.STATE_UNLOCKED) {
                                hard_flagCountry = true;
                            }
                            editor.putBoolean("hard_flagCountry", hard_flagCountry);
                            editor.apply();
                        } else if (achiv.equals(getResources().getString(R.string.achievement_hard_unlocked_countryflag))) {
                            if (elem.getState() == Achievement.STATE_UNLOCKED) {
                                hard_countryFlag = true;
                            }
                            editor.putBoolean("hard_countryFlag", hard_countryFlag);
                            editor.apply();
                        } else if (achiv.equals(getResources().getString(R.string.achievement_hard_unlocked_capitals))) {
                            if (elem.getState() == Achievement.STATE_UNLOCKED) {
                                hard_capitals = true;
                            }
                            editor.putBoolean("hard_capitals", hard_capitals);
                            editor.apply();
                        } else if (achiv.equals(getResources().getString(R.string.achievement_hard_unlocked_countryshape))) {
                            if (elem.getState() == Achievement.STATE_UNLOCKED) {
                                hard_countryShape = true;
                            }
                            editor.putBoolean("hard_countryShape", hard_countryShape);
                            editor.apply();
                        }
                    }
                    buffer.release();
                }

            }
        });
    }

    public void update_scores() {
        Cursor best;
        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signedInAccount != null){
            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_0_hard + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String hard_0 =  best.getString(0);

            if(!hard_0.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_flag__country_hard), Integer.parseInt(hard_0));
            }

            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_1_hard + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String hard_1 = best.getString(0);

            if(!hard_1.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_country__flag_hard), Integer.parseInt(hard_1));
            }

            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_2_hard + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String hard_2 = best.getString(0);

            if(!hard_2.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_capitals_hard), Integer.parseInt(hard_2));
            }

            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_3_hard + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String hard_3 = best.getString(0);

            if(!hard_3.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_country__shape_hard), Integer.parseInt(hard_3));
            }

            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_0_medium + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String medium_0 = best.getString(0);

            if(!medium_0.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_flag__country_medium), Integer.parseInt(medium_0));
            }

            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_1_medium + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String medium_1 = best.getString(0);

            if(!medium_1.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_country__flag_medium), Integer.parseInt(medium_1));
            }
            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_2_medium + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String medium_2 = best.getString(0);

            if(!medium_2.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_capitals_medium), Integer.parseInt(medium_2));
            }

            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_3_medium + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String medium_3 = best.getString(0);

            if(!medium_3.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_country__shape_medium), Integer.parseInt(medium_3));
            }

            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_0_easy + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String easy_0 = best.getString(0);

            if(!easy_0.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_flag__country_easy), Integer.parseInt(easy_0));
            }

            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_1_easy + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String easy_1 = best.getString(0);

            if(!easy_1.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_country__flag_easy), Integer.parseInt(easy_1));
            }

            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_2_easy + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String easy_2 = best.getString(0);

            if(!easy_2.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_capitals_easy), Integer.parseInt(easy_2));
            }
            best = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_3_easy + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id= 1", null);
            best.moveToFirst();
            String easy_3 = best.getString(0);
            if(!easy_3.equals("0")) {
                Games.getLeaderboardsClient(this, signedInAccount)
                        .submitScore(getString(R.string.leaderboard_country__shape_easy), Integer.parseInt(easy_3));
            }
            best.close();
        }



    }

    @Override
    protected void attachBaseContext(Context newBase) {
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
}

