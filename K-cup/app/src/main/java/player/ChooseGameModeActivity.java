package player;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import java.util.List;

import game.*;
import identity.Rule;
import utils.GameUtils;
import utils.SharedPreferenceUtils;
import utils.TextUtils;

import static utils.GameUtils.EXTRA_TYPE;

public class ChooseGameModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_game_mode);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);

        Button easy = (Button) findViewById(R.id.easy);
        easy.setTypeface(typeface);
        Button normal = (Button) findViewById(R.id.normal);
        normal.setTypeface(typeface);
        Button hard = (Button) findViewById(R.id.hard);
        hard.setTypeface(typeface);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGame(25, 2);
                goToBeginGame();
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGame(40, 4);
                goToBeginGame();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGame(66, 6);
                goToBeginGame();
            }
        });

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void goToBeginGame() {
        Intent k = new Intent(ChooseGameModeActivity.this, BeginGameActivity.class);
        k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
        startActivity(k);
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
        finish();
    }

    private void createNewGame(int nbQuestion, int maxDrinks) {

        if (getIntent() != null) {
            Bundle b = getIntent().getExtras();
            if (b != null) {

                String extra = b.getString(EXTRA_TYPE);

                if (!TextUtils.isEmpty(extra)) {

                    List<Rule> ruleList = GameUtils.createCustomKcupGame(getApplicationContext(), extra, nbQuestion, maxDrinks);

                    SharedPreferenceUtils.saveRule(getApplicationContext(), ruleList, SharedPreferenceUtils.PREFS_RULE);
                    SharedPreferenceUtils.setPositionGame(getApplicationContext(), 0);

                    List<Rule> ttt = SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE);
                    for (int a = 0; a < ttt.size(); a++) {
                        Log.e("rule", String.valueOf(a) + " " + ttt.get(a).getType());
                    }
                }
            }
        }
    }
}
