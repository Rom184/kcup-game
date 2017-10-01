package player;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import game.ChallengeActivity;
import game.ChoiceActivity;
import game.NoNeedPlayerActivity;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class BeginGameBerserkActivity extends AppCompatActivity {

    private AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_begin_game_berserk);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        FrameLayout container = (FrameLayout) findViewById(R.id.content);

        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);

        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);
        title.startAnimation(anim1);

        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(1000);
        anim.setExitFadeDuration(2000);
        anim.setOneShot(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNextRule();
            }
        }, 4000);
    }

    private void goToNextRule() {

        if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.NO_NEED_PLAYER.toString())) {
            Intent k = new Intent(BeginGameBerserkActivity.this, NoNeedPlayerActivity.class);
            k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
            startActivity(k);
        } else if ((SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.CHALLENGE.toString()))) {
            Intent k = new Intent(BeginGameBerserkActivity.this, ChallengeActivity.class);
            k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
            startActivity(k);
        } else if ((SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.CHOICE.toString()))) {
            Intent k = new Intent(BeginGameBerserkActivity.this, ChoiceActivity.class);
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
