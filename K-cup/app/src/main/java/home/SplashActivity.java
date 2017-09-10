package home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.crashlytics.android.Crashlytics;
import com.kcup.drinkgame.k_cup.R;

import java.util.ArrayList;
import java.util.List;

import dialog.WarningActivity;
import io.fabric.sdk.android.Fabric;
import utils.SharedPreferenceUtils;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 3500;
    private boolean isTimeElapsed = false;

    private LottieAnimationView animationView;
    private LinearLayout buttonArea;

    private Button okButton;
    private Button cancelButton;

    private TextView text;
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

        buttonArea = (LinearLayout) findViewById(R.id.button_area);
        okButton = (Button) findViewById(R.id.not_cancel);
        cancelButton = (Button) findViewById(R.id.cancel);
        okButton.setTypeface(typeface);
        cancelButton.setTypeface(typeface);
        buttonArea.setVisibility(View.GONE);

        text = (TextView) findViewById(R.id.text);
        text.setTypeface(typeface);
        text.setVisibility(View.VISIBLE);

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

        animationView.setAnimation("preloader.json");
        animationView.loop(true);
        animationView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isTimeElapsed = true;
                showAdvertissement();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void showAdvertissement() {

        animationView.cancelAnimation();
        animationView.setVisibility(View.GONE);

        content.setVisibility(View.VISIBLE);
        begin.setVisibility(View.VISIBLE);

        text.setVisibility(View.GONE);
        buttonArea.setVisibility(View.VISIBLE);

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

    private void startHomeActivity() {
        if (isTimeElapsed) {
            Intent i = new Intent(this, WarningActivity.class);
            startActivityForResult(i, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            } else {
                finish();
            }
        }
    }

}
