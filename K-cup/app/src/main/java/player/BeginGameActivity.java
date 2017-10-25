package player;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.kcup.drinkgame.k_cup.R;

import game.ChallengeActivity;
import game.ChoiceActivity;
import game.KingGameActivity;
import game.NoNeedPlayerActivity;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class BeginGameActivity extends AppCompatActivity {

    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_begin_game);

        animationView = (LottieAnimationView) findViewById(R.id.animation_view);

        animationView.setAnimation("begin_game.json");
        animationView.playAnimation();

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                goToNextRule();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    private void goToNextRule() {

        if (SharedPreferenceUtils.getTypeGame(getApplicationContext()).equals(GameUtils.Game.KCUP)) {
            if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                    .equals(GameUtils.Type.NO_NEED_PLAYER.toString())) {
                Intent k = new Intent(BeginGameActivity.this, NoNeedPlayerActivity.class);
                k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
                startActivity(k);
            } else if ((SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                    .equals(GameUtils.Type.CHALLENGE.toString()))) {
                Intent k = new Intent(BeginGameActivity.this, ChallengeActivity.class);
                k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
                startActivity(k);
            } else if ((SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                    .equals(GameUtils.Type.CHOICE.toString()))) {
                Intent k = new Intent(BeginGameActivity.this, ChoiceActivity.class);
                k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
                startActivity(k);
            }
        } else {
            Intent k = new Intent(BeginGameActivity.this, KingGameActivity.class);
            startActivity(k);
        }
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
        finish();
    }

}
