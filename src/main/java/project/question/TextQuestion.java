package project.question;

public class TextQuestion {
    //Fields
    private int tqId;
    private int questionId;
    private int charLimit;
    //Constructor
    public TextQuestion() { }
    //Getters
    public int getTqId() {
        return tqId;
    }
    public int getQuestionId() {
        return questionId;
    }
    public int getCharLimit() {
        return charLimit;
    }
    //Setters
    public void setTqId(int tqId) {
        this.tqId = tqId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public void setCharLimit(int charLimit) {
        this.charLimit = charLimit;
    }
    //Methods
    @Override
    public String toString() {
        return "TextQuestion{" +
                "tqId=" + tqId +
                ", questionId=" + questionId +
                ", charLimit=" + charLimit +
                '}';
    }
}
