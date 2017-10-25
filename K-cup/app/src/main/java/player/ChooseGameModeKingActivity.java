package player;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import identity.KingRule;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

import static utils.GameUtils.getRandomPlayer;

public class ChooseGameModeKingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_game_mode_king);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);

        Button easy = (Button) findViewById(R.id.easy);
        easy.setTypeface(typeface);
        Button normal = (Button) findViewById(R.id.normal);
        normal.setTypeface(typeface);
        Button hard = (Button) findViewById(R.id.hard);
        hard.setTypeface(typeface);
        Button reallyHard = (Button) findViewById(R.id.really_hard);
        reallyHard.setTypeface(typeface);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGame(1);
                goToBeginGame();
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGame(2);
                goToBeginGame();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGame(3);
                goToBeginGame();
            }
        });

        reallyHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGame(4);
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
        Intent k = new Intent(ChooseGameModeKingActivity.this, BeginGameActivity.class);
        k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
        startActivity(k);
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
        finish();
    }

    private void createNewGame(int nbDrinks) {

        List<KingRule> kingRulesForGame = new ArrayList<>();
        List<KingRule> kingRules = SharedPreferenceUtils.getkingRule(getApplicationContext(), SharedPreferenceUtils.PREFS_KING_RULE);

        if (kingRules != null && !kingRules.isEmpty()) {

            List<String> listPlayer = SharedPreferenceUtils.getAllPlayer(getApplicationContext(), SharedPreferenceUtils.PREFS_PLAYER);

            int nbKing = 0;

            Collections.shuffle(kingRules);
            for (int a = 0;a < kingRules.size();a++){

                KingRule kingRule = new KingRule();
                String player = getRandomPlayer(listPlayer);

                if (kingRules.get(a).getContent().equals(getString(R.string.card_king))){
                    nbKing++;
                }

                if (nbKing == 4){
                    kingRule.setKing(true);
                    kingRule.setNameKing(player);
                    nbKing++;
                }

                kingRule.setType(kingRules.get(a).getType());
                kingRule.setNumber(kingRules.get(a).getNumber());
                kingRule.setContent(getContentForKing(getApplicationContext(), kingRules.get(a).getContent(), player, nbDrinks));

                kingRulesForGame.add(kingRule);
            }

            SharedPreferenceUtils.saveKingkRuleGame(getApplicationContext(), kingRulesForGame, SharedPreferenceUtils.PREFS_KING_RULE_GAME);
        }
    }

    private static String getContentForKing(Context context, String kingRule, String player, int nbDrinks) {
        String tmp = kingRule;
        tmp = tmp.replace("%s", player);

        Random r = new Random();

        String newDrinks;

        if (nbDrinks > 1) {
            newDrinks = String.valueOf(nbDrinks) + " " + context.getString(R.string.drinks);
        } else {
            newDrinks = String.valueOf(nbDrinks) + " " + context.getString(R.string.drink);
        }

        tmp = tmp.replace("%b", newDrinks);

        return tmp;
    }
}
