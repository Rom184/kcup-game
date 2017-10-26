package identity;

import java.util.List;

public class BerserkRule {

    public String content;
    private String goodQuestion;
    private String badQuestion;

    private List<String> goodAnswers;
    private List<String> badAnswers;


    private String goodAnswer;
    private String badAnswer;
    private int randomGoodAnswer;
    private int randomBadAnswer;

    public BerserkRule(){}

    public BerserkRule(String content, String goodQuestion, String badQuestion, List<String> goodAnswers, List<String> badAnswers) {
        this.content = content;
        this.goodQuestion = goodQuestion;
        this.badQuestion = badQuestion;
        this.goodAnswers = goodAnswers;
        this.badAnswers = badAnswers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGoodQuestion() {
        return goodQuestion;
    }

    public void setGoodQuestion(String goodQuestion) {
        this.goodQuestion = goodQuestion;
    }

    public String getBadQuestion() {
        return badQuestion;
    }

    public void setBadQuestion(String badQuestion) {
        this.badQuestion = badQuestion;
    }

    public List<String> getGoodAnswers() {
        return goodAnswers;
    }

    public void setGoodAnswers(List<String> goodAnswers) {
        this.goodAnswers = goodAnswers;
    }

    public List<String> getBadAnswers() {
        return badAnswers;
    }

    public void setBadAnswers(List<String> badAnswers) {
        this.badAnswers = badAnswers;
    }

    public String getGoodAnswer() {
        return goodAnswer;
    }

    public void setGoodAnswer(String goodAnswer) {
        this.goodAnswer = goodAnswer;
    }

    public String getBadAnswer() {
        return badAnswer;
    }

    public void setBadAnswer(String badAnswer) {
        this.badAnswer = badAnswer;
    }

    public int getRandomGoodAnswer() {
        return randomGoodAnswer;
    }

    public void setRandomGoodAnswer(int randomGoodAnswer) {
        this.randomGoodAnswer = randomGoodAnswer;
    }

    public int getRandomBadAnswer() {
        return randomBadAnswer;
    }

    public void setRandomBadAnswer(int randomBadAnswer) {
        this.randomBadAnswer = randomBadAnswer;
    }
}
