package home;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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
import java.util.List;

import io.fabric.sdk.android.Fabric;
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

}
