package player;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import java.util.ArrayList;
import java.util.List;

import game.ChallengeActivity;
import game.ChoiceActivity;
import game.NoNeedPlayerActivity;
import identity.Rule;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class PlayerActivity extends AppCompatActivity {

    private EditText player1;
    private EditText player2;
    private EditText player3;
    private EditText player4;
    private EditText player5;
    private EditText player6;
    private EditText player7;
    private EditText player8;
    private EditText player9;
    private EditText player10;

    private Button addPlayer;

    private View line;

    private int nbPlayer = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_player);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");

        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);

        player1 = (EditText) findViewById(R.id.player_1);
        player1.setTypeface(typeface);
        player2 = (EditText) findViewById(R.id.player_2);
        player2.setTypeface(typeface);
        player3 = (EditText) findViewById(R.id.player_3);
        player3.setTypeface(typeface);
        player4 = (EditText) findViewById(R.id.player_4);
        player4.setTypeface(typeface);
        player5 = (EditText) findViewById(R.id.player_5);
        player5.setTypeface(typeface);
        player6 = (EditText) findViewById(R.id.player_6);
        player6.setTypeface(typeface);
        player7 = (EditText) findViewById(R.id.player_7);
        player7.setTypeface(typeface);
        player8 = (EditText) findViewById(R.id.player_8);
        player8.setTypeface(typeface);
        player9 = (EditText) findViewById(R.id.player_9);
        player9.setTypeface(typeface);
        player10 = (EditText) findViewById(R.id.player_10);
        player10.setTypeface(typeface);

        addPlayer = (Button) findViewById(R.id.add_player);
        addPlayer.setTypeface(typeface);
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayer();
            }
        });

        Button newGame = (Button) findViewById(R.id.new_game);
        newGame.setTypeface(typeface);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePlayer();
                createNewGame();
                goToNextRule();
            }
        });

        line = findViewById(R.id.line);
        setPlayer();

    }

    private void goToNextRule() {

        if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.NO_NEED_PLAYER.toString())) {
            Intent k = new Intent(PlayerActivity.this, NoNeedPlayerActivity.class);
            startActivity(k);
        } else if ((SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.CHALLENGE.toString()))) {
            Intent k = new Intent(PlayerActivity.this, ChallengeActivity.class);
            startActivity(k);
        } else if ((SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.CHOICE.toString()))) {
            Intent k = new Intent(PlayerActivity.this, ChoiceActivity.class);
            startActivity(k);
        }

        finish();
    }

    private void createNewGame() {
        List<Rule> ruleList;
        ruleList = GameUtils.createKcupGame(getApplicationContext());
        /*if (withRule.isChecked()) {
            ruleList = GameUtils.createRandomGameWithNewRule(getApplicationContext());
        } else {
            ruleList = GameUtils.createRandomGame(getApplicationContext());
        }*/
        SharedPreferenceUtils.saveRule(getApplicationContext(), ruleList, SharedPreferenceUtils.PREFS_RULE);
        SharedPreferenceUtils.setPositionGame(getApplicationContext(), 0);

        List<Rule> ttt = SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE);
        for (int a = 0; a < ttt.size(); a++) {
            Log.e("rule", String.valueOf(a) + " " + ttt.get(a).getType());
        }
    }

    private void savePlayer() {

        List<String> newPlayerList = new ArrayList<>();

        if (player1.getText().toString().equals("")) {
            newPlayerList.add(player1.getHint().toString());
        } else {
            newPlayerList.add(player1.getText().toString());
        }

        if (player2.getText().toString().equals("")) {
            newPlayerList.add(player2.getHint().toString());
        } else {
            newPlayerList.add(player2.getText().toString());
        }

        if (player3.getText().toString().equals("")) {
            newPlayerList.add(player3.getHint().toString());
        } else {
            newPlayerList.add(player3.getText().toString());
        }

        if (player4.getVisibility() == View.VISIBLE) {
            if (player4.getText().toString().equals("")) {
                newPlayerList.add(player4.getHint().toString());
            } else {
                newPlayerList.add(player4.getText().toString());
            }

            if (player5.getVisibility() == View.VISIBLE) {
                if (player5.getText().toString().equals("")) {
                    newPlayerList.add(player5.getHint().toString());
                } else {
                    newPlayerList.add(player5.getText().toString());
                }

                if (player6.getVisibility() == View.VISIBLE) {
                    if (player6.getText().toString().equals("")) {
                        newPlayerList.add(player6.getHint().toString());
                    } else {
                        newPlayerList.add(player6.getText().toString());
                    }

                    if (player7.getVisibility() == View.VISIBLE) {
                        if (player7.getText().toString().equals("")) {
                            newPlayerList.add(player7.getHint().toString());
                        } else {
                            newPlayerList.add(player7.getText().toString());
                        }

                        if (player8.getVisibility() == View.VISIBLE) {
                            if (player8.getText().toString().equals("")) {
                                newPlayerList.add(player8.getHint().toString());
                            } else {
                                newPlayerList.add(player8.getText().toString());
                            }

                            if (player9.getVisibility() == View.VISIBLE) {
                                if (player9.getText().toString().equals("")) {
                                    newPlayerList.add(player9.getHint().toString());
                                } else {
                                    newPlayerList.add(player9.getText().toString());
                                }

                                if (player10.getVisibility() == View.VISIBLE) {
                                    if (player10.getText().toString().equals("")) {
                                        newPlayerList.add(player10.getHint().toString());
                                    } else {
                                        newPlayerList.add(player10.getText().toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        SharedPreferenceUtils.savePlayer(getApplicationContext(), newPlayerList, SharedPreferenceUtils.PREFS_PLAYER);
    }

    private void addPlayer() {

        if (nbPlayer == 3) {
            player4.setVisibility(View.VISIBLE);
            nbPlayer = 4;
        } else if (nbPlayer == 4) {
            player5.setVisibility(View.VISIBLE);
            nbPlayer = 5;
        } else if (nbPlayer == 5) {
            player6.setVisibility(View.VISIBLE);
            nbPlayer = 6;
        } else if (nbPlayer == 6) {
            player7.setVisibility(View.VISIBLE);
            nbPlayer = 7;
        } else if (nbPlayer == 7) {
            player8.setVisibility(View.VISIBLE);
            nbPlayer = 8;
        } else if (nbPlayer == 8) {
            player9.setVisibility(View.VISIBLE);
            nbPlayer = 9;
        } else if (nbPlayer == 9) {
            player10.setVisibility(View.VISIBLE);
            nbPlayer = 10;
            addPlayer.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }
    }

    private void setPlayer() {

        player1.setHint(getString(R.string.player, "1"));
        player2.setHint(getString(R.string.player, "2"));
        player3.setHint(getString(R.string.player, "3"));
        player4.setHint(getString(R.string.player, "4"));
        player5.setHint(getString(R.string.player, "5"));
        player6.setHint(getString(R.string.player, "6"));
        player7.setHint(getString(R.string.player, "7"));
        player8.setHint(getString(R.string.player, "8"));
        player9.setHint(getString(R.string.player, "9"));
        player10.setHint(getString(R.string.player, "10"));
    }

}
