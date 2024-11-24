package project.survey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import project.question.Question;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer surveyId;

    private Integer userId;
    private String surveyName;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Question> surveyQuestions = new ArrayList<>();

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
        question.setSurvey(this);
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

    public void removeAllQuestions() {
        for (Question question : this.surveyQuestions) {
            question.setSurvey(null); // Unlink the question from the survey
        }
        this.surveyQuestions.clear(); // Clear the list of questions
    }


    public int getQuestionCount() {
        return this.surveyQuestions.size();
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
