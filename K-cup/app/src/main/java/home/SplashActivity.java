package home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.kcup.drinkgame.k_cup.R;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;

import dialog.WarningActivity;
import utils.SharedPreferenceUtils;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 1500;
    private boolean isTimeElapsed = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(typeface);
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isTimeElapsed = true;
                startHomeActivity();
            }
        }, SPLASH_DISPLAY_LENGTH);
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
