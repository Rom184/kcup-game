package game;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.kcup.drinkgame.k_cup.R;

import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class BeginGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_begin_game);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation("StickAndBall.json");
        animationView.loop(true);
        animationView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNextRule();
            }
        }, 5000);
    }

    private void goToNextRule(){
        if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.NO_NEED_PLAYER.toString())) {
            Intent k = new Intent(BeginGameActivity.this, NoNeedPlayerActivity.class);
            startActivity(k);
        } else if ((SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.CHALLENGE.toString()))) {
            Intent k = new Intent(BeginGameActivity.this, ChallengeActivity.class);
            startActivity(k);
        } else if ((SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.CHOICE.toString()))) {
            Intent k = new Intent(BeginGameActivity.this, ChoiceActivity.class);
            startActivity(k);
        }
        finish();
    }

}
