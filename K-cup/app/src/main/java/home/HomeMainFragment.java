package home;

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
import game.KcupActivity;
import game.NewRuleActivity;
import game.NewRuleNextActivity;
import game.NoNeedPlayerActivity;
import player.PlayerActivity;
import utils.GameUtils;
import utils.SharedPreferenceUtils;

import static utils.GameUtils.EXTRA_TYPE;

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

        LinearLayout newNormalGame = (LinearLayout) view.findViewById(R.id.new_game_normal);
        TextView titleNormalGame = (TextView) view.findViewById(R.id.new_normal_game_title);
        TextView contentNormalGame = (TextView) view.findViewById(R.id.normal_game_info);
        titleNormalGame.setTypeface(typeface);
        contentNormalGame.setTypeface(typeface);

        Button continueGame = (Button) view.findViewById(R.id.continue_game);
        continueGame.setTypeface(typeface);

        LinearLayout newAfterGame = (LinearLayout) view.findViewById(R.id.new_game_after);
        TextView titleAfterGame = (TextView) view.findViewById(R.id.new_after_game_title);
        TextView contentAfterGame = (TextView) view.findViewById(R.id.after_game_info);
        titleAfterGame.setTypeface(typeface);
        contentAfterGame.setTypeface(typeface);

        LinearLayout newBerserkGame = (LinearLayout) view.findViewById(R.id.new_game_berserk);
        TextView titleBerserkGame = (TextView) view.findViewById(R.id.berserk_game_title);
        TextView contentBerserkGame = (TextView) view.findViewById(R.id.berserk_game_info);
        titleBerserkGame.setTypeface(typeface);
        contentBerserkGame.setTypeface(typeface);

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

        if (SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext()) > 0
                && SharedPreferenceUtils.getPositionGame(getActivity().getApplicationContext()) <
                SharedPreferenceUtils.getRule(getActivity().getApplicationContext(), SharedPreferenceUtils.PREFS_RULE).size()) {
            continueGame.setVisibility(View.VISIBLE);
            continueGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
            });
        } else {
            continueGame.setVisibility(View.GONE);
        }

        newNormalGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(getActivity(), PlayerActivity.class);
                k.putExtra(EXTRA_TYPE, GameUtils.Game.KCUP.toString());
                startActivity(k);
            }
        });

        newAfterGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(getActivity(), PlayerActivity.class);
                k.putExtra(EXTRA_TYPE, GameUtils.Game.AFTER_KCUP.toString());
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
        try {
            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            String facebookUrl = getFacebookPageURL(getActivity());
            facebookIntent.setData(Uri.parse(facebookUrl));
            startActivity(facebookIntent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), getString(R.string.error_open_facebook), Toast.LENGTH_SHORT).show();
        }

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
