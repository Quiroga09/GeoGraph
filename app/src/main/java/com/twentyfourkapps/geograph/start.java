package com.twentyfourkapps.geograph;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.twentyfourkapps.geograph.R.id.counter;
import static com.twentyfourkapps.geograph.R.layout.activity_start;

public class start extends AppCompatActivity {
    public int game_mode;
    public int game_difficulty;
    public int practice_mode;
    public int ad_count;
    Intent intent = new Intent();
    ImageView btn_rotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_start);
        RelativeLayout background = findViewById(R.id.game_background);

        //Use custom font for a TEXTVIEW
        String customFont = "Baloo-Regular.ttf";
        Typeface typeface = Typeface.createFromAsset(getAssets(), customFont);
        TextView textView2 =  findViewById(counter);
        textView2.setTypeface(typeface);

        Bundle bundle = getIntent().getExtras();
        ad_count = getIntent().getIntExtra("ad_count",0);
        game_mode = getIntent().getIntExtra("game_mode",0);
        game_difficulty = getIntent().getIntExtra("game_difficulty",0);
        practice_mode = getIntent().getIntExtra("practice_mode",0);

        btn_rotate =  findViewById(R.id.circle);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                myCountDown.start();

                Animation rotation = AnimationUtils.loadAnimation(start.this, R.anim.rotate_play);
                rotation.setFillAfter(true);
                btn_rotate.startAnimation(rotation);

            }
        }, 500);

        //Rotate an imageButton using xlm rotate_play animation.

        switch (game_mode) {
            case 3:
                background.setBackground(getResources().getDrawable(R.drawable.verd_full));
                break;
            case 0:
                background.setBackground(getResources().getDrawable(R.drawable.groc_full));
                break;
            case 1:
                background.setBackground(getResources().getDrawable(R.drawable.blau_full));
                break;
            case 2:
                background.setBackground(getResources().getDrawable(R.drawable.rosa_full));
                break;
            default:
        }

        if(practice_mode ==1){
            background.setBackground(getResources().getDrawable(R.drawable.gris_full));
        }
    }

    //new Counter that counts 3000 ms with a tick each 1000 ms
    CountDownTimer myCountDown = new CountDownTimer(4000,100) {
        public void onTick(long millisUntilFinished) {
            //update the UI with the new count
            TextView count_down =  findViewById(counter);
            count_down.setText("" + ((millisUntilFinished)/1000));
        }

        public void onFinish() {
            //start the activity
            change();
        }
    };
    public void change(){
        btn_rotate.clearAnimation();
        switch (game_mode) {
            case 3:
                intent = new Intent(this, game.class);
                break;
            case 0:
                intent = new Intent(this, game.class);
                break;
            case 1:
                intent = new Intent(this, game_flags.class);
                break;
            case 2:
                intent = new Intent(this, game_capitals.class);
                break;

        }

        intent.putExtra("game_mode", game_mode);
        intent.putExtra("ad_count",ad_count);
        intent.putExtra("game_difficulty",game_difficulty);
        intent.putExtra("practice_mode",practice_mode);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 500ms

                startActivity(intent);
                finish();

            }
        }, 200);

    }

    @Override
    public void onBackPressed(){
        myCountDown.cancel();
        Intent i2 = new Intent(start.this, MainActivity.class);
        i2.putExtra("game_mode", game_mode);
        i2.putExtra("game_difficulty",game_difficulty);
        i2.putExtra("ad_count",ad_count);
        startActivity(i2);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }
}


