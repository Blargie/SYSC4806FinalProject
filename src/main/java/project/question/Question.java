package project.question;

public class Question {
    //Fields
    private int questionId;
    private String questionText;
    private int surveyId;
    private boolean required;

    //Constructor
    public Question() { }
    //Getters
    public int getQuestionId() {
        return questionId;
    }
    public String getQuestionText() {
        return questionText;
    }
    public int getSurveyId() {
        return surveyId;
    }
    public boolean getRequired() {
        return required;
    }
    //Setters
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }
    public void setRequired(boolean required) {
        this.required = required;
    }
    //Methods
    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionText='" + questionText + '\'' +
                ", surveyId=" + surveyId +
                ", required=" + required +
                '}';
    }
}
