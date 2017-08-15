package home;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kcup.drinkgame.k_cup.R;

import java.util.List;

import game.ChallengeActivity;
import game.ChoiceActivity;
import game.NewRuleActivity;
import game.NewRuleNextActivity;
import game.NoNeedPlayerActivity;
import identity.Rule;
import player.PlayerActivity;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class HomeMainFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    private void bindview() {

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Medium.ttf");

        Button newNormalGame = (Button) view.findViewById(R.id.new_game);
        newNormalGame.setTypeface(typeface);

        Button continueGame = (Button) view.findViewById(R.id.continue_game);
        continueGame.setTypeface(typeface);

        if (SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext()) > 0) {
            continueGame.setVisibility(View.VISIBLE);
            continueGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SharedPreferenceUtils.getRule(
                            getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                            .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                            .equals(GameUtils.Type.NO_NEED_PLAYER.toString())) {
                        Intent k = new Intent(getActivity(), NoNeedPlayerActivity.class);
                        startActivity(k);
                    } else if (SharedPreferenceUtils.getRule(
                            getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                            .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                            .equals(GameUtils.Type.CHALLENGE.toString())) {
                        Intent k = new Intent(getActivity(), ChallengeActivity.class);
                        startActivity(k);
                    } else if (SharedPreferenceUtils.getRule(
                            getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                            .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                            .equals(GameUtils.Type.CHOICE.toString())) {
                        Intent k = new Intent(getActivity(), ChoiceActivity.class);
                        startActivity(k);
                    } else if (SharedPreferenceUtils.getRule(
                            getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                            .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                            .equals(GameUtils.Type.NEW_RULE.toString())) {
                        Intent k = new Intent(getActivity(), NewRuleActivity.class);
                        startActivity(k);
                    } else if (SharedPreferenceUtils.getRule(
                            getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE)
                            .get(SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext())).getType()
                            .equals(GameUtils.Type.NEW_RULE_NEXT.toString())) {
                        Intent k = new Intent(getActivity(), NewRuleNextActivity.class);
                        startActivity(k);
                    }
                }
            });
        } else {
            continueGame.setVisibility(View.GONE);
        }

        newNormalGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(getActivity(), PlayerActivity.class);
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
