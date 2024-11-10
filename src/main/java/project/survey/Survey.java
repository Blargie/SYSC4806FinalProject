package project.survey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import project.question.Question;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Survey {
    @Id
    private Integer surveyId;
    private Integer userId; //ToDo Milestone2: Implement, not applicable for Milestone 1
    private String surveyName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> surveyQuestions = new ArrayList<Question>(); //List of questions in the survey
    private boolean isOpen; //Indicates whether the survey is open or closed (true if open)

    //Constructor
    public Survey() {}

    // Make getters public
    public Integer getSurveyId() {
        return this.surveyId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public String getSurveyName() {
        return this.surveyName;
    }

    public List<Question> getSurveyQuestions() {
        return this.surveyQuestions;
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    // Make setters public
    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public void setSurveyOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}