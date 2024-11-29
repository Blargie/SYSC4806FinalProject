package project.question;

public class NumericRangeQuestion extends Question{
    //Fields
    private int nrqId;
    private int questionId;
    private int upperBound;
    private int lowerBound;
    //Constructor
    public NumericRangeQuestion() { }
    //Getters
    public int getNrqId() {
        return nrqId;
    }
    public int getQuestionId() {
        return questionId;
    }
    public int getUpperBound() {
        return upperBound;
    }
    public int getLowerBound() {
        return lowerBound;
    }
    //Setters
    public void setNrqId(int nrqId) {
        this.nrqId = nrqId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }
    //Methods
    @Override
    public String toString() {
        return "NumericRangeQuestion{" +
                "nrqId=" + nrqId +
                ", questionId=" + questionId +
                ", upperBound=" + upperBound +
                ", lowerBound=" + lowerBound +
                '}';
    }
}
