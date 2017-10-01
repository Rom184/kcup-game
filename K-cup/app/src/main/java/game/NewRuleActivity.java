package game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.kcup.drinkgame.k_cup.R;

import dialog.CancelActivity;
import end.EndGameActivity;
import identity.Rule;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class NewRuleActivity extends AppCompatActivity {

    private Rule currentRule;
    private int position;

    private ImageView bulle;
    private ImageView back;

    private FrameLayout content;
    private TextView title;
    private TextView ruleContent;
    private TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_rule);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");


        content = (FrameLayout) findViewById(R.id.background);
        bulle = (ImageView) findViewById(R.id.bulle);
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);
        ruleContent = (TextView) findViewById(R.id.content_rule);
        ruleContent.setTypeface(typeface);
        count = (TextView) findViewById(R.id.count);
        count.setTypeface(typeface);

        getCurrentRule();
        if (currentRule != null) {
            bindView();
        }
    }

    private void bindView() {
        String currentCount = String.valueOf(SharedPreferenceUtils.getPositionGame(getApplicationContext()) + 1) + " / "
                + String.valueOf(SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).size());

        count.setText(currentCount);
        ruleContent.setText(currentRule.getContent());
        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);
        title.startAnimation(anim1);
        ruleContent.startAnimation(anim1);
        count.startAnimation(anim1);
        bulle.startAnimation(anim1);

        content.setOnClickListener(new View.OnClickListener() {
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
        SharedPreferenceUtils.setPositionGame(getApplicationContext(), position + 1);
        if (position < SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).size()) {
            if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position + 1).getType()
                    .equals(GameUtils.Type.NO_NEED_PLAYER.toString())) {
                Intent k = new Intent(NewRuleActivity.this, NoNeedPlayerActivity.class);
                startActivity(k);
                overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                finish();
            } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position + 1).getType()
                    .equals(GameUtils.Type.CHALLENGE.toString())) {
                Intent k = new Intent(NewRuleActivity.this, ChallengeActivity.class);
                startActivity(k);
                overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                finish();
            } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position + 1).getType()
                    .equals(GameUtils.Type.CHOICE.toString())) {
                Intent k = new Intent(NewRuleActivity.this, ChoiceActivity.class);
                startActivity(k);
                overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                finish();
            } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position + 1).getType()
                    .equals(GameUtils.Type.KCUPS.toString())) {
                Intent k = new Intent(NewRuleActivity.this, KcupActivity.class);
                startActivity(k);
                overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                finish();
            }
        } else {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), 0);
            Intent k = new Intent(NewRuleActivity.this, EndGameActivity.class);
            startActivity(k);
            overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
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
            Intent k = new Intent(NewRuleActivity.this, ChallengeActivity.class);
            startActivity(k);
        } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position - 1)
                .getType().equals((GameUtils.Type.CHALLENGE.toString()))) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position - 1);
            Intent k = new Intent(NewRuleActivity.this, ChallengeActivity.class);
            startActivity(k);
        } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position - 1)
                .getType().equals((GameUtils.Type.CHOICE.toString()))) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position - 1);
            Intent k = new Intent(NewRuleActivity.this, ChoiceActivity.class);
            startActivity(k);
        } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position - 1)
                .getType().equals((GameUtils.Type.KCUPS.toString()))) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position - 1);
            Intent k = new Intent(NewRuleActivity.this, KcupActivity.class);
            startActivity(k);
        } else if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(position - 1).getType()
                .equals(GameUtils.Type.NEW_RULE_NEXT.toString())) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position - 1);
            Intent k = new Intent(NewRuleActivity.this, NewRuleNextActivity.class);
            startActivity(k);
        }
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, CancelActivity.class);
        i.putExtra(GameUtils.EXTRA_TYPE, GameUtils.Type.NEW_RULE.toString());
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
