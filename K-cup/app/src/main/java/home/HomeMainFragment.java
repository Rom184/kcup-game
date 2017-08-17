package home;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kcup.drinkgame.k_cup.R;

import game.ChallengeActivity;
import game.ChoiceActivity;
import game.NewRuleActivity;
import game.NewRuleNextActivity;
import game.NoNeedPlayerActivity;
import player.PlayerActivity;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

public class HomeMainFragment extends Fragment {

    public static String FACEBOOK_URL = "https://www.facebook.com/KCupGame/";
    public static String FACEBOOK_PAGE_ID = "KCupGame";

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    private void bindview() {

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Medium.ttf");

        Button newNormalGame = (Button) view.findViewById(R.id.new_game_normal);
        newNormalGame.setTypeface(typeface);

        TextView infoNormalGame = (TextView) view.findViewById(R.id.normal_game_info);
        infoNormalGame.setTypeface(typeface);

        Button continueGame = (Button) view.findViewById(R.id.continue_game);
        continueGame.setTypeface(typeface);

        Button newBerserkGame = (Button) view.findViewById(R.id.new_game_berserk);
        newBerserkGame.setTypeface(typeface);

        TextView infoBerserkGame = (TextView) view.findViewById(R.id.berserk_game_info);
        infoBerserkGame.setTypeface(typeface);

        ImageView like = (ImageView) view.findViewById(R.id.like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFacebookPage();
            }
        });

        ImageView mail = (ImageView) view.findViewById(R.id.mail);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        LinearLayout continueArea = (LinearLayout) view.findViewById(R.id.continue_area);

        if (SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext()) > 0) {
            continueArea.setVisibility(View.VISIBLE);
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
            continueArea.setVisibility(View.GONE);
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

    private void goToFacebookPage() {
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(getActivity());
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }

    private void sendMail() {
        String[] TO = {"kcup-drink-game@hotmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject_mail));

        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_mail)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), getString(R.string.error_send_mail), Toast.LENGTH_SHORT).show();
        }
    }

}
