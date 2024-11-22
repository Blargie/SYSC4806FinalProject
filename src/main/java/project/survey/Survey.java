package project.survey;

import jakarta.persistence.*;
import project.question.Question;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer surveyId;

    private Integer userId;

    @Column(nullable = false)
    private String surveyName;

    @Column(columnDefinition = "TEXT")
    private String surveyDescription;
    
    @Column(nullable = false)
    private boolean isOpen;
    
    @Column
    private boolean isAnonymous;
    
    @Column
    private Date expirationDate;
    
    @Column(nullable = false)
    private Date createdAt;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Question> surveyQuestions = new ArrayList<>();

    // Constructors
    public Survey() {
        this.createdAt = new Date();
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

    public Integer getUserId() {
        return userId;
    }

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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<Question> getSurveyQuestions() {
        return surveyQuestions;
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

    public void setSurveyDescription(String surveyDescription) {
        this.surveyDescription = surveyDescription;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setIsAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setSurveyQuestions(List<Question> surveyQuestions) {
        this.surveyQuestions = surveyQuestions;
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

    public int getQuestionCount() {
        return this.surveyQuestions.size();
    }

    // Status Check Methods
    public boolean isExpired() {
        return expirationDate != null && expirationDate.before(new Date());
    }

    public boolean isActive() {
        return isOpen && !isExpired();
    }

    // Override Methods
    @Override
    public String toString() {
        return "Survey{" +
                "surveyId=" + surveyId +
                ", surveyName='" + surveyName + '\'' +
                ", description='" + surveyDescription + '\'' +
                ", isOpen=" + isOpen +
                ", isAnonymous=" + isAnonymous +
                ", questionCount=" + getQuestionCount() +
                ", createdAt=" + createdAt +
                ", expirationDate=" + expirationDate +
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