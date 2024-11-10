package project.survey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import project.question.Question;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer surveyId;

    private Integer userId;
    private String surveyName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> surveyQuestions = new ArrayList<Question>();

    private boolean isOpen;

    // Constructors
    public Survey() {}

    public Survey(String surveyName) {
        this.surveyName = surveyName;
        this.isOpen = true;
        this.surveyQuestions = new ArrayList<>();
    }

    // Getters
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

    // Setters
    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setSurveyQuestions(List<Question> surveyQuestions) {
        this.surveyQuestions = surveyQuestions;
    }

    // Helper methods
    public void addQuestion(Question question) {
        if (this.surveyQuestions == null) {
            this.surveyQuestions = new ArrayList<>();
        }
        this.surveyQuestions.add(question);
    }

    public void removeQuestion(Question question) {
        this.surveyQuestions.remove(question);
    }

    public void removeQuestion(int index) {
        if (index >= 0 && index < this.surveyQuestions.size()) {
            this.surveyQuestions.remove(index);
        }
    }

    public int getQuestionCount() {
        return this.surveyQuestions.size();
    }

    public void clearQuestions() {
        this.surveyQuestions.clear();
    }

    // Override methods
    @Override
    public String toString() {
        return "Survey{" +
                "surveyId=" + surveyId +
                ", surveyName='" + surveyName + '\'' +
                ", questionCount=" + getQuestionCount() +
                ", isOpen=" + isOpen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Survey)) return false;
        Survey survey = (Survey) o;
        return surveyId != null && surveyId.equals(survey.getSurveyId());
    }

    @Override
    public int hashCode() {
        return surveyId != null ? surveyId.hashCode() : 0;
    }
}