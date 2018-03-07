package com.twentyfourkapps.geograph;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnSuccessListener;
import com.twentyfourkapps.geograph.data.PlayerContract;


public class scores extends BaseActivity implements View.OnClickListener {
    private String LANG_CURRENT = "kk";
    private SQLiteDatabase db;
    private GoogleSignInAccount signedInAccount;
    public int user_logged_in=0;
    private static final int RC_LEADERBOARD_UI = 9004;
    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);


        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signedInAccount != null){
            user_logged_in = 1;
            setContentView(R.layout.activity_scores);

            Button easy0 = findViewById(R.id.easy0);
            Button easy1 = findViewById(R.id.easy1);
            Button easy2 = findViewById(R.id.easy2);
            Button easy3 = findViewById(R.id.easy3);
            Button medium0 = findViewById(R.id.medium0);
            Button medium1 = findViewById(R.id.medium1);
            Button medium2 = findViewById(R.id.medium2);
            Button medium3 = findViewById(R.id.medium3);
            Button hard0 = findViewById(R.id.hard0);
            Button hard1 = findViewById(R.id.hard1);
            Button hard2 = findViewById(R.id.hard2);
            Button hard3 = findViewById(R.id.hard3);

            easy0.setOnClickListener(this);
            easy1.setOnClickListener(this);
            easy2.setOnClickListener(this);
            easy3.setOnClickListener(this);
            medium0.setOnClickListener(this);
            medium1.setOnClickListener(this);
            medium2.setOnClickListener(this);
            medium3.setOnClickListener(this);
            hard0.setOnClickListener(this);
            hard1.setOnClickListener(this);
            hard2.setOnClickListener(this);
            hard3.setOnClickListener(this);

        } else{
            user_logged_in=0;
            setContentView(R.layout.activity_scores_no_logged);
            getDatabase();
            setBestScore();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.easy0:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_country__shape_easy))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.easy1:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_flag__country_easy))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.easy2:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_country__flag_easy))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.easy3:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_capitals_easy))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.medium0:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_country__shape_medium))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.medium1:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_flag__country_medium))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.medium2:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_country__flag_medium))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.medium3:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_capitals_medium))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.hard0:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_country__shape_hard))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.hard1:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_flag__country_hard))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.hard2:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_country__flag_hard))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            case R.id.hard3:
                Games.getLeaderboardsClient(this, signedInAccount)
                        .getLeaderboardIntent(getString(R.string.leaderboard_capitals_hard))
                        .addOnSuccessListener(new OnSuccessListener<Intent>() {
                            @Override
                            public void onSuccess(Intent intent) {
                                startActivityForResult(intent, RC_LEADERBOARD_UI);
                            }
                        });
                break;

            default:
                break;
        }

    }

    public void home_pressed(View view) {
        super.onBackPressed();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        LANG_CURRENT = preferences.getString("Language", "en");

        super.attachBaseContext(LocaleManager.setNewLocale(newBase,LANG_CURRENT));
    }

    public void getDatabase() {
        CountriesDbHelper countries_db = new CountriesDbHelper(this);
        setDb(countries_db.getWritableDatabase());

    }

    public void  setBestScore() {
        TextView easy0 = findViewById(R.id.easy0);
        TextView easy1 = findViewById(R.id.easy1);
        TextView easy2 = findViewById(R.id.easy2);
        TextView easy3 = findViewById(R.id.easy3);
        TextView medium0 = findViewById(R.id.medium0);
        TextView medium1 = findViewById(R.id.medium1);
        TextView medium2 = findViewById(R.id.medium2);
        TextView medium3 = findViewById(R.id.medium3);
        TextView hard0 = findViewById(R.id.hard0);
        TextView hard1 = findViewById(R.id.hard1);
        TextView hard2 = findViewById(R.id.hard2);
        TextView hard3 = findViewById(R.id.hard3);

        Cursor best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_0_easy + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1", null);
        best0.moveToFirst();
        easy0.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_0_medium + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        medium0.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_0_hard + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        hard0.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_1_easy + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        easy1.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_1_medium + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        medium1.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_1_hard + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        hard1.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_2_easy + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        easy2.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_2_medium + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        medium2.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_2_hard + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        hard2.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_3_easy + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        easy3.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_3_medium + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        medium3.setText(best0.getString(0));
        best0.close();

        best0 = db.rawQuery("SELECT " + PlayerContract.PlayerEntry.BEST_SCORE_3_hard + " FROM " + PlayerContract.PlayerEntry.TABLE_NAME + " WHERE id=1",  null);
        best0.moveToFirst();
        hard3.setText(best0.getString(0));
        best0.close();
    }



}