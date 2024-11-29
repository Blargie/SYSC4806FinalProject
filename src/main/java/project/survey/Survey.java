package project.survey;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import project.question.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
public class Survey {
    @Id
    @JsonIgnore
    private Integer surveyId;

//    private Integer userId;

    @Column(nullable = false)
    private String surveyName;

    @Column(columnDefinition = "TEXT")
    private String surveyDescription;

    @Column(nullable = false)
    private boolean isOpen;

    @Column
    private boolean isAnonymous;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime expirationDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("survey")
    @JsonProperty("surveyQuestions")
    private List<Question> surveyQuestions = new ArrayList<>();

    // Constructors
    public Survey() {
        this.createdAt = LocalDateTime.now();
        this.isOpen = true;
        this.isAnonymous = false;
    }

    public Survey(String surveyName) {
        this();
        this.surveyName = surveyName;
    }

    // Getters
    public Integer getSurveyId() {
        return surveyId;
    }

//    public Integer getUserId() {
//        return userId;
//    }

    public String getSurveyName() {
        return surveyName;
    }

    public String getSurveyDescription() {
        return surveyDescription;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public boolean getIsAnonymous() {
        return isAnonymous;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonIgnore
    public List<Question> getSurveyQuestions() {
        return surveyQuestions;
    }

    // Setters
    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public void setSurveyDescription(String surveyDescription) {
        this.surveyDescription = surveyDescription;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setIsAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setSurveyQuestions(List<Question> surveyQuestions) {
        this.surveyQuestions = surveyQuestions;
        for (Question question : surveyQuestions) {
            question.setSurvey(this);
        }
    }

    // Question Management Methods
    public void addQuestion(Question question) {
        if (this.surveyQuestions == null) {
            this.surveyQuestions = new ArrayList<>();
        }
        question.setSurvey(this);
        this.surveyQuestions.add(question);
    }

    public void removeQuestion(Question question) {
        question.setSurvey(null);
        this.surveyQuestions.remove(question);
    }

    public void removeQuestion(int index) {
        if (index >= 0 && index < this.surveyQuestions.size()) {
            Question question = this.surveyQuestions.get(index);
            question.setSurvey(null);
            this.surveyQuestions.remove(index);
        }
    }

    public void removeAllQuestions() {
        for (Question question : this.surveyQuestions) {
            question.setSurvey(null);
        }
        this.surveyQuestions.clear();
    }

    @JsonIgnore
    public int getQuestionCount() {
        return this.surveyQuestions.size();
    }

    // Status Check Methods
    @JsonIgnore
    public boolean isExpired() {
        return expirationDate != null && expirationDate.isBefore(LocalDateTime.now());
    }

    @JsonIgnore
    public boolean isOpen() {
        return isOpen && !isExpired();
    }

    // Override Methods
    @Override
    public String toString() {
        return "Survey{" +
                "surveyId=" + surveyId +
                ", surveyName='" + surveyName + '\'' +
                ", surveyDescription='" + surveyDescription + '\'' +
                ", isOpen=" + isOpen +
                ", isAnonymous=" + isAnonymous +
                ", expirationDate=" + expirationDate +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Survey))
            return false;
        Survey survey = (Survey) o;
        return surveyId != null && surveyId.equals(survey.getSurveyId());
    }

    @Override
    public int hashCode() {
        return surveyId != null ? surveyId.hashCode() : 0;
    }
}