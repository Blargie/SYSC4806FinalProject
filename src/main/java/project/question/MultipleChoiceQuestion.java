package project.question;

public class MultipleChoiceQuestion extends Question {
    //Fields
    private int mcqId;
    private int questionId;
    private int numAnswers;
    //Constructor
    public MultipleChoiceQuestion() { }
    //Getters
    public int getMcqId() {
        return mcqId;
    }
    public int getQuestionId() {
        return questionId;
    }
    public int getNumAnswers() {
        return numAnswers;
    }
    //Setters
    public void setMcqId(int mcqId) {
        this.mcqId = mcqId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public void setNumAnswers(int numAnswers) {
        this.numAnswers = numAnswers;
    }
    //Methods
    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" +
                "mcqId=" + mcqId +
                ", questionId=" + questionId +
                ", numAnswers=" + numAnswers +
                '}';
    }
}
