package game;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import end.EndGameActivity;
import identity.Rule;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class BerserkQuestionActivity extends AppCompatActivity {

    private Rule currentRule;
    private int position;

    private ImageView back;

    private TextView question;
    private TextView count;

    private Button answer1;
    private Button answer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_berserk_question);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        back = (ImageView) findViewById(R.id.back);

        answer1 = (Button) findViewById(R.id.answer_1);
        answer2 = (Button) findViewById(R.id.answer_2);

        question = (TextView) findViewById(R.id.question);
        count = (TextView) findViewById(R.id.count);
        count.setTypeface(typeface);
        answer1.setTypeface(typeface);
        answer2.setTypeface(typeface);

        getCurrentRule();
        if (currentRule != null) {
            bindView();
        }
    }

    private void bindView() {
        String currentCount = String.valueOf(SharedPreferenceUtils.getPositionGame(getApplicationContext()) + 1) + " / "
                + String.valueOf(SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).size());

        count.setText(currentCount);

        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);

        count.startAnimation(anim1);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextRule();
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextRule();
            }
        });

        if (position > 0) {
            back.setVisibility(View.VISIBLE);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBack();
            }
        });
    }

    private void goToNextRule() {

        if (position < SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).size() - 1) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position + 1);
            if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position + 1).getType()
                    .equals(GameUtils.Type.BERSERK.toString())) {
                getCurrentRule();
                if (currentRule != null) {
                    bindView();
                } else {
                    finish();
                }
            }
        } else {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), 0);
            Intent k = new Intent(BerserkQuestionActivity.this, EndGameActivity.class);
            startActivity(k);
            finish();
        }

    }

    private void getCurrentRule() {
        position = SharedPreferenceUtils.getPositionGame(getApplicationContext());
        this.currentRule = SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).
                get(position);
    }

    private void getBack() {
        if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position - 1)
                .getType().equals((GameUtils.Type.BERSERK.toString()))) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position - 1);
            getCurrentRule();
            if (currentRule != null) {
                bindView();
            } else {
                finish();
            }
        }
    }

}
