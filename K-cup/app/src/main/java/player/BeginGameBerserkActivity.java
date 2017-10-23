package player;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import game.BerserkQuestionActivity;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class BeginGameBerserkActivity extends AppCompatActivity {

    private AnimationDrawable anim;

    private ImageView logo;

    private TextView title;
    private TextView title2;

    private Animation anim1;
    private Animation anim2;
    private Animation anim3;

    private final Handler handler = new Handler();
    private final Handler handler2 = new Handler();
    private final Handler handler3 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_begin_game_berserk);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        LinearLayout container = (LinearLayout) findViewById(R.id.content);

        logo = (ImageView) findViewById(R.id.logo);

        title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);

        title2 = (TextView) findViewById(R.id.title_2);
        title2.setTypeface(typeface);

        anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_disappear);
        anim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_disappear);

        title.startAnimation(anim1);

        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(2000);
        anim.setExitFadeDuration(2000);
        anim.setOneShot(true);
    }

    private void goToNextRule() {

        if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.BERSERK.toString())) {
            Intent k = new Intent(BeginGameBerserkActivity.this, BerserkQuestionActivity.class);
            k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
            startActivity(k);
        }
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
        handler2.removeCallbacksAndMessages(null);
        handler3.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (anim1 != null) {
                    logo.setVisibility(View.VISIBLE);
                    logo.startAnimation(anim1);

                    anim3.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            title.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    title.startAnimation(anim3);

                    title.setVisibility(View.INVISIBLE);
                    title2.setVisibility(View.VISIBLE);
                    title2.startAnimation(anim1);
                }
            }
        }, 3000);

        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {

                anim2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        logo.setVisibility(View.INVISIBLE);
                        title2.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                logo.startAnimation(anim2);
                title2.startAnimation(anim2);
            }
        }, 5000);

        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNextRule();
            }
        }, 7200);

        if (anim != null && !anim.isRunning())
            anim.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning()) {
            anim.stop();
        }
    }

}
