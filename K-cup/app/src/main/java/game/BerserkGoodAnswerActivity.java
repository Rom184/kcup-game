package game;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import end.EndGameActivity;
import end.EndGameBerserkActivity;
import identity.BerserkRule;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class BerserkGoodAnswerActivity extends AppCompatActivity {

    private BerserkRule currentRule;
    private int position;

    private FrameLayout background;

    private TextView content;
    private TextView count;
    private TextView enrage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_berserk_good_answer);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        background = (FrameLayout) findViewById(R.id.background);

        content = (TextView) findViewById(R.id.content);
        content.setTypeface(typeface);
        count = (TextView) findViewById(R.id.count);
        count.setTypeface(typeface);

        enrage = (TextView) findViewById(R.id.enrage);
        enrage.setTypeface(typeface);

        getCurrentRule();
        if (currentRule != null) {
            bindView();
        }
    }

    private void bindView() {
        String currentCount = String.valueOf(SharedPreferenceUtils.getPositionGame(getApplicationContext()) + 1) + " / "
                + String.valueOf(SharedPreferenceUtils.getBerserkRuleGame(getApplicationContext(), SharedPreferenceUtils.PREFS_BERSERK_RULE).size());

        count.setText(currentCount);
        content.setText(currentRule.getGoodAnswer());
        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);
        content.startAnimation(anim1);
        count.startAnimation(anim1);

        String currentEnrage = String.valueOf(SharedPreferenceUtils.getPositionEnrage(getApplicationContext())) + " / " + "100";
        enrage.setText(currentEnrage);
        enrage.startAnimation(anim1);

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextRule();
            }
        });
    }

    private void goToNextRule() {

        if (position < SharedPreferenceUtils.getBerserkRule(getApplicationContext(), SharedPreferenceUtils.PREFS_BERSERK_RULE).size() - 1) {
            SharedPreferenceUtils.setRoundGame(getApplicationContext(), GameUtils.Type.BERSERK_QUESTION.toString());
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position + 1);
            Intent k = new Intent(BerserkGoodAnswerActivity.this, BerserkQuestionActivity.class);
            startActivity(k);
            finish();
            overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
        } else {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), 0);
            Intent k = new Intent(BerserkGoodAnswerActivity.this, EndGameBerserkActivity.class);
            startActivity(k);
            finish();
        }
    }

    private void getCurrentRule() {
        position = SharedPreferenceUtils.getPositionGame(getApplicationContext());
        this.currentRule = SharedPreferenceUtils.getBerserkRuleGame(getApplicationContext(), SharedPreferenceUtils.PREFS_BERSERK_RULE_GAME).
                get(position);
    }

}
