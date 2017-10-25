package utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import identity.BerserkRule;
import identity.Rule;

public class SharedPreferenceUtils {

    private static final String KEY = "PLAYER_KEY";
    public static final String PREFS_PLAYER = "PREFS_PLAYER";
    private static final String KEY_POSITION_GAME = "POSITION_GAME_KEY";
    private static final String PREFS_POSITION_GAME = "PREFS_POSITION_GAME";
    private static final String KEY_TYPE_GAME = "KEY_TYPE_GAME";
    public static final String PREFS_TYPE_GAME = "PREFS_TYPE_GAME";
    public static final String PREFS_ROUND_GAME = "PREFS_ROUND_GAME";
    private static final String KEY_ROUND_GAME = "KEY_ROUND_GAME";
    public static final String PREFS_RULE = "PREFS_RULE";
    public static final String PREFS_BERSERK_RULE = "PREFS_BERSERK_RULE";
    public static final String PREFS_BERSERK_RULE_GAME = "PREFS_BERSERK_RULE_GAME";

    /**** Player ****/
    public static List<String> getAllPlayer(Context context, String key) {

        if (context == null) {
            return null;
        }

        SharedPreferences settings;
        List<String> cardList;

        settings = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);

        if (settings.contains(key)) {
            String jsonReview = settings.getString(key, null);
            Gson gson = new Gson();
            String[] reviewItems = gson.fromJson(jsonReview,
                    String[].class);

            cardList = Arrays.asList(reviewItems);
            cardList = new ArrayList<>(cardList);
        } else
            return null;

        return cardList;
    }

    public static void savePlayer(Context context, List<String> cardList, String key) {

        if (context == null) {
            return;
        }

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonAgence = gson.toJson(cardList);

        editor.putString(key, jsonAgence);
        editor.apply();
    }

    /**** Position game ****/
    public static void setPositionGame(Context context, int positionGame) {
        if (context != null) {
            SharedPreferences sharedPref = context.getSharedPreferences(KEY_POSITION_GAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(PREFS_POSITION_GAME, positionGame);
            editor.apply();
        }
    }

    public static int getPositionGame(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(KEY_POSITION_GAME, Context.MODE_PRIVATE);
        return sharedPref.getInt(PREFS_POSITION_GAME, 0);
    }

    /**** Type game ****/
    public static void setTypeGame(Context context, String typeGame) {
        if (context != null) {
            SharedPreferences sharedPref = context.getSharedPreferences(KEY_TYPE_GAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(PREFS_TYPE_GAME, typeGame);
            editor.apply();
        }
    }

    public static String getTypeGame(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(KEY_TYPE_GAME, Context.MODE_PRIVATE);
        return sharedPref.getString(PREFS_TYPE_GAME, null);
    }

    /**** Type round ****/
    public static void setRoundGame(Context context, String roundGame) {
        if (context != null) {
            SharedPreferences sharedPref = context.getSharedPreferences(KEY_ROUND_GAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(PREFS_ROUND_GAME, roundGame);
            editor.apply();
        }
    }

    public static String getRoundGame(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(KEY_ROUND_GAME, Context.MODE_PRIVATE);
        return sharedPref.getString(PREFS_ROUND_GAME, null);
    }

    /**** Rule ****/
    public static List<Rule> getRule(Context context, String key) {

        if (context == null) {
            return null;
        }

        SharedPreferences settings;
        List<Rule> cardList;

        settings = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);

        if (settings.contains(key)) {
            String jsonReview = settings.getString(key, null);
            Gson gson = new Gson();
            Rule[] reviewItems = gson.fromJson(jsonReview,
                    Rule[].class);

            cardList = Arrays.asList(reviewItems);
            cardList = new ArrayList<>(cardList);
        } else
            return null;

        return cardList;
    }

    public static void saveRule(Context context, List<Rule> ruleList, String key) {

        if (context == null) {
            return;
        }

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonAgence = gson.toJson(ruleList);

        editor.putString(key, jsonAgence);
        editor.apply();
    }

    /**** Berserk Rule ****/
    public static List<BerserkRule> getBerserkRule(Context context, String key) {

        if (context == null) {
            return null;
        }

        SharedPreferences settings;
        List<BerserkRule> cardList;

        settings = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);

        if (settings.contains(key)) {
            String jsonReview = settings.getString(key, null);
            Gson gson = new Gson();
            BerserkRule[] reviewItems = gson.fromJson(jsonReview,
                    BerserkRule[].class);

            cardList = Arrays.asList(reviewItems);
            cardList = new ArrayList<>(cardList);
        } else
            return null;

        return cardList;
    }

    public static void saveBerserkRule(Context context, List<BerserkRule> ruleList, String key) {

        if (context == null) {
            return;
        }

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonAgence = gson.toJson(ruleList);

        editor.putString(key, jsonAgence);
        editor.apply();
    }

    /**** Berserk Rule ****/
    public static List<BerserkRule> getBerserkRuleGame(Context context, String key) {

        if (context == null) {
            return null;
        }

        SharedPreferences settings;
        List<BerserkRule> cardList;

        settings = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);

        if (settings.contains(key)) {
            String jsonReview = settings.getString(key, null);
            Gson gson = new Gson();
            BerserkRule[] reviewItems = gson.fromJson(jsonReview,
                    BerserkRule[].class);

            cardList = Arrays.asList(reviewItems);
            cardList = new ArrayList<>(cardList);
        } else
            return null;

        return cardList;
    }

    public static void saveBerserkRuleGame(Context context, List<BerserkRule> ruleList, String key) {

        if (context == null) {
            return;
        }

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonAgence = gson.toJson(ruleList);

        editor.putString(key, jsonAgence);
        editor.apply();
    }
}
