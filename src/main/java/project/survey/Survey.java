package project.survey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import project.question.Question;

import java.util.ArrayList;
import java.util.List;

public class Survey {
    //Variables
    private Integer surveyId;
    private Integer userId; //ToDo Milestone2: Implement, not applicable for Milestone 1
    private String surveyName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> surveyQuestions = new ArrayList<Question>(); //List of questions in the survey
    private boolean isOpen; //Indicates whether the survey is open or closed (true if open)

    //Constructor
    public Survey() {}

    //Getters
    private Integer getSurveyId() {
        return this.surveyId;
    }
    private Integer getUserId() {
        return this.userId;
    }
    private String getSurveyName() {
        return this.surveyName;
    }
    private List<Question> getSurveyQuestions() {
        return this.surveyQuestions;
    }
    private boolean getIsOpen() {
        return this.isOpen;
    }

    //Setters
    private void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }
    private void setUserId(Integer userId) {
        this.userId = userId;
    }
    private void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }
    private void setSurveyOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    //QuestionList Manipulators
    private void addQuestion(Question question) {
        this.surveyQuestions.add(question);
    }
    private void removeQuestion(int index) {
        this.surveyQuestions.remove(index);
    }


}
