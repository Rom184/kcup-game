package utils;

import android.content.Context;
import android.util.Log;

import com.kcup.drinkgame.k_cup.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import identity.BerserkRule;
import identity.Rule;

public class GameUtils {

    public static final String EXTRA_TYPE = "extra_type";

    public enum Game {
        KCUP,
        KINGKCUP,
        BERSERK
    }

    public enum KingType {
        TREFLE,
        PIQUE,
        CARREAU,
        COEUR
    }

    public enum Type {
        NO_NEED_PLAYER,
        CHALLENGE,
        CHOICE,
        NEW_RULE,
        NEW_RULE_NEXT,
        KCUPS,
        BERSERK_QUESTION,
        BERSERK_BAD_ANSWER,
        BERSERK_GOOD_ANSWER
    }

    public static List<Rule> createCustomKcupGame(Context context, int nbQuestion, int maxDrinks) {

        List<String> listPlayer = SharedPreferenceUtils.getAllPlayer(context.getApplicationContext(), SharedPreferenceUtils.PREFS_PLAYER);
        List<Rule> newRule = new ArrayList<>();

        List<String> noNeedPlayer = getNoNeedPlayer(context);
        List<String> challenge = getChallenge(context);
        List<String> choice = getChoice(context);
        List<String> kcup = getKcup(context);

        List<Integer> randomForNewRule = getRandomForNewRule(context);
        List<String> newRuleAll = getNewRule(context);
        List<String> newRuleNext = getNewRuleNext(context);
        int positionNewRuleNext = 0;

        Log.e("noNeedPlayer", String.valueOf(noNeedPlayer.size()));
        Log.e("challenge", String.valueOf(challenge.size()));
        Log.e("choice", String.valueOf(choice.size()));
        Log.e("randomForNewRule", String.valueOf(newRuleAll.size()));
        Log.e("randomForNewRuleNext", String.valueOf(newRuleNext.size()));

        int test = noNeedPlayer.size() + challenge.size() + choice.size() + newRuleAll.size() + newRuleNext.size();

        Log.e("total", String.valueOf(test));

        boolean isPossibleNewRule = false;

        List<Integer> newKcup = new ArrayList<>();
        int positionWhenKcupPossible = 8;
        boolean isPossibleNewKcup = false;

        Random r = new Random();
        int nbRule = r.nextInt(nbQuestion - 20) + 25;
        int positionWhenNewRulePossible = 2;
        int nbStopNewRule = nbRule - 6;

        for (int a = 0; a < nbRule; a++) {

            if (a == positionWhenNewRulePossible) {
                isPossibleNewRule = true;
            }

            if (a == positionWhenKcupPossible && newKcup.size() < 2) {
                isPossibleNewKcup = true;
            }

            int randomNewRule = r.nextInt(11) + 1;

            if (a < nbStopNewRule && isPossibleNewRule && randomNewRule < 3) {
                newRule.add(new Rule(getRandomSoftDrinks(context, newRuleAll.get(randomForNewRule.get(0)), maxDrinks + 2), Type.NEW_RULE.toString()));
                isPossibleNewRule = false;
                positionNewRuleNext = a + (r.nextInt(3) + 5);
            } else if (!isPossibleNewRule && a > 0 && a == positionNewRuleNext) {
                newRule.add(new Rule(getRandomSoftDrinks(context, newRuleNext.get(randomForNewRule.get(0)), maxDrinks + 2), Type.NEW_RULE_NEXT.toString()));
                positionNewRuleNext = 0;
                randomForNewRule.remove(0);
                isPossibleNewRule = true;
            } else {

                int randomForKcup = r.nextInt(100) + 1;

                if (isPossibleNewKcup && randomForKcup > 85) {
                    newRule.add(new Rule(getSelectedWithTwoPlayer(context, kcup.get(0), listPlayer, maxDrinks + 2), Type.KCUPS.toString()));
                    kcup.remove(0);
                    newKcup.add(a);
                    /*newRule.add(new Rule(getSelectedWithTwoPlayer(context, context.getString(R.string.kcup_content), listPlayer, maxDrinks + 2), Type.KCUPS.toString()));
                    newKcup.add(a);*/
                    positionWhenKcupPossible = a + 8;
                    isPossibleNewKcup = false;
                }

                int randomType = r.nextInt(3) + 1;

                if (randomType == 1) {
                    Collections.shuffle(noNeedPlayer);
                    newRule.add(new Rule(getRandomSoftDrinks(context, noNeedPlayer.get(0), maxDrinks), Type.NO_NEED_PLAYER.toString()));
                    noNeedPlayer.remove(0);
                } else if (randomType == 2) {
                    Collections.shuffle(challenge);
                    newRule.add(new Rule(getSelectedWithTwoPlayer(context, challenge.get(0), listPlayer, maxDrinks), Type.CHALLENGE.toString()));
                    challenge.remove(0);
                } else {
                    Collections.shuffle(choice);
                    newRule.add(new Rule(getChallengeWithPlayer(context, choice.get(0), listPlayer, maxDrinks), Type.CHOICE.toString()));
                    choice.remove(0);
                }
            }
        }

        return newRule;
    }

    public static List<BerserkRule> createBerserkGame(Context context) {

        List<BerserkRule> berserkRules = SharedPreferenceUtils.getBerserkRule(context, SharedPreferenceUtils.PREFS_BERSERK_RULE);
        List<BerserkRule> newRule = new ArrayList<>();

        Collections.shuffle(berserkRules);

        Random r = new Random();

        int nbRule = 5;

        for (int a = 0; a < nbRule; a++) {

            BerserkRule berserkRule = new BerserkRule();

            berserkRule.setContent(berserkRules.get(a).getContent());
            berserkRule.setGoodQuestion(berserkRules.get(a).getGoodQuestion());
            berserkRule.setBadQuestion(berserkRules.get(a).getBadQuestion());

            List<String> goodAnswers = berserkRules.get(a).getGoodAnswers();
            Collections.shuffle(goodAnswers);
            int randomAnswer = r.nextInt(5) + 2;
            berserkRule.setGoodAnswer(getRandomDrinks(context, goodAnswers.get(0), randomAnswer));
            int randomGoodAnswer = r.nextInt(3) + 1;
            berserkRule.setRandomGoodAnswer(randomGoodAnswer);

            List<String> badAnswers = berserkRules.get(a).getBadAnswers();
            Collections.shuffle(badAnswers);
            int randomAnswer2 = (r.nextInt(4)) + 1;
            berserkRule.setBadAnswer(getRandomDrinks(context, badAnswers.get(0), randomAnswer2));
            int randomBadAnswer = (r.nextInt(6)) + 2;
            berserkRule.setRandomBadAnswer(randomBadAnswer);

            newRule.add(berserkRule);
        }

        return newRule;
    }

    private static List<String> getNoNeedPlayer(Context context) {
        String[] noNeedPlayerArray = context.getResources().getStringArray(R.array.no_need_player_array);

        List<String> noNeedPlayer = new ArrayList<>();
        Collections.addAll(noNeedPlayer, noNeedPlayerArray);
        return noNeedPlayer;
    }

    private static List<String> getChallenge(Context context) {
        String[] challengeArray = context.getResources().getStringArray(R.array.challenge_array);

        List<String> challenge = new ArrayList<>();
        Collections.addAll(challenge, challengeArray);
        return challenge;
    }

    private static String getRandomDrinks(Context context, String rule, int nbRule) {
        String tmp = rule;

        String newDrinks;

        if (nbRule > 1) {
            newDrinks = String.valueOf(nbRule) + " " + context.getString(R.string.drinks);
        } else {
            newDrinks = String.valueOf(nbRule) + " " + context.getString(R.string.drink);
        }

        tmp = tmp.replace("%b", newDrinks);
        return tmp;
    }

    private static String getRandomSoftDrinks(Context context, String rule, int nbRandom) {
        String tmp = rule;
        Random r = new Random();

        String newDrinks;

        int nbRule = r.nextInt(nbRandom) + 1;

        if (nbRule > 1) {
            newDrinks = String.valueOf(nbRule) + " " + context.getString(R.string.drinks);
        } else {
            newDrinks = String.valueOf(nbRule) + " " + context.getString(R.string.drink);
        }

        tmp = tmp.replace("%b", newDrinks);
        return tmp;
    }

    private static String getRandomSoftDrinksBerserk(Context context, String rule, int nbRandom) {
        String tmp = rule;
        Random r = new Random();

        String newDrinks;

        int nbRule = r.nextInt(nbRandom) + 1;

        if (nbRule > 1) {
            newDrinks = String.valueOf(nbRule) + " " + context.getString(R.string.drinks);
        } else {
            newDrinks = String.valueOf(nbRule) + " " + context.getString(R.string.drink);
        }

        tmp = tmp.replace("%b", context.getString(R.string.all));
        return tmp;
    }

    private static String getChallengeWithPlayer(Context context, String challenge, List<String> listPlayer, int nbRandom) {
        String tmp = challenge;
        tmp = tmp.replace("%s", getRandomPlayer(listPlayer));

        Random r = new Random();

        String newDrinks;

        int nbRule = r.nextInt(nbRandom) + 1;

        if (nbRule > 1) {
            newDrinks = String.valueOf(nbRule) + " " + context.getString(R.string.drinks);
        } else {
            newDrinks = String.valueOf(nbRule) + " " + context.getString(R.string.drink);
        }

        tmp = tmp.replace("%b", newDrinks);

        return tmp;
    }

    private static String getSelectedWithTwoPlayer(Context context, String rule, List<String> listPlayer, int nbRandom) {
        String player1 = getRandomPlayer(listPlayer);
        String player2 = getRandomPlayer(listPlayer);

        while (player2.equals(player1)) {
            player2 = getRandomPlayer(listPlayer);
        }

        String newDrinks;
        Random r = new Random();
        int nbRule = r.nextInt(nbRandom) + 1;

        if (nbRule > 1) {
            newDrinks = String.valueOf(nbRule) + " " + context.getString(R.string.drinks);
        } else {
            newDrinks = String.valueOf(nbRule) + " " + context.getString(R.string.drink);
        }

        String ruleWithPlayer = rule;
        ruleWithPlayer = ruleWithPlayer.replace("%s", player1);
        ruleWithPlayer = ruleWithPlayer.replace("%a", player2);
        ruleWithPlayer = ruleWithPlayer.replace("%b", newDrinks);
        return ruleWithPlayer;
    }

    private static List<String> getChoice(Context context) {
        String[] choiceArray = context.getResources().getStringArray(R.array.choice_array);
        List<String> choice = new ArrayList<>();
        Collections.addAll(choice, choiceArray);
        return choice;
    }

    private static List<String> getKcup(Context context) {
        String[] kcupArray = context.getResources().getStringArray(R.array.kcup_array);
        List<String> kcup = new ArrayList<>();
        Collections.addAll(kcup, kcupArray);
        return kcup;
    }

    private static List<Integer> getRandomForNewRule(Context context) {
        List<Integer> randomForNewRule = new ArrayList<>();
        for (int a = 0; a < context.getResources().getStringArray(R.array.new_rule_array).length; a++) {
            randomForNewRule.add(a);
        }
        Collections.shuffle(randomForNewRule);
        return randomForNewRule;
    }

    private static List<String> getNewRule(Context context) {
        String[] newRuleArray = context.getResources().getStringArray(R.array.new_rule_array);
        List<String> newRule = new ArrayList<>();
        Collections.addAll(newRule, newRuleArray);
        return newRule;
    }

    private static List<String> getNewRuleNext(Context context) {
        String[] newRuleNextArray = context.getResources().getStringArray(R.array.new_rule_next_array);
        List<String> newRuleNext = new ArrayList<>();
        Collections.addAll(newRuleNext, newRuleNextArray);
        return newRuleNext;
    }

    public static String getRandomPlayer(List<String> listPlayer) {

        if (listPlayer != null && !listPlayer.isEmpty()) {
            Collections.shuffle(listPlayer);
            return listPlayer.get(0);
        }
        return "";
    }

}
