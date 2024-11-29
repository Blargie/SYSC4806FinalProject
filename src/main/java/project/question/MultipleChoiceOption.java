package project.question;

public class MultipleChoiceOption extends Question {
    //Fields
    private int optionId;
    private int mcqId;
    private String optionText;
    //Constructor
    public MultipleChoiceOption() { }
    //Getters
    public int getOptionId() {
        return optionId;
    }
    public int getMcqId() {
        return mcqId;
    }
    public String getOptionText() {
        return optionText;
    }
    //Setters
    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }
    public void setMcqId(int mcqId) {
        this.mcqId = mcqId;
    }
    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
    //Methods
    @Override
    public String toString() {
        return "MultipleChoiceOption{" +
                "optionId=" + optionId +
                ", mcqId=" + mcqId +
                ", optionText='" + optionText + '\'' +
                '}';
    }
}
