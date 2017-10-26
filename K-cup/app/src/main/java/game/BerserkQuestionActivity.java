package game;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import identity.BerserkRule;
import utils.GameUtils;
import utils.SharedPreferenceUtils;
import utils.TextUtils;

public class BerserkQuestionActivity extends AppCompatActivity {

    private BerserkRule currentRule;
    private int position;

    private TextView content;
    private TextView count;

    private Button goodQuestion;
    private Button badQuestion;

    private TextView enrage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_berserk_question);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        goodQuestion = (Button) findViewById(R.id.good_question);
        badQuestion = (Button) findViewById(R.id.bad_question);

        content = (TextView) findViewById(R.id.content);
        count = (TextView) findViewById(R.id.count);
        content.setTypeface(typeface);
        count.setTypeface(typeface);
        goodQuestion.setTypeface(typeface);
        badQuestion.setTypeface(typeface);

        enrage = (TextView) findViewById(R.id.enrage);
        enrage.setTypeface(typeface);

        getCurrentRule();
        if (currentRule != null) {
            bindView();
        }
    }

    private void bindView() {
        String currentCount = String.valueOf(SharedPreferenceUtils.getPositionGame(getApplicationContext()) + 1) + " / "
                + String.valueOf(SharedPreferenceUtils.getBerserkRuleGame(getApplicationContext(), SharedPreferenceUtils.PREFS_BERSERK_RULE_GAME).size());

        count.setText(currentCount);

        if (!TextUtils.isEmpty(currentRule.getContent())) {
            content.setText(currentRule.getContent());
        }

        if (!TextUtils.isEmpty(currentRule.getGoodQuestion())) {
            goodQuestion.setText(currentRule.getGoodQuestion());
        }

        if (!TextUtils.isEmpty(currentRule.getBadQuestion())) {
            badQuestion.setText(currentRule.getBadQuestion());
        }

        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);

        count.startAnimation(anim1);

        String currentEnrage = String.valueOf(SharedPreferenceUtils.getPositionEnrage(getApplicationContext())) + " / " + "100";
        enrage.setText(currentEnrage);
        enrage.startAnimation(anim1);

        goodQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextRule(GameUtils.Type.BERSERK_GOOD_ANSWER.toString());
            }
        });

        badQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextRule(GameUtils.Type.BERSERK_BAD_ANSWER.toString());
            }
        });
    }

    private void goToNextRule(String type) {

        if (type.equals(GameUtils.Type.BERSERK_GOOD_ANSWER.toString())) {
            SharedPreferenceUtils.setPositionEnrage(getApplicationContext(), (
                    SharedPreferenceUtils.getPositionEnrage(getApplicationContext()) + currentRule.getRandomGoodAnswer()));
            SharedPreferenceUtils.setRoundGame(getApplicationContext(), GameUtils.Type.BERSERK_GOOD_ANSWER.toString());
            Intent k = new Intent(BerserkQuestionActivity.this, BerserkGoodAnswerActivity.class);
            startActivity(k);
            finish();
            overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
        } else {
            SharedPreferenceUtils.setPositionEnrage(getApplicationContext(), (
                    SharedPreferenceUtils.getPositionEnrage(getApplicationContext()) + currentRule.getRandomBadAnswer()));
            SharedPreferenceUtils.setPositionEnrage(getApplicationContext(), currentRule.getRandomGoodAnswer());
            SharedPreferenceUtils.setRoundGame(getApplicationContext(), GameUtils.Type.BERSERK_BAD_ANSWER.toString());
            Intent k = new Intent(BerserkQuestionActivity.this, BerserkBadAnswerActivity.class);
            startActivity(k);
            finish();
            overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
        }
    }

    private void getCurrentRule() {
        position = SharedPreferenceUtils.getPositionGame(getApplicationContext());
        this.currentRule = SharedPreferenceUtils.getBerserkRuleGame(getApplicationContext(), SharedPreferenceUtils.PREFS_BERSERK_RULE_GAME).get(position);
    }

}
