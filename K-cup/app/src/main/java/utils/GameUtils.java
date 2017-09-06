package utils;

import android.content.Context;
import android.util.Log;

import com.kcup.drinkgame.k_cup.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import identity.Rule;

public class GameUtils {

    public enum Type {
        NO_NEED_PLAYER,
        CHALLENGE,
        CHOICE,
        NEW_RULE,
        NEW_RULE_NEXT,
        KCUPS
    }

    public static List<Rule> createKcupGame(Context context) {

        List<String> listPlayer = SharedPreferenceUtils.getAllPlayer(context.getApplicationContext(), SharedPreferenceUtils.PREFS_PLAYER);
        List<Rule> newRule = new ArrayList<>();

        List<String> noNeedPlayer = getNoNeedPlayer(context);
        List<String> challenge = getChallenge(context);
        List<String> choice = getChoice(context);

        List<Integer> randomForNewRule = getRandomForNewRule(context);
        List<String> newRuleAll = getNewRule(context);
        List<String> newRuleNext = getNewRuleNext(context);
        int positionNewRuleNext = 0;

        Log.e("noNeedPlayer", String.valueOf(noNeedPlayer.size()));
        Log.e("challenge", String.valueOf(challenge.size()));
        Log.e("choice", String.valueOf(choice.size()));
        Log.e("randomForNewRule", String.valueOf(randomForNewRule.size()));

        int test = noNeedPlayer.size() + challenge.size() + choice.size() + randomForNewRule.size();

        Log.e("total", String.valueOf(test));

        boolean isPossibleNewRule = false;

        List<Integer> newKcup = new ArrayList<>();
        int positionWhenKcupPossible = 8;
        boolean isPossibleNewKcup = false;

        Random r = new Random();
        int nbRule = r.nextInt(40 - 25) + 25;
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
                newRule.add(new Rule(getRandomSoftDrinks(context, newRuleAll.get(randomForNewRule.get(0)), 8), Type.NEW_RULE.toString()));
                isPossibleNewRule = false;
                positionNewRuleNext = a + (r.nextInt(3) + 5);
            } else if (!isPossibleNewRule && a > 0 && a == positionNewRuleNext) {
                newRule.add(new Rule(getRandomSoftDrinks(context, newRuleNext.get(randomForNewRule.get(0)), 8), Type.NEW_RULE_NEXT.toString()));
                positionNewRuleNext = 0;
                randomForNewRule.remove(0);
                isPossibleNewRule = true;
            } else {

                int randomForKcup = r.nextInt(100) + 1;

                if (isPossibleNewKcup && randomForKcup > 85) {
                    newRule.add(new Rule(getSelectedWithTwoPlayer(context, context.getString(R.string.kcup_content), listPlayer, 6), Type.KCUPS.toString()));
                    newKcup.add(a);
                    positionWhenKcupPossible = a + 8;
                    isPossibleNewKcup = false;
                }

                int randomType = r.nextInt(3) + 1;

                if (randomType == 1) {
                    Collections.shuffle(noNeedPlayer);
                    newRule.add(new Rule(getRandomSoftDrinks(context, noNeedPlayer.get(0), 4), Type.NO_NEED_PLAYER.toString()));
                    noNeedPlayer.remove(0);
                } else if (randomType == 2) {
                    Collections.shuffle(challenge);
                    newRule.add(new Rule(getSelectedWithTwoPlayer(context, challenge.get(0), listPlayer, 4), Type.NO_NEED_PLAYER.toString()));
                    challenge.remove(0);
                } else {
                    Collections.shuffle(choice);
                    newRule.add(new Rule(getChallengeWithPlayer(context, choice.get(0), listPlayer, 4), Type.CHOICE.toString()));
                    choice.remove(0);
                }
            }
        }

        return newRule;
    }

    /*public static List<Rule> createKcupGame(Context context) {

        List<String> listPlayer = SharedPreferenceUtils.getAllPlayer(context.getApplicationContext(), SharedPreferenceUtils.PREFS_PLAYER);
        List<Rule> newRule = new ArrayList<>();

        List<String> noNeedPlayer = getNoNeedPlayer(context);
        List<String> challenge = getChallenge(context);
        List<String> choice = getChoice(context);

        List<Integer> randomForNewRule = getRandomForNewRule(context);
        List<String> newRuleAll = getNewRule(context);
        List<String> newRuleNext = getNewRuleNext(context);
        int positionNewRuleNext = 0;

        boolean isPossibleNewRule = false;

        Random r = new Random();
        int nbRule = r.nextInt(40 - 25) + 25;
        int positionWhenNewRulePossible = 2;
        int nbStopNewRule = nbRule - 6;

        for (int a = 0; a < nbRule; a++) {

            if (a == positionWhenNewRulePossible) {
                isPossibleNewRule = true;
            }

            int randomNewRule = r.nextInt(11) + 1;

            if (a < nbStopNewRule && isPossibleNewRule && randomNewRule < 3) {
                newRule.add(new Rule(getRandomSoftDrinks(context, newRuleAll.get(randomForNewRule.get(0)), 8), Type.NEW_RULE.toString()));
                isPossibleNewRule = false;
                positionNewRuleNext = a + (r.nextInt(3) + 5);
            } else if (!isPossibleNewRule && a > 0 && a == positionNewRuleNext) {
                newRule.add(new Rule(getRandomSoftDrinks(context, newRuleNext.get(randomForNewRule.get(0)), 8), Type.NEW_RULE_NEXT.toString()));
                positionNewRuleNext = 0;
                randomForNewRule.remove(0);
                isPossibleNewRule = true;
            } else {
                int randomType = r.nextInt(3) + 1;

                if (randomType == 1) {
                    Collections.shuffle(noNeedPlayer);
                    newRule.add(new Rule(getRandomSoftDrinks(context, noNeedPlayer.get(0), 4), Type.NO_NEED_PLAYER.toString()));
                    noNeedPlayer.remove(0);
                } else if (randomType == 2) {
                    Collections.shuffle(challenge);
                    newRule.add(new Rule(getSelectedWithTwoPlayer(context, challenge.get(0), listPlayer, 4), Type.NO_NEED_PLAYER.toString()));
                    challenge.remove(0);
                } else {
                    Collections.shuffle(choice);
                    newRule.add(new Rule(getChallengeWithPlayer(context, choice.get(0), listPlayer, 4), Type.CHOICE.toString()));
                    choice.remove(0);
                }
            }
        }

        return newRule;
    }*/

    /*public static List<Rule> createRandomGame(Context context) {

        List<String> listPlayer = SharedPreferenceUtils.getAllPlayer(context.getApplicationContext(), SharedPreferenceUtils.PREFS_PLAYER);
        List<Rule> newRule = new ArrayList<>();

        List<String> noNeedPlayer = getNoNeedPlayer(context);
        List<String> challenge = getChallenge(context);
        List<String> choice = getChoice(context);
        List<String> selectedPlayer = getSelectedPlayer(context);

        Random r = new Random();
        int nbRule = r.nextInt(30 - 25) + 25;

        for (int a = 0; a < nbRule; a++) {
            int randomType = r.nextInt(7) + 1;

            if (randomType == 1 || randomType == 5) {
                Collections.shuffle(noNeedPlayer);
                //newRule.add(new Rule(getRandomSoftDrinks(noNeedPlayer.get(0), Type.NO_NEED_PLAYER.toString())));
                noNeedPlayer.remove(0);
            } else if (randomType == 2 || randomType == 7) {
                Collections.shuffle(challenge);
                newRule.add(new Rule(getChallengeWithPlayer(context, challenge.get(0), listPlayer), Type.CHALLENGE.toString()));
                challenge.remove(0);
            } else if (randomType == 3) {
                Collections.shuffle(choice);
                newRule.add(new Rule(choice.get(0), Type.CHOICE.toString()));
                choice.remove(0);
            } else if (randomType == 4 || randomType == 6) {
                Collections.shuffle(selectedPlayer);
                newRule.add(new Rule(getSelectedWithTwoPlayer(selectedPlayer.get(0), listPlayer), Type.NO_NEED_PLAYER.toString()));
                selectedPlayer.remove(0);
            }
        }

        return newRule;

    }

    public static List<Rule> createRandomLongGame(Context context) {

        List<String> listPlayer = SharedPreferenceUtils.getAllPlayer(context.getApplicationContext(), SharedPreferenceUtils.PREFS_PLAYER);
        List<Rule> newRule = new ArrayList<>();

        List<String> noNeedPlayer = getNoNeedPlayer(context);
        List<String> challenge = getChallenge(context);
        List<String> choice = getChoice(context);
        List<String> selectedPlayer = getSelectedPlayer(context);

        Random r = new Random();
        int nbRule = r.nextInt(60 - 50) + 50;

        for (int a = 0; a < nbRule; a++) {
            int randomType = r.nextInt(7) + 1;

            if (randomType == 1 || randomType == 5) {
                Collections.shuffle(noNeedPlayer);
                newRule.add(new Rule(noNeedPlayer.get(0), Type.NO_NEED_PLAYER.toString()));
                noNeedPlayer.remove(0);
            } else if (randomType == 2 || randomType == 7) {
                Collections.shuffle(challenge);
                newRule.add(new Rule(getChallengeWithPlayer(context, challenge.get(0), listPlayer), Type.CHALLENGE.toString()));
                challenge.remove(0);
            } else if (randomType == 3) {
                Collections.shuffle(choice);
                newRule.add(new Rule(choice.get(0), Type.CHOICE.toString()));
                choice.remove(0);
            } else if (randomType == 4 || randomType == 6) {
                Collections.shuffle(selectedPlayer);
                newRule.add(new Rule(getSelectedWithTwoPlayer(selectedPlayer.get(0), listPlayer), Type.NO_NEED_PLAYER.toString()));
                selectedPlayer.remove(0);
            }
        }

        return newRule;

    }

    public static List<Rule> createRandomGameWithNewRule(Context context) {

        List<String> listPlayer = SharedPreferenceUtils.getAllPlayer(context.getApplicationContext(), SharedPreferenceUtils.PREFS_PLAYER);
        List<Rule> newRule = new ArrayList<>();

        List<String> noNeedPlayer = getNoNeedPlayer(context);
        List<String> challenge = getChallenge(context);
        List<String> choice = getChoice(context);
        List<String> selectedPlayer = getSelectedPlayer(context);

        List<Integer> randomForNewRule = getRandomForNewRule(context);
        List<String> newRuleAll = getNewRule(context);
        List<String> newRuleNext = getNewRuleNext(context);
        int positionNewRuleNext = 0;
        boolean isPossibleNewRule = true;

        int nbLenght = noNeedPlayer.size() + challenge.size() + choice.size() + selectedPlayer.size() + newRuleAll.size();

        Log.e("size", String.valueOf(nbLenght));

        Random r = new Random();
        int nbRule = r.nextInt(35 - 30) + 30;

        for (int a = 0; a < nbRule; a++) {
            int randomType;

            if (a < nbRule - 6 && isPossibleNewRule) {
                randomType = r.nextInt(8) + 1;
                if (randomType == 6) {
                    newRule.add(new Rule(newRuleAll.get(randomForNewRule.get(0)), Type.NEW_RULE.toString()));
                    isPossibleNewRule = false;
                    positionNewRuleNext = a + (r.nextInt(4) + 3);
                } else if (randomType == 1 || randomType == 4) {
                    Collections.shuffle(noNeedPlayer);
                    newRule.add(new Rule(noNeedPlayer.get(0), Type.NO_NEED_PLAYER.toString()));
                    noNeedPlayer.remove(0);
                } else if (randomType == 2 || randomType == 7) {
                    Collections.shuffle(challenge);
                    newRule.add(new Rule(getChallengeWithPlayer(context, challenge.get(0), listPlayer), Type.CHALLENGE.toString()));
                    challenge.remove(0);
                } else if (randomType == 3) {
                    Collections.shuffle(choice);
                    newRule.add(new Rule(choice.get(0), Type.CHOICE.toString()));
                    choice.remove(0);
                } else if (randomType == 5 || randomType == 8) {
                    Collections.shuffle(selectedPlayer);
                    newRule.add(new Rule(getSelectedWithTwoPlayer(selectedPlayer.get(0), listPlayer), Type.NO_NEED_PLAYER.toString()));
                    selectedPlayer.remove(0);
                }

            } else {

                if (positionNewRuleNext > 0 && a == positionNewRuleNext) {
                    newRule.add(new Rule(newRuleNext.get(randomForNewRule.get(0)), Type.NEW_RULE_NEXT.toString()));
                    positionNewRuleNext = 0;
                    randomForNewRule.remove(0);
                    isPossibleNewRule = true;

                } else {
                    randomType = r.nextInt(7) + 1;
                    if (randomType == 1 || randomType == 5) {
                        Collections.shuffle(noNeedPlayer);
                        newRule.add(new Rule(noNeedPlayer.get(0), Type.NO_NEED_PLAYER.toString()));
                        noNeedPlayer.remove(0);
                    } else if (randomType == 2 || randomType == 6) {
                        Collections.shuffle(challenge);
                        newRule.add(new Rule(getChallengeWithPlayer(context, challenge.get(0), listPlayer), Type.CHALLENGE.toString()));
                        challenge.remove(0);
                    } else if (randomType == 3) {
                        Collections.shuffle(choice);
                        newRule.add(new Rule(choice.get(0), Type.CHOICE.toString()));
                        choice.remove(0);
                    } else if (randomType == 4 || randomType == 7) {
                        Collections.shuffle(selectedPlayer);
                        newRule.add(new Rule(getSelectedWithTwoPlayer(selectedPlayer.get(0), listPlayer), Type.NO_NEED_PLAYER.toString()));
                        selectedPlayer.remove(0);
                    }
                }
            }
        }
        return newRule;

    }

    public static List<Rule> createRandomGameWithNewRuleLong(Context context) {

        List<String> listPlayer = SharedPreferenceUtils.getAllPlayer(context.getApplicationContext(), SharedPreferenceUtils.PREFS_PLAYER);
        List<Rule> newRule = new ArrayList<>();

        List<String> noNeedPlayer = getNoNeedPlayer(context);
        List<String> challenge = getChallenge(context);
        List<String> choice = getChoice(context);
        List<String> selectedPlayer = getSelectedPlayer(context);

        List<Integer> randomForNewRule = getRandomForNewRule(context);
        List<String> newRuleAll = getNewRule(context);
        List<String> newRuleNext = getNewRuleNext(context);
        int positionNewRuleNext = 0;
        boolean isPossibleNewRule = true;

        int nbLenght = noNeedPlayer.size() + challenge.size() + choice.size() + selectedPlayer.size() + newRuleAll.size();

        Log.e("size", String.valueOf(nbLenght));

        Random r = new Random();
        int nbRule = r.nextInt(70 - 60) + 60;

        for (int a = 0; a < nbRule; a++) {
            int randomType;

            if (a < nbRule - 6 && isPossibleNewRule) {
                randomType = r.nextInt(8) + 1;
                if (randomType == 6) {
                    newRule.add(new Rule(newRuleAll.get(randomForNewRule.get(0)), Type.NEW_RULE.toString()));
                    isPossibleNewRule = false;
                    positionNewRuleNext = a + (r.nextInt(4) + 3);
                } else if (randomType == 1 || randomType == 4) {
                    Collections.shuffle(noNeedPlayer);
                    newRule.add(new Rule(noNeedPlayer.get(0), Type.NO_NEED_PLAYER.toString()));
                    noNeedPlayer.remove(0);
                } else if (randomType == 2 || randomType == 7) {
                    Collections.shuffle(challenge);
                    newRule.add(new Rule(getChallengeWithPlayer(context, challenge.get(0), listPlayer), Type.CHALLENGE.toString()));
                    challenge.remove(0);
                } else if (randomType == 3) {
                    Collections.shuffle(choice);
                    newRule.add(new Rule(choice.get(0), Type.CHOICE.toString()));
                    choice.remove(0);
                } else if (randomType == 5 || randomType == 8) {
                    Collections.shuffle(selectedPlayer);
                    newRule.add(new Rule(getSelectedWithTwoPlayer(selectedPlayer.get(0), listPlayer), Type.NO_NEED_PLAYER.toString()));
                    selectedPlayer.remove(0);
                }

            } else {

                if (positionNewRuleNext > 0 && a == positionNewRuleNext) {
                    newRule.add(new Rule(newRuleNext.get(randomForNewRule.get(0)), Type.NEW_RULE_NEXT.toString()));
                    positionNewRuleNext = 0;
                    randomForNewRule.remove(0);
                    isPossibleNewRule = true;

                } else {
                    randomType = r.nextInt(7) + 1;
                    if (randomType == 1 || randomType == 5) {
                        Collections.shuffle(noNeedPlayer);
                        newRule.add(new Rule(noNeedPlayer.get(0), Type.NO_NEED_PLAYER.toString()));
                        noNeedPlayer.remove(0);
                    } else if (randomType == 2 || randomType == 6) {
                        Collections.shuffle(challenge);
                        newRule.add(new Rule(getChallengeWithPlayer(context, challenge.get(0), listPlayer), Type.CHALLENGE.toString()));
                        challenge.remove(0);
                    } else if (randomType == 3) {
                        Collections.shuffle(choice);
                        newRule.add(new Rule(choice.get(0), Type.CHOICE.toString()));
                        choice.remove(0);
                    } else if (randomType == 4 || randomType == 7) {
                        Collections.shuffle(selectedPlayer);
                        newRule.add(new Rule(getSelectedWithTwoPlayer(selectedPlayer.get(0), listPlayer), Type.NO_NEED_PLAYER.toString()));
                        selectedPlayer.remove(0);
                    }
                }
            }
        }
        return newRule;

    }*/

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

    /*private static List<String> getSelectedPlayer(Context context) {
        String[] selectedPlayerArray = context.getResources().getStringArray(R.array.selected_player_array);
        List<String> selectedPlayer = new ArrayList<>();
        Collections.addAll(selectedPlayer, selectedPlayerArray);
        return selectedPlayer;
    }*/

    private static String getRandomPlayer(List<String> listPlayer) {

        if (listPlayer != null && !listPlayer.isEmpty()) {
            Collections.shuffle(listPlayer);
            return listPlayer.get(0);
        }
        return "";
    }

}
