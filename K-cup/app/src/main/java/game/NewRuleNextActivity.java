package game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import dialog.CancelActivity;
import identity.Rule;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class NewRuleNextActivity extends AppCompatActivity {

    private Rule currentRule;
    private int position;

    private ImageView bulle;
    private ImageView back;

    private FrameLayout content;
    private TextView ruleContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_rule_next);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        content = (FrameLayout) findViewById(R.id.background);
        bulle = (ImageView) findViewById(R.id.bulle);
        //back = (ImageView) findViewById(R.id.back);
        ruleContent = (TextView) findViewById(R.id.content_rule);
        ruleContent.setTypeface(typeface);

        getCurrentRule();
        if (currentRule != null) {
            bindViewNext();
        }
    }

    private void bindViewNext() {
        ruleContent.setText(currentRule.getContent());
        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);
        ruleContent.startAnimation(anim1);
        bulle.startAnimation(anim1);

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextRule();
            }
        });
        /*if (position > 0) {
            back.setVisibility(View.VISIBLE);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBack();
            }
        });*/
    }

    private void goToNextRule() {
        SharedPreferenceUtils.setPositionGame(getApplicationContext(), position + 1);
        if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position + 1).getType()
                .equals(GameUtils.Type.NO_NEED_PLAYER.toString())) {
            Intent k = new Intent(NewRuleNextActivity.this, NoNeedPlayerActivity.class);
            startActivity(k);
            finish();
        } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position + 1).getType()
                .equals(GameUtils.Type.CHALLENGE.toString())) {
            Intent k = new Intent(NewRuleNextActivity.this, ChallengeActivity.class);
            startActivity(k);
            finish();
        } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position + 1).getType()
                .equals(GameUtils.Type.CHOICE.toString())) {
            Intent k = new Intent(NewRuleNextActivity.this, ChoiceActivity.class);
            startActivity(k);
            finish();
        } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position + 1).getType()
                .equals(GameUtils.Type.NEW_RULE.toString())) {
            Intent k = new Intent(NewRuleNextActivity.this, NewRuleActivity.class);
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
                .getType().equals((GameUtils.Type.NO_NEED_PLAYER.toString()))) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position - 1);
            Intent k = new Intent(NewRuleNextActivity.this, ChallengeActivity.class);
            startActivity(k);
        } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position - 1)
                .getType().equals((GameUtils.Type.CHALLENGE.toString()))) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position - 1);
            Intent k = new Intent(NewRuleNextActivity.this, ChallengeActivity.class);
            startActivity(k);
        } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position - 1)
                .getType().equals((GameUtils.Type.CHOICE.toString()))) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position - 1);
            Intent k = new Intent(NewRuleNextActivity.this, ChoiceActivity.class);
            startActivity(k);
        }
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, CancelActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                this.finish();
            }
        }
    }

}
