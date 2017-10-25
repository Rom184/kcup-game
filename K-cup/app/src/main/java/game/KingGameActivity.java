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
import end.EndGameActivity;
import identity.KingRule;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class KingGameActivity extends AppCompatActivity {

    private KingRule currentRule;

    private FrameLayout card;

    private ImageView symbole1;
    private ImageView symbole2;
    private ImageView back;

    private TextView count;
    private TextView content;
    private TextView letter1;
    private TextView letter2;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_king_game);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        card = (FrameLayout) findViewById(R.id.card);

        symbole1 = (ImageView) findViewById(R.id.symbole1);
        symbole2 = (ImageView) findViewById(R.id.symbole2);
        back = (ImageView) findViewById(R.id.back);

        content = (TextView) findViewById(R.id.content);
        count = (TextView) findViewById(R.id.count);
        letter1 = (TextView) findViewById(R.id.letter1);
        letter2 = (TextView) findViewById(R.id.letter2);

        content.setTypeface(typeface);
        count.setTypeface(typeface);
        letter1.setTypeface(typeface);
        letter2.setTypeface(typeface);

        getCurrentRule();
        if (currentRule != null) {
            bindView();
        }
    }

    private void bindView() {
        String currentCount = String.valueOf(SharedPreferenceUtils.getPositionGame(getApplicationContext()) + 1) + " / "
                + String.valueOf(SharedPreferenceUtils.getkingRuleGame(getApplicationContext(), SharedPreferenceUtils.PREFS_KING_RULE_GAME).size());

        if (currentRule.getType().equals(GameUtils.KingType.COEUR.toString())){
            symbole1.setBackgroundResource(R.drawable.coeur);
            symbole2.setBackgroundResource(R.drawable.coeur);
        } else if (currentRule.getType().equals(GameUtils.KingType.CARREAU.toString())){
            symbole1.setBackgroundResource(R.drawable.carreau);
            symbole2.setBackgroundResource(R.drawable.carreau);
        } else if (currentRule.getType().equals(GameUtils.KingType.TREFLE.toString())){
            symbole1.setBackgroundResource(R.drawable.trefle);
            symbole2.setBackgroundResource(R.drawable.trefle);
        } else {
            symbole1.setBackgroundResource(R.drawable.pique);
            symbole2.setBackgroundResource(R.drawable.pique);
        }

        letter1.setText(currentRule.getNumber());
        letter2.setText(currentRule.getNumber());

        count.setText(currentCount);
        content.setText(currentRule.getContent());
        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);
        content.startAnimation(anim1);
        count.startAnimation(anim1);
        letter1.startAnimation(anim1);
        letter2.startAnimation(anim1);
        symbole1.startAnimation(anim1);
        symbole2.startAnimation(anim1);

        card.setOnClickListener(new View.OnClickListener() {
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
        if (position < SharedPreferenceUtils.getkingRuleGame(getApplicationContext(), SharedPreferenceUtils.PREFS_KING_RULE_GAME).size() - 1) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position + 1);
            Intent k = new Intent(KingGameActivity.this, KingGameActivity.class);
            startActivity(k);
            overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
            finish();
        } else {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), 0);
            Intent k = new Intent(KingGameActivity.this, EndGameActivity.class);
            startActivity(k);
            finish();
        }
    }

    private void getBack() {
        if (position > 1) {
            SharedPreferenceUtils.setPositionGame(getApplicationContext(), position - 1);
            Intent k = new Intent(KingGameActivity.this, KingGameActivity.class);
            startActivity(k);
            finish();
        }
    }

    private void getCurrentRule() {
        position = SharedPreferenceUtils.getPositionGame(getApplicationContext());
        this.currentRule = SharedPreferenceUtils.getkingRuleGame(getApplicationContext(), SharedPreferenceUtils.PREFS_KING_RULE_GAME).
                get(position);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, CancelActivity.class);
        i.putExtra(GameUtils.EXTRA_TYPE, GameUtils.Type.NO_NEED_PLAYER.toString());
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
