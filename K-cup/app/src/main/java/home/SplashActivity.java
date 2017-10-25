package home;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.crashlytics.android.Crashlytics;
import com.kcup.drinkgame.k_cup.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import identity.BerserkRule;
import identity.KingRule;
import io.fabric.sdk.android.Fabric;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 3300;

    private LottieAnimationView animationView;
    private LinearLayout titleArea;
    private LinearLayout buttonArea;

    private ImageView logo;

    private Button okButton;
    private Button cancelButton;

    private TextView begin;
    private TextView content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        content = (TextView) findViewById(R.id.content);
        begin = (TextView) findViewById(R.id.begin);
        content.setTypeface(typeface);
        begin.setTypeface(typeface);
        content.setVisibility(View.GONE);

        logo = (ImageView) findViewById(R.id.logo);

        titleArea = (LinearLayout) findViewById(R.id.title_area);
        buttonArea = (LinearLayout) findViewById(R.id.button_area);
        okButton = (Button) findViewById(R.id.not_cancel);
        cancelButton = (Button) findViewById(R.id.cancel);
        okButton.setTypeface(typeface);
        cancelButton.setTypeface(typeface);
        buttonArea.setVisibility(View.GONE);

        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        savePlayer();
        saveKing();
        saveQuestionAnswerBerserk();
        timerToLaunchHome();
    }

    private void savePlayer() {

        List<String> playerList = SharedPreferenceUtils.getAllPlayer(getApplicationContext(), SharedPreferenceUtils.PREFS_PLAYER);

        if (playerList == null || playerList.isEmpty()) {

            playerList = new ArrayList<>();
            playerList.add(getString(R.string.player) + " 1");
            playerList.add(getString(R.string.player) + " 2");
            playerList.add(getString(R.string.player) + " 3");

            SharedPreferenceUtils.savePlayer(getApplicationContext(), playerList, SharedPreferenceUtils.PREFS_PLAYER);
        }
    }

    private void saveKing(){

        List<KingRule> kingRules = SharedPreferenceUtils.getkingRule(getApplicationContext(), SharedPreferenceUtils.PREFS_KING_RULE);

        if (kingRules == null || kingRules.isEmpty()){
            saveKingInfo();
        }

    }

    private void saveKingInfo(){

        List<KingRule> kingRules = new ArrayList<>();

        kingRules.add(new KingRule(getString(R.string.card_1), GameUtils.KingType.PIQUE.toString(), "1"));
        kingRules.add(new KingRule(getString(R.string.card_2), GameUtils.KingType.PIQUE.toString(), "2"));
        kingRules.add(new KingRule(getString(R.string.card_3), GameUtils.KingType.PIQUE.toString(), "3"));
        kingRules.add(new KingRule(getString(R.string.card_4), GameUtils.KingType.PIQUE.toString(), "4"));
        kingRules.add(new KingRule(getString(R.string.card_5), GameUtils.KingType.PIQUE.toString(), "5"));
        kingRules.add(new KingRule(getString(R.string.card_6), GameUtils.KingType.PIQUE.toString(), "6"));
        kingRules.add(new KingRule(getString(R.string.card_7), GameUtils.KingType.PIQUE.toString(), "7"));
        kingRules.add(new KingRule(getString(R.string.card_8), GameUtils.KingType.PIQUE.toString(), "8"));
        kingRules.add(new KingRule(getString(R.string.card_9), GameUtils.KingType.PIQUE.toString(), "9"));
        kingRules.add(new KingRule(getString(R.string.card_10), GameUtils.KingType.PIQUE.toString(), "10"));
        kingRules.add(new KingRule(getString(R.string.card_knight), GameUtils.KingType.PIQUE.toString(), "V"));
        kingRules.add(new KingRule(getString(R.string.card_queen), GameUtils.KingType.PIQUE.toString(), "D"));
        kingRules.add(new KingRule(getString(R.string.card_king), GameUtils.KingType.PIQUE.toString(), "R"));
        kingRules.add(new KingRule(getString(R.string.card_1), GameUtils.KingType.TREFLE.toString(), "1"));
        kingRules.add(new KingRule(getString(R.string.card_2), GameUtils.KingType.TREFLE.toString(), "2"));
        kingRules.add(new KingRule(getString(R.string.card_3), GameUtils.KingType.TREFLE.toString(), "3"));
        kingRules.add(new KingRule(getString(R.string.card_4), GameUtils.KingType.TREFLE.toString(), "4"));
        kingRules.add(new KingRule(getString(R.string.card_5), GameUtils.KingType.TREFLE.toString(), "5"));
        kingRules.add(new KingRule(getString(R.string.card_6), GameUtils.KingType.TREFLE.toString(), "6"));
        kingRules.add(new KingRule(getString(R.string.card_7), GameUtils.KingType.TREFLE.toString(), "7"));
        kingRules.add(new KingRule(getString(R.string.card_8), GameUtils.KingType.TREFLE.toString(), "8"));
        kingRules.add(new KingRule(getString(R.string.card_9), GameUtils.KingType.TREFLE.toString(), "9"));
        kingRules.add(new KingRule(getString(R.string.card_10), GameUtils.KingType.TREFLE.toString(), "10"));
        kingRules.add(new KingRule(getString(R.string.card_knight), GameUtils.KingType.TREFLE.toString(), "V"));
        kingRules.add(new KingRule(getString(R.string.card_queen), GameUtils.KingType.TREFLE.toString(), "D"));
        kingRules.add(new KingRule(getString(R.string.card_king), GameUtils.KingType.TREFLE.toString(), "R"));
        kingRules.add(new KingRule(getString(R.string.card_1), GameUtils.KingType.CARREAU.toString(), "1"));
        kingRules.add(new KingRule(getString(R.string.card_2), GameUtils.KingType.CARREAU.toString(), "2"));
        kingRules.add(new KingRule(getString(R.string.card_3), GameUtils.KingType.CARREAU.toString(), "3"));
        kingRules.add(new KingRule(getString(R.string.card_4), GameUtils.KingType.CARREAU.toString(), "4"));
        kingRules.add(new KingRule(getString(R.string.card_5), GameUtils.KingType.CARREAU.toString(), "5"));
        kingRules.add(new KingRule(getString(R.string.card_6), GameUtils.KingType.CARREAU.toString(), "6"));
        kingRules.add(new KingRule(getString(R.string.card_7), GameUtils.KingType.CARREAU.toString(), "7"));
        kingRules.add(new KingRule(getString(R.string.card_8), GameUtils.KingType.CARREAU.toString(), "8"));
        kingRules.add(new KingRule(getString(R.string.card_9), GameUtils.KingType.CARREAU.toString(), "9"));
        kingRules.add(new KingRule(getString(R.string.card_10), GameUtils.KingType.CARREAU.toString(), "10"));
        kingRules.add(new KingRule(getString(R.string.card_knight), GameUtils.KingType.CARREAU.toString(), "V"));
        kingRules.add(new KingRule(getString(R.string.card_queen), GameUtils.KingType.CARREAU.toString(), "D"));
        kingRules.add(new KingRule(getString(R.string.card_king), GameUtils.KingType.CARREAU.toString(), "R"));
        kingRules.add(new KingRule(getString(R.string.card_1), GameUtils.KingType.COEUR.toString(), "1"));
        kingRules.add(new KingRule(getString(R.string.card_2), GameUtils.KingType.COEUR.toString(), "2"));
        kingRules.add(new KingRule(getString(R.string.card_3), GameUtils.KingType.COEUR.toString(), "3"));
        kingRules.add(new KingRule(getString(R.string.card_4), GameUtils.KingType.COEUR.toString(), "4"));
        kingRules.add(new KingRule(getString(R.string.card_5), GameUtils.KingType.COEUR.toString(), "5"));
        kingRules.add(new KingRule(getString(R.string.card_6), GameUtils.KingType.COEUR.toString(), "6"));
        kingRules.add(new KingRule(getString(R.string.card_7), GameUtils.KingType.COEUR.toString(), "7"));
        kingRules.add(new KingRule(getString(R.string.card_8), GameUtils.KingType.COEUR.toString(), "8"));
        kingRules.add(new KingRule(getString(R.string.card_9), GameUtils.KingType.COEUR.toString(), "9"));
        kingRules.add(new KingRule(getString(R.string.card_10), GameUtils.KingType.COEUR.toString(), "10"));
        kingRules.add(new KingRule(getString(R.string.card_knight), GameUtils.KingType.COEUR.toString(), "V"));
        kingRules.add(new KingRule(getString(R.string.card_queen), GameUtils.KingType.COEUR.toString(), "D"));
        kingRules.add(new KingRule(getString(R.string.card_king), GameUtils.KingType.COEUR.toString(), "R"));

        SharedPreferenceUtils.saveKingkRule(getApplicationContext(), kingRules, SharedPreferenceUtils.PREFS_KING_RULE);
    }

    private void saveQuestionAnswerBerserk() {

        List<BerserkRule> berserkRules = SharedPreferenceUtils.getBerserkRule(getApplicationContext(), SharedPreferenceUtils.PREFS_BERSERK_RULE);

        if (berserkRules == null || berserkRules.isEmpty()) {
            saveBerserkInfo();
        }
    }

    private void saveBerserkInfo() {

        List<BerserkRule> berserkRules = new ArrayList<>();

        List<String> content = getContentBerserk(getApplicationContext());
        List<String> goodQuestion = getGoodQuestionBerserk(getApplicationContext());
        List<String> badQuestion = getBadQuestionBerserk(getApplicationContext());

        for (int a = 0; a < content.size(); a++) {
            berserkRules.add(getBerserkRule(a, content, goodQuestion, badQuestion));
        }

        if (!berserkRules.isEmpty()) {
            SharedPreferenceUtils.saveBerserkRule(getApplicationContext(), berserkRules, SharedPreferenceUtils.PREFS_BERSERK_RULE);
        }

    }

    private BerserkRule getBerserkRule(int position, List<String> content, List<String> goodQuestion, List<String> badQuestion) {
        if (position == 0) {
            List<String> goodAnswer = getGoodAnswerBerserk0(getApplicationContext());
            List<String> badAnswer = getBadAnswerBerserk0(getApplicationContext());
            return new BerserkRule(content.get(position), goodQuestion.get(position), badQuestion.get(position), goodAnswer, badAnswer);
        } else if (position == 1) {
            List<String> goodAnswer = getGoodAnswerBerserk1(getApplicationContext());
            List<String> badAnswer = getBadAnswerBerserk1(getApplicationContext());
            return new BerserkRule(content.get(position), goodQuestion.get(position), badQuestion.get(position), goodAnswer, badAnswer);
        } else if (position == 2) {
            List<String> goodAnswer = getGoodAnswerBerserk2(getApplicationContext());
            List<String> badAnswer = getBadAnswerBerserk2(getApplicationContext());
            return new BerserkRule(content.get(position), goodQuestion.get(position), badQuestion.get(position), goodAnswer, badAnswer);
        }
        return null;
    }

    private void timerToLaunchHome() {

        animationView.setAnimation("begin.json");
        animationView.playAnimation();

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                showAdvertissement();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void showAdvertissement() {

        animationView.setVisibility(View.GONE);

        final Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.effect_challenge);

        content.setAnimation(anim1);
        logo.setAnimation(anim1);
        titleArea.setAnimation(anim1);

        content.setVisibility(View.VISIBLE);
        titleArea.setVisibility(View.VISIBLE);
        buttonArea.setVisibility(View.VISIBLE);
        buttonArea.setAnimation(anim1);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }

    private static List<String> getContentBerserk(Context context) {
        String[] listString = context.getResources().getStringArray(R.array.b_content_array);
        List<String> newString = new ArrayList<>();
        Collections.addAll(newString, listString);
        return newString;
    }

    private static List<String> getGoodQuestionBerserk(Context context) {
        String[] listString = context.getResources().getStringArray(R.array.b_good_question_array);
        List<String> newString = new ArrayList<>();
        Collections.addAll(newString, listString);
        return newString;
    }

    private static List<String> getBadQuestionBerserk(Context context) {
        String[] listString = context.getResources().getStringArray(R.array.b_bad_question_array);
        List<String> newString = new ArrayList<>();
        Collections.addAll(newString, listString);
        return newString;
    }

    private static List<String> getGoodAnswerBerserk0(Context context) {
        String[] listString = context.getResources().getStringArray(R.array.b_good_answer_array_0);
        List<String> newString = new ArrayList<>();
        Collections.addAll(newString, listString);
        return newString;
    }

    private static List<String> getBadAnswerBerserk0(Context context) {
        String[] listString = context.getResources().getStringArray(R.array.b_bad_answer_array_0);
        List<String> newString = new ArrayList<>();
        Collections.addAll(newString, listString);
        return newString;
    }

    private static List<String> getGoodAnswerBerserk1(Context context) {
        String[] listString = context.getResources().getStringArray(R.array.b_good_answer_array_1);
        List<String> newString = new ArrayList<>();
        Collections.addAll(newString, listString);
        return newString;
    }

    private static List<String> getBadAnswerBerserk1(Context context) {
        String[] listString = context.getResources().getStringArray(R.array.b_bad_answer_array_1);
        List<String> newString = new ArrayList<>();
        Collections.addAll(newString, listString);
        return newString;
    }

    private static List<String> getGoodAnswerBerserk2(Context context) {
        String[] listString = context.getResources().getStringArray(R.array.b_good_answer_array_2);
        List<String> newString = new ArrayList<>();
        Collections.addAll(newString, listString);
        return newString;
    }

    private static List<String> getBadAnswerBerserk2(Context context) {
        String[] listString = context.getResources().getStringArray(R.array.b_bad_answer_array_2);
        List<String> newString = new ArrayList<>();
        Collections.addAll(newString, listString);
        return newString;
    }
}
