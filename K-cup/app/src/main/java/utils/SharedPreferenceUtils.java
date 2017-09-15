package utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import identity.Rule;

public class SharedPreferenceUtils {

    private static final String KEY = "PLAYER_KEY";
    public static final String PREFS_PLAYER = "PREFS_PLAYER";
    private static final String KEY_POSITION_GAME = "POSITION_GAME_KEY";
    private static final String PREFS_POSITION_GAME = "PREFS_POSITION_GAME";
    private static final String PREFS_TYPE_GAME = "PREFS_TYPE_GAME";
    public static final String PREFS_RULE = "PREFS_RULE";

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
            SharedPreferences sharedPref = context.getSharedPreferences(KEY_POSITION_GAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(PREFS_POSITION_GAME, typeGame);
            editor.apply();
        }
    }

    public static String getTypeGame(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(KEY_POSITION_GAME, Context.MODE_PRIVATE);
        return sharedPref.getString(PREFS_POSITION_GAME, null);
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

}
