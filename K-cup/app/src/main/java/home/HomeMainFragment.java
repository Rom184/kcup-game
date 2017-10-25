package home;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kcup.drinkgame.k_cup.R;

import java.util.List;

import dialog.homeOptionActivity;
import game.ChallengeActivity;
import game.ChoiceActivity;
import game.KcupActivity;
import game.KingGameActivity;
import game.NewRuleActivity;
import game.NewRuleNextActivity;
import game.NoNeedPlayerActivity;
import identity.BerserkRule;
import player.BeginGameBerserkActivity;
import player.PlayerActivity;
import player.PlayerKingActivity;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

import static utils.GameUtils.EXTRA_TYPE;

public class HomeMainFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    private void bindview() {

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Medium.ttf");

        LinearLayout newNormalGame = (LinearLayout) view.findViewById(R.id.new_game_normal);
        TextView titleNormalGame = (TextView) view.findViewById(R.id.new_normal_game_title);
        TextView contentNormalGame = (TextView) view.findViewById(R.id.normal_game_info);
        titleNormalGame.setTypeface(typeface);
        contentNormalGame.setTypeface(typeface);

        final Button continueGame = (Button) view.findViewById(R.id.continue_game);
        continueGame.setTypeface(typeface);

        LinearLayout newKingGame = (LinearLayout) view.findViewById(R.id.new_game_king);
        TextView titleKingGame = (TextView) view.findViewById(R.id.new_king_game_title);
        TextView contentKingGame = (TextView) view.findViewById(R.id.king_game_info);
        titleKingGame.setTypeface(typeface);
        contentKingGame.setTypeface(typeface);

        LinearLayout newBerserkGame = (LinearLayout) view.findViewById(R.id.new_game_berserk);
        TextView titleBerserkGame = (TextView) view.findViewById(R.id.berserk_game_title);
        TextView contentBerserkGame = (TextView) view.findViewById(R.id.berserk_game_info);
        titleBerserkGame.setTypeface(typeface);
        contentBerserkGame.setTypeface(typeface);

        ImageView option = (ImageView) view.findViewById(R.id.option);
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), homeOptionActivity.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
            }
        });

        if (SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext()) > 0) {
            continueGame.setVisibility(View.VISIBLE);

            if (SharedPreferenceUtils.getTypeGame(getActivity().getApplicationContext()).equals(GameUtils.Game.KINGKCUP.toString())) {

                String continueContent = getString(R.string.continue_game) + "\n" +
                        (String.valueOf(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext()) + 1)) + " / " +
                        String.valueOf(SharedPreferenceUtils.getkingRuleGame(getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_KING_RULE_GAME).size());

                continueGame.setText(continueContent);

                continueGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent k = new Intent(getActivity(), KingGameActivity.class);
                        startActivity(k);
                        getActivity().overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                    }
                });
            }

            if (SharedPreferenceUtils.getTypeGame(getActivity().getApplicationContext()).equals(GameUtils.Game.KCUP.toString())) {
                if (SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext()) <
                        SharedPreferenceUtils.getRule(getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).size()) {

                    continueGame.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String continueContent = getString(R.string.continue_game) + "\n" +
                                    (String.valueOf(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext()) + 1)) + " / " +
                                    String.valueOf(SharedPreferenceUtils.getRule(getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).size());

                            continueGame.setText(continueContent);

                            if (SharedPreferenceUtils.getTypeGame(getActivity().getApplicationContext()).equals(GameUtils.Game.KCUP.toString())) {
                                if (SharedPreferenceUtils.getRule(
                                        getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                                        .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                                        .equals(GameUtils.Type.NO_NEED_PLAYER.toString())) {
                                    Intent k = new Intent(getActivity(), NoNeedPlayerActivity.class);
                                    k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
                                    startActivity(k);
                                    getActivity().overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                                } else if (SharedPreferenceUtils.getRule(
                                        getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                                        .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                                        .equals(GameUtils.Type.CHALLENGE.toString())) {
                                    Intent k = new Intent(getActivity(), ChallengeActivity.class);
                                    k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
                                    startActivity(k);
                                    getActivity().overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                                } else if (SharedPreferenceUtils.getRule(
                                        getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                                        .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                                        .equals(GameUtils.Type.CHOICE.toString())) {
                                    Intent k = new Intent(getActivity(), ChoiceActivity.class);
                                    k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
                                    startActivity(k);
                                    getActivity().overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                                } else if (SharedPreferenceUtils.getRule(
                                        getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                                        .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                                        .equals(GameUtils.Type.KCUPS.toString())) {
                                    Intent k = new Intent(getActivity(), KcupActivity.class);
                                    k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
                                    startActivity(k);
                                    getActivity().overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                                } else if (SharedPreferenceUtils.getRule(
                                        getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                                        .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                                        .equals(GameUtils.Type.NEW_RULE.toString())) {
                                    Intent k = new Intent(getActivity(), NewRuleActivity.class);
                                    k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
                                    startActivity(k);
                                    getActivity().overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                                } else if (SharedPreferenceUtils.getRule(
                                        getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                                        .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                                        .equals(GameUtils.Type.NEW_RULE_NEXT.toString())) {
                                    Intent k = new Intent(getActivity(), NewRuleNextActivity.class);
                                    k.putExtra(GameUtils.EXTRA_ANIMATION_BEGIN, true);
                                    startActivity(k);
                                    getActivity().overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
                                }
                            }
                        }
                    });
                }
            }
        } else {
            continueGame.setVisibility(View.GONE);
        }

        newNormalGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferenceUtils.setTypeGame(getActivity().getApplicationContext(), GameUtils.Game.KCUP.toString());
                SharedPreferenceUtils.setPositionGame(getActivity().getApplicationContext(), 0);
                Intent k = new Intent(getActivity(), PlayerActivity.class);
                k.putExtra(EXTRA_TYPE, GameUtils.Game.KCUP.toString());
                startActivity(k);
            }
        });

        newKingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferenceUtils.setTypeGame(getActivity().getApplicationContext(), GameUtils.Game.KINGKCUP.toString());
                SharedPreferenceUtils.setPositionGame(getActivity().getApplicationContext(), 0);
                Intent k = new Intent(getActivity(), PlayerKingActivity.class);
                k.putExtra(EXTRA_TYPE, GameUtils.Game.KINGKCUP.toString());
                startActivity(k);
            }
        });

        newBerserkGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<BerserkRule> ruleList = GameUtils.createBerserkGame(getActivity().getApplicationContext());

                SharedPreferenceUtils.saveBerserkRuleGame(getActivity().getApplicationContext(), ruleList, SharedPreferenceUtils.PREFS_BERSERK_RULE_GAME);
                SharedPreferenceUtils.setPositionGame(getActivity().getApplicationContext(), 0);
                SharedPreferenceUtils.setTypeGame(getActivity().getApplicationContext(), GameUtils.Game.BERSERK.toString());
                SharedPreferenceUtils.setRoundGame(getActivity().getApplicationContext(), GameUtils.Type.BERSERK_QUESTION.toString());

                Intent k = new Intent(getActivity(), BeginGameBerserkActivity.class);
                startActivity(k);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        bindview();
    }

}
