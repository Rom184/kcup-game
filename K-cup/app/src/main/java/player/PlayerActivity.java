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
    private EditText player11;
    private EditText player12;
    private EditText player13;
    private EditText player14;
    private EditText player15;
    private EditText player16;
    private EditText player17;
    private EditText player18;
    private EditText player19;
    private EditText player20;

    private Button addPlayer;

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
        player11 = (EditText) findViewById(R.id.player_11);
        player11.setTypeface(typeface);
        player12 = (EditText) findViewById(R.id.player_12);
        player12.setTypeface(typeface);
        player13 = (EditText) findViewById(R.id.player_13);
        player13.setTypeface(typeface);
        player14 = (EditText) findViewById(R.id.player_14);
        player14.setTypeface(typeface);
        player15 = (EditText) findViewById(R.id.player_15);
        player15.setTypeface(typeface);
        player16 = (EditText) findViewById(R.id.player_16);
        player16.setTypeface(typeface);
        player17 = (EditText) findViewById(R.id.player_17);
        player17.setTypeface(typeface);
        player18 = (EditText) findViewById(R.id.player_18);
        player18.setTypeface(typeface);
        player19 = (EditText) findViewById(R.id.player_19);
        player19.setTypeface(typeface);
        player20 = (EditText) findViewById(R.id.player_20);
        player20.setTypeface(typeface);

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

        setPlayer();

    }

    private void goToNextRule() {

        if (SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.NO_NEED_PLAYER.toString())) {
            Intent k = new Intent(PlayerActivity.this, NoNeedPlayerActivity.class);
            k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
            startActivity(k);
        } else if ((SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.CHALLENGE.toString()))) {
            Intent k = new Intent(PlayerActivity.this, ChallengeActivity.class);
            k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
            startActivity(k);
        } else if ((SharedPreferenceUtils.getRule(getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).get(0).getType()
                .equals(GameUtils.Type.CHOICE.toString()))) {
            Intent k = new Intent(PlayerActivity.this, ChoiceActivity.class);
            k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
            startActivity(k);
        }
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);

        finish();
    }

    private void createNewGame() {
        List<Rule> ruleList;
        ruleList = GameUtils.createKcupGame(getApplicationContext());
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

                                    if (player11.getVisibility() == View.VISIBLE) {
                                        if (player11.getText().toString().equals("")) {
                                            newPlayerList.add(player11.getHint().toString());
                                        } else {
                                            newPlayerList.add(player11.getText().toString());
                                        }

                                        if (player12.getVisibility() == View.VISIBLE) {
                                            if (player12.getText().toString().equals("")) {
                                                newPlayerList.add(player12.getHint().toString());
                                            } else {
                                                newPlayerList.add(player12.getText().toString());
                                            }

                                            if (player13.getVisibility() == View.VISIBLE) {
                                                if (player13.getText().toString().equals("")) {
                                                    newPlayerList.add(player13.getHint().toString());
                                                } else {
                                                    newPlayerList.add(player13.getText().toString());
                                                }

                                                if (player14.getVisibility() == View.VISIBLE) {
                                                    if (player14.getText().toString().equals("")) {
                                                        newPlayerList.add(player14.getHint().toString());
                                                    } else {
                                                        newPlayerList.add(player14.getText().toString());
                                                    }

                                                    if (player15.getVisibility() == View.VISIBLE) {
                                                        if (player15.getText().toString().equals("")) {
                                                            newPlayerList.add(player15.getHint().toString());
                                                        } else {
                                                            newPlayerList.add(player15.getText().toString());
                                                        }

                                                        if (player16.getVisibility() == View.VISIBLE) {
                                                            if (player16.getText().toString().equals("")) {
                                                                newPlayerList.add(player16.getHint().toString());
                                                            } else {
                                                                newPlayerList.add(player16.getText().toString());
                                                            }

                                                            if (player17.getVisibility() == View.VISIBLE) {
                                                                if (player17.getText().toString().equals("")) {
                                                                    newPlayerList.add(player17.getHint().toString());
                                                                } else {
                                                                    newPlayerList.add(player17.getText().toString());
                                                                }

                                                                if (player18.getVisibility() == View.VISIBLE) {
                                                                    if (player18.getText().toString().equals("")) {
                                                                        newPlayerList.add(player18.getHint().toString());
                                                                    } else {
                                                                        newPlayerList.add(player18.getText().toString());
                                                                    }

                                                                    if (player19.getVisibility() == View.VISIBLE) {
                                                                        if (player19.getText().toString().equals("")) {
                                                                            newPlayerList.add(player19.getHint().toString());
                                                                        } else {
                                                                            newPlayerList.add(player19.getText().toString());
                                                                        }

                                                                        if (player20.getVisibility() == View.VISIBLE) {
                                                                            if (player20.getText().toString().equals("")) {
                                                                                newPlayerList.add(player20.getHint().toString());
                                                                            } else {
                                                                                newPlayerList.add(player20.getText().toString());
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
        } else if (nbPlayer == 10) {
            player11.setVisibility(View.VISIBLE);
            nbPlayer = 11;
        } else if (nbPlayer == 11) {
            player12.setVisibility(View.VISIBLE);
            nbPlayer = 12;
        } else if (nbPlayer == 12) {
            player13.setVisibility(View.VISIBLE);
            nbPlayer = 13;
        } else if (nbPlayer == 13) {
            player14.setVisibility(View.VISIBLE);
            nbPlayer = 14;
        } else if (nbPlayer == 14) {
            player15.setVisibility(View.VISIBLE);
            nbPlayer = 15;
        } else if (nbPlayer == 15) {
            player16.setVisibility(View.VISIBLE);
            nbPlayer = 16;
        } else if (nbPlayer == 16) {
            player17.setVisibility(View.VISIBLE);
            nbPlayer = 17;
        } else if (nbPlayer == 17) {
            player18.setVisibility(View.VISIBLE);
            nbPlayer = 18;
        } else if (nbPlayer == 18) {
            player19.setVisibility(View.VISIBLE);
            nbPlayer = 19;
        } else if (nbPlayer == 19) {
            player20.setVisibility(View.VISIBLE);
            nbPlayer = 20;
            addPlayer.setVisibility(View.INVISIBLE);
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
        player11.setHint(getString(R.string.player, "11"));
        player12.setHint(getString(R.string.player, "12"));
        player13.setHint(getString(R.string.player, "13"));
        player14.setHint(getString(R.string.player, "14"));
        player15.setHint(getString(R.string.player, "15"));
        player16.setHint(getString(R.string.player, "16"));
        player17.setHint(getString(R.string.player, "17"));
        player18.setHint(getString(R.string.player, "18"));
        player19.setHint(getString(R.string.player, "19"));
        player20.setHint(getString(R.string.player, "20"));
    }

}
