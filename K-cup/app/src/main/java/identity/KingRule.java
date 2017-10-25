package identity;


public class KingRule {

    private String content;
    private String type;
    private String number;
    private boolean isKing;
    private String nameKing;

    public KingRule() {
    }

    public KingRule(String content, String type, String number) {
        this.content = content;
        this.type = type;
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }

    public String getNameKing() {
        return nameKing;
    }

    public void setNameKing(String nameKing) {
        this.nameKing = nameKing;
    }
}
