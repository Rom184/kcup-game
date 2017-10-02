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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_begin_game_berserk);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        LinearLayout container = (LinearLayout) findViewById(R.id.content);

        final ImageView logo = (ImageView) findViewById(R.id.logo);

        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);

        final TextView title2 = (TextView) findViewById(R.id.title_2);
        title2.setTypeface(typeface);

        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);
        title.startAnimation(anim1);

        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(2000);
        anim.setExitFadeDuration(2000);
        anim.setOneShot(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logo.setVisibility(View.VISIBLE);
                logo.startAnimation(anim1);
                title2.setVisibility(View.VISIBLE);
                title2.startAnimation(anim1);
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_disappear);

                anim2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        logo.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                logo.startAnimation(anim2);
            }
        }, 5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNextRule();
            }
        }, 7200);

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
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    // Stopping animation:- stop the animation on onPause.
    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }

}
