package com.twentyfourkapps.geograph;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.twentyfourkapps.geograph.data.CountryContract;

import java.util.ArrayList;
import java.util.Collections;

import static com.twentyfourkapps.geograph.R.id.plus5;
import static com.twentyfourkapps.geograph.R.layout.activity_game;
import static com.twentyfourkapps.geograph.R.layout.activity_game_siluet;

public class game extends AppCompatActivity {
    private long score;

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    private SQLiteDatabase db;

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    private Boolean exit = false;
    private CountDownTimer progressBarTimer;

    final long levelMaxTime = 6000;
    private long answerTime;

    public void setAnswerTime(long answerTime) {
        this.answerTime = answerTime;
    }

    private String country_name;
    private String country_button;
    private ArrayList<Integer> country_random = new ArrayList<>();

    private ArrayList<Integer> country_random_0 = new ArrayList<>();
    private ArrayList<Integer> country_buttons = new ArrayList<>();
    private ArrayList<Integer> country_buttons_all = new ArrayList<>();

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private ArrayList<Integer> number = new ArrayList<>();

    private int game_mode;
    private int game_difficulty;
    private int practice_mode;
    private int streak = 0;
    private int total_streak = 0;
    private int hits = 0;
    private int errors = 0;

    private TextView a25_powerup;
    private TextView a50_powerup;
    private TextView skip_powerup;
    private TextView timestop_powerup;

    private int a25_count;
    private int a50_count;
    private int skip_count;
    private int time_count;

    private TextView counter;
    private long current_time=60000;
    private CountDownTimer mTimer;

    private ImageButton skip_button;
    private ImageButton a25_button;
    private ImageButton time_button;
    private ImageButton a50_button;

    private int timer_on = 1;
    private long paused_question_time;

    private float ad_count;
    private int paused=0;
    private CountriesDbHelper countries_db = new CountriesDbHelper(this);
    private String LANG_CURRENT ="en";
    private int VIB_CURRENT = 1;
    private Boolean skip=false;
    private Boolean time=false;
    private Boolean a50=false;
    private Boolean a25=false;

    private int inarow=0;
    private int cheat=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        InterstitialAd mInterstitialAd;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Get values from previous activity
        ad_count = getIntent().getFloatExtra("ad_count",0);
        game_mode = getIntent().getIntExtra("game_mode",0);
        game_difficulty = getIntent().getIntExtra("game_difficulty",0);
        practice_mode = getIntent().getIntExtra("practice_mode",0);

        switch (game_mode){
            case 3:
                setContentView(activity_game_siluet);
                break;
            case 0:
                setContentView(activity_game);
                break;
        }

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //Use custom font for a TEXTVIEW
        String customFont = "Baloo-Regular.ttf";
        Typeface typeface = Typeface.createFromAsset(getAssets(), customFont);
        TextView textView2 =  findViewById(R.id.timer);
        TextView textView3 =  findViewById(R.id.score);
        TextView textView4 =  findViewById(R.id.plus5);
        textView2.setTypeface(typeface);
        textView3.setTypeface(typeface);
        textView4.setTypeface(typeface);

        //power ups transparency
        skip_button =  findViewById(R.id.skip_button);
        a25_button =  findViewById(R.id.a25_button);
        time_button =  findViewById(R.id.time_button);
        a50_button =  findViewById(R.id.a50_button);

        skip_powerup =  findViewById(R.id.skip_text);
        a25_powerup =  findViewById(R.id.a25_text);
        timestop_powerup =  findViewById(R.id.time_text);
        a50_powerup =  findViewById(R.id.a50_text);

        skip_button.setImageAlpha(76);
        a25_button.setImageAlpha(76);
        time_button.setImageAlpha(76);
        a50_button.setImageAlpha(76);

        //Create database and open it in readable mode
        getDatabase();

        get_idCountry_by_difficulty(game_difficulty);

        switch (game_mode){
            case 0:
                textView2.setTextColor(getResources().getColor(R.color.yellow_diff));
                break;
            case 1:
                textView2.setTextColor(getResources().getColor(R.color.blue_diff));
                break;
            case 2:
                textView2.setTextColor(getResources().getColor(R.color.pink_dark));
                break;
            case 3:
                textView2.setTextColor(getResources().getColor(R.color.green_diff));
                break;
        }


        switch (practice_mode){
            case 0:
                createCountDownTimer(current_time,100);
                mTimer.start();
                break;
            case 1:
                RelativeLayout background = findViewById(R.id.game_background);
                background.setBackground(getResources().getDrawable(R.drawable.gris));

                ProgressBar bar = findViewById(R.id.progressbar);
                bar.setVisibility(View.INVISIBLE);
                TextView practice_mode = findViewById(R.id.practice_mode_text);
                practice_mode.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);

                ImageButton back = findViewById(R.id.back);
                back.setVisibility(View.VISIBLE);
                break;
            default:
                throw new RuntimeException("Unknown game mode");
        }

        begin();

    }

    @Override
    protected void onPause() {
        super.onPause();
        paused=1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(paused == 2){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i3 = new Intent(game.this, finish.class);
                    i3.putExtra("ad_count",ad_count);
                    i3.putExtra("score",getScore());
                    i3.putExtra("game_mode", game_mode);
                    i3.putExtra("game_difficulty", game_difficulty);
                    startActivity(i3);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up );
                    finish();
                }
            }, 1000);

        }if(paused == 1){
            paused = 0;
        }

    }

    public void get_idCountry_by_difficulty(int diff){
        Cursor cursor_0 = getDb().rawQuery("SELECT " + CountryContract.CountryEntry.ID + " FROM " + CountryContract.CountryEntry.TABLE_NAME + " WHERE difficulty<="+ diff, null);

        if (cursor_0.moveToFirst()) {
            do {
                country_random_0.add(cursor_0.getInt(cursor_0.getColumnIndex(CountryContract.CountryEntry.ID )));
            } while (cursor_0.moveToNext());
        }
        country_random.addAll(country_random_0);
        country_buttons.addAll(country_random_0);
        country_random_0.clear();
        cursor_0.close();
    }

    public void begin() {
        //restart countries
        if (country_random.size() == 0){
            country_random.clear();
            get_idCountry_by_difficulty(game_difficulty);
        }

        number.clear();

        if (practice_mode==0) {
            question_timer(6000);
        }
        if(timer_on == 0 && practice_mode==0){
            mTimer.cancel();
            createCountDownTimer(current_time,100);
            mTimer.start();
            timer_on =1;
        }

        //randomize
        Collections.shuffle(country_random);
        Collections.shuffle(country_buttons);

        country_name = getCountry_name(country_random.get(0));
        country_button = getButton_name(country_random.get(0));

        country_buttons.remove(country_random.get(0));

        String answer1 = getButton_name(country_buttons.get(1));
        String answer2 = getButton_name(country_buttons.get(2));
        String answer3 = getButton_name(country_buttons.get(3));

        if(country_random.size()>0) {
            country_buttons.add(country_random.get(0));
        }
        country_random.remove(country_random.get(0));

        ImageView pais =  findViewById(R.id.country);
        switch (game_mode){
            case 3:
                //paint the random map selected
                int resource;
                resource = getResources().getIdentifier(country_name+"_country", "drawable", getPackageName());
                pais.setImageResource(resource);
                break;
            case 0:
                //paint the random flag selected

                int resource2;
                resource2 = getResources().getIdentifier(country_name+"_flag", "drawable", getPackageName());
                pais.setImageResource(resource2);

                int back2 = getResources().getIdentifier("shadow", "drawable", getPackageName());
                pais.setBackgroundResource(back2);
                break;

        }

        TextView score_text =  findViewById(R.id.score);
        score_text.setText("" + getScore());

        //randomize answers
        for (int i = 1; i <= 4; ++i) number.add(i);
        Collections.shuffle(number);

        String button_id_1 = "option" + number.get(0); //correct answer
        int button_name_1 = getResources().getIdentifier(button_id_1, "id", getPackageName());
        button1 =  findViewById(button_name_1);
        button1.setText(country_button);

        String button_id_2 = "option" + number.get(1);
        int button_name_2 = getResources().getIdentifier(button_id_2, "id", getPackageName());
        button2 =  findViewById(button_name_2);
        button2.setText(answer1);

        String button_id_3 = "option" + number.get(2);
        int button_name_3 = getResources().getIdentifier(button_id_3, "id", getPackageName());
        button3 =  findViewById(button_name_3);
        button3.setText(answer2);

        String button_id_4 = "option" + number.get(3);
        int button_name_4 = getResources().getIdentifier(button_id_4, "id", getPackageName());
        button4 =  findViewById(button_name_4);
        button4.setText(answer3);

        //reestablish button opacity and clickable attributes
        button1.setAlpha(1);
        button1.setClickable(true);
        button2.setAlpha(1);
        button2.setClickable(true);
        button3.setAlpha(1);
        button3.setClickable(true);
        button4.setAlpha(1);
        button4.setClickable(true);

    }

    public void answer(final View v) {
        switch (v.getId()) {
            case R.id.option1:
                button1 =  findViewById(R.id.option1);
                check(v, button1);
                break;
            case R.id.option2:
                button2 =  findViewById(R.id.option2);
                check(v, button2);
                break;
            case R.id.option3:
                button3 =  findViewById(R.id.option3);
                check(v, button3);
                break;
            case R.id.option4:
                button4 =  findViewById(R.id.option4);
                check(v, button4);
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Make the marks visibles during 200ms
                ImageView wrong =  findViewById(R.id.wrong_mark);
                ImageView check =  findViewById(R.id.check_mark);
                wrong.setVisibility(View.INVISIBLE);
                check.setVisibility(View.INVISIBLE);

                button1.setBackgroundResource(R.drawable.button_answer);
                button2.setBackgroundResource(R.drawable.button_answer);
                button3.setBackgroundResource(R.drawable.button_answer);
                button4.setBackgroundResource(R.drawable.button_answer);


                if(practice_mode ==0) {
                    progressBarTimer.cancel();
                }

                begin();

            }
        }, 200);
    }

    public void check(View v, Button button_OK) {
        TextView score_text =  findViewById(R.id.score);
        ImageView check =  findViewById(R.id.check_mark);
        ImageView wrong =  findViewById(R.id.wrong_mark);

        //If answer is correct assign score and streak
        if (button_OK.getText() == country_button) {
            long scoreQuestion;
            if(answerTime>5800){
                cheat++;
            }else{
                cheat =0;
            }

            if(cheat >=5 && errors>=20){
                score=0;
            }
            scoreQuestion = Math.max(0, (levelMaxTime - (levelMaxTime - answerTime))) / 100;
            scoreQuestion = Math.round(scoreQuestion*(1+total_streak/10));
            score = score + scoreQuestion;
            setScore(score);
            score_text.setText(String.valueOf(getScore()));
            button_OK.setBackgroundResource(R.color.green_button);
            check.setVisibility(View.VISIBLE);
            streak++;
            total_streak++;
            hits++;

            switch(total_streak){
                case 12:
                    inarow=12;
                    break;
                case 25:
                    inarow=25;
                    break;
                case 50:
                    inarow=50;
                    break;
            }

        } else { //if is not correct clear streak and substract score
            if(score<15){
                score = 0;
            }else{
                score=score-15;
            }
            setScore(score);
            wrong.setVisibility(View.VISIBLE);
            button_OK.setBackgroundResource(R.color.red_button);
            streak = 0;
            total_streak=0;
            errors++;
        }
        if(practice_mode ==0) {
            winning_streak(v);
        }
    }

    public ArrayList<Integer> check_achievements(){
        ArrayList<Integer> ret = new ArrayList<>();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(game.this);
        SharedPreferences.Editor editor = preferences.edit();

        if(hits>=50 && score >=2000){
            if (game_mode == 0) {
                switch(game_difficulty) {
                    case 0:
                        ret.add(R.string.achievement_medium_unlocked_flagcountry);
                        editor.putBoolean("medium_flagCountry", true);
                        editor.apply();
                        break;
                    case 1:
                        ret.add(R.string.achievement_hard_unlocked_flagcountry);
                        editor.putBoolean("hard_flagCountry", true);
                        editor.apply();
                        break;
                    case 2:
                        ret.add(R.string.achievement_master_of_flagcountry);
                        break;
                }
            }else{
                switch(game_difficulty) {
                    case 0:
                        ret.add(R.string.achievement_medium_unlocked_countryshape);
                        editor.putBoolean("medium_countryShape", true);
                        editor.apply();
                        break;
                    case 1:
                        ret.add(R.string.achievement_hard_unlocked_countryshape);
                        editor.putBoolean("hard_countryShape", true);
                        editor.apply();
                        break;
                    case 2:
                        ret.add(R.string.achievement_master_of_countryshape);
                        break;
                }
            }
        }

        if(skip&&time&&a25&&a50){
            ret.add(R.string.achievement_better_with_help);
        }
        if(!skip&&!time&&!a25&&!a50){
            ret.add(R.string.achievement_on_your_own);
        }

        switch (inarow){
            case 12:
                ret.add(R.string.achievement_first_streak);
                break;
            case 25:
                ret.add(R.string.achievement_first_streak);
                ret.add(R.string.achievement_you_are_in);
                break;
            case 50:
                ret.add(R.string.achievement_first_streak);
                ret.add(R.string.achievement_you_are_in);
                ret.add(R.string.achievement_the_masterpiece);
                break;
        }

        if(errors==0 && score>2000){
            ret.add(R.string.achievement_perfect_game);
        }

        return ret;
    }


    public String getCountry_name(int rand) {
        //Return filename of country to load drawable item
        Cursor show_country = getDb().rawQuery("SELECT " + CountryContract.CountryEntry.FILE_NAME + " FROM " + CountryContract.CountryEntry.TABLE_NAME + " WHERE id=" + rand + " AND difficulty<="+ game_difficulty, null);
        if(show_country.getCount() > 0) {
            show_country.moveToPosition(0);
            String result = show_country.getString(0);
            show_country.close();
            return result;
        }
        else{

            return "error";
        }

    }

    public String getButton_name(int rand) {
        //Select the answer of the questions in different languages
        Cursor show_country;
        if(LANG_CURRENT.equals("en")){
            show_country = getDb().rawQuery("SELECT " + CountryContract.CountryEntry.ANSWER + " FROM " + CountryContract.CountryEntry.TABLE_NAME + " WHERE id=" + rand + " AND difficulty<="+ game_difficulty , null);
            show_country.moveToFirst();
            String result = show_country.getString(0);
            show_country.close();
            return result;
        }else{
            show_country = getDb().rawQuery("SELECT " + CountryContract.CountryEntry.ANSWER_ES + " FROM " + CountryContract.CountryEntry.TABLE_NAME + " WHERE id=" + rand + " AND difficulty<="+ game_difficulty, null);
            show_country.moveToFirst();
            String result = show_country.getString(0);
            show_country.close();
            return result;
        }
    }


    @Override
    public void onBackPressed() {
        if (exit) {
            if (practice_mode == 0){
                progressBarTimer.cancel();
                mTimer.cancel();
            }
            countries_db.close();
            // db.delete("countries", null, null);
            ad_count = ad_count + 0.5f;
            Intent i2 = new Intent(game.this, MainActivity.class);
            i2.putExtra("game_mode", game_mode);
            i2.putExtra("ad_count",ad_count);
            i2.putExtra("game_difficulty",game_difficulty);
            startActivity(i2);
            finish();
        } else {
            Toast.makeText(this, R.string.backpressed_game,
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

    public void home_pressed(View v){
        countries_db.close();
        // db.delete("countries", null, null);
        Intent i2 = new Intent(game.this, MainActivity.class);
        i2.putExtra("game_mode", game_mode);
        i2.putExtra("ad_count",ad_count);
        i2.putExtra("game_difficulty",game_difficulty);
        startActivity(i2);
    }


    public void getDatabase() {
        setDb(countries_db.getWritableDatabase());
    }

    public void question_timer(long time) {
        final ProgressBar mProgressBar;
        int interval =1;


        mProgressBar =  findViewById(R.id.progressbar);
        mProgressBar.setProgress(0);
        progressBarTimer = new CountDownTimer(time, interval) {

            @Override
            public void onTick(long millisUntilFinished) {
                mProgressBar.setProgress((int) Math.round(millisUntilFinished / 1.0));
                setAnswerTime(millisUntilFinished);
                paused_question_time = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                //Do what you want
                mProgressBar.setProgress(0);
                streak=0;
                begin();
            }
        };
        progressBarTimer.start();
    }

    public void winning_streak(final View v) {
        //power ups
        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        int vibrate_time = 100;
        switch (streak) {
            case 3: //skip3

                if ((VIB_CURRENT == 1) && (vib != null )){
                    vib.vibrate(vibrate_time);
                }
                skip_count++;
                skip_powerup.setText(String.valueOf(skip_count));
                skip_button.setImageAlpha(255);
                skip_button.setClickable(true);
                RunAnimation(skip_button);
                break;
            case 6: //25%6
                if ((VIB_CURRENT == 1) && (vib != null )) {
                    vib.vibrate(vibrate_time);
                }
                a25_count++;
                a25_powerup.setText(String.valueOf(a25_count));
                a25_button.setImageAlpha(255);
                a25_button.setClickable(true);
                RunAnimation(a25_button);
                break;
            case 9: //stoptime9
                if ((VIB_CURRENT == 1)&& (vib != null )){
                    vib.vibrate(vibrate_time);
                }
                time_count++;
                timestop_powerup.setText(String.valueOf(time_count));
                time_button.setImageAlpha(255);
                time_button.setClickable(true);
                RunAnimation(time_button);
                break;
            case 12: //50%12
                if ((VIB_CURRENT == 1) && (vib != null )){
                    vib.vibrate(vibrate_time);
                }
                a50_count++;
                a50_powerup.setText(String.valueOf(a50_count));
                a50_button.setImageAlpha(255);
                a50_button.setClickable(true);
                RunAnimation(a50_button);
                streak=0;
                break;
            default:
        }

        if (((streak % 5) == 0 ) && (streak>0)) { //Extra time
            TextView plus =  findViewById(plus5);
            plus.setVisibility(View.VISIBLE);
            if (practice_mode==0) {
                mTimer.cancel();
                createCountDownTimer(current_time + 5000, 100);
                mTimer.start();
            }
            plusAnimation();
            plus.setVisibility(View.INVISIBLE);
        }
    }

    private void RunAnimation(ImageButton p_up) {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.textview_animation);
        a.reset();
        p_up.clearAnimation();
        p_up.startAnimation(a);
    }
    private void plusAnimation() {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.plus_animation);
        TextView plus =  findViewById(plus5);
        a.reset();
        plus.clearAnimation();
        plus.startAnimation(a);
    }

    public void skip_pressed(final View v){//skip powerup
        if(skip_count > 0 ){
            skip=true;
            skip_count--;
            skip_powerup.setText(String.valueOf(skip_count));
            progressBarTimer.cancel();
            begin();
        }
        if (skip_count == 0){
            skip_button.setClickable(false);
            skip_button.setImageAlpha(76);

        }
    }

    public void time_pressed(final View v){//stop time power up
        if(time_count > 0 ){
            time=true;
            time_count--;
            timestop_powerup.setText(String.valueOf(time_count));
            progressBarTimer.cancel();
            mTimer.cancel();
            timer_on=0;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    if(timer_on == 0 && practice_mode==0) {
                        question_timer(paused_question_time);
                        createCountDownTimer(current_time, 100);
                        mTimer.start();

                    }

                }
            }, 5000);
        }
        if (time_count == 0){
            time_button.setClickable(false);
            time_button.setImageAlpha(76);

        }
    }



    public void a25_pressed(final View v){ //25% power up

        ArrayList<Integer> numero = new ArrayList<>();
        if(a25_count >0) {
            a25=true;
            a25_count--;
            a25_powerup.setText(String.valueOf(a25_count));
            for (int i = 2; i <= 4; ++i) numero.add(i);
            Collections.shuffle(numero);
            switch (numero.get(0)) {
                case 2:
                    button2.setAlpha(0.3f);
                    button2.setClickable(false);
                    break;
                case 3:
                    button3.setAlpha(0.3f);
                    button3.setClickable(false);
                    break;
                case 4:
                    button4.setAlpha(0.3f);
                    button4.setClickable(false);
                    break;

            }
            if(a25_count ==0){
                a25_button.setClickable(false);
                a25_button.setImageAlpha(76);
            }
        }
    }

    public void a50_pressed(final View v){ // 50% power up

        ArrayList<Integer> numero = new ArrayList<>();
        if(a50_count >0) {
            a50=true;
            a50_count--;
            a50_powerup.setText(String.valueOf(a50_count));
            for (int i = 2; i <= 4; ++i) numero.add(i);
            Collections.shuffle(numero);
            switch (numero.get(0)) {
                case 2:
                    button2.setAlpha(0.3f);
                    button2.setClickable(false);
                    break;
                case 3:
                    button3.setAlpha(0.3f);
                    button3.setClickable(false);
                    break;
                case 4:
                    button4.setAlpha(0.3f);
                    button4.setClickable(false);
                    break;

            }
            switch (numero.get(1)) {
                case 2:
                    button2.setAlpha(0.3f);
                    button2.setClickable(false);
                    break;
                case 3:
                    button3.setAlpha(0.3f);
                    button3.setClickable(false);
                    break;
                case 4:
                    button4.setAlpha(0.3f);
                    button4.setClickable(false);
                    break;
            }

            if(a50_count ==0){
                a50_button.setClickable(false);
                a50_button.setImageAlpha(76);
            }
        }
    }

    private void createCountDownTimer(long time, int interval) {
        mTimer = new CountDownTimer(time, interval) {

            @Override
            public void onTick(long millisUntilFinished) {

                current_time = millisUntilFinished;
                //update the UI with the new count
                counter =  findViewById(R.id.timer);

                if (millisUntilFinished < 10000) {
                    counter.setText("0" + ((millisUntilFinished) / 1000));
                    //   TextView clock =  findViewById(R.id.timer);
                    //  clock.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.red_clock));
                } else {
                    counter.setText("" + ((millisUntilFinished) / 1000));

                }

            }


            @Override
            public void onFinish() {

                progressBarTimer.cancel();
                mTimer.cancel();

                // countries_db.close();
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                TextView plus =  findViewById(plus5);
                plus.setText(R.string.time_out);
                plus.setVisibility(View.VISIBLE);
                if(paused == 0){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<Integer> achievement;
                            achievement = check_achievements();
                            TextView plus = findViewById(plus5);
                            plus.setVisibility(View.INVISIBLE);
                            Intent i3 = new Intent(game.this, finish.class);
                            if (achievement.size()>0) {
                                i3.putExtra("achievement", achievement);
                            }
                            i3.putExtra("ad_count",ad_count);
                            i3.putExtra("score",getScore());
                            i3.putExtra("game_mode", game_mode);
                            i3.putExtra("game_difficulty", game_difficulty);
                            startActivity(i3);
                            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up );
                            finish();
                        }
                    }, 1000);

                }
                if (paused == 1){
                    paused =2;
                }
            }
        };
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        LANG_CURRENT = preferences.getString("Language", "en");
        VIB_CURRENT = preferences.getInt("Vibration",1);

        super.attachBaseContext(LocaleManager.setNewLocale(newBase,LANG_CURRENT));
    }
}








