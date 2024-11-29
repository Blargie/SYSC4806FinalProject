package project.survey;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Survey {
    //Fields
    private int surveyId;
    private String surveyName;
    private boolean isOpen;
    private boolean isAnonymous;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime expirationDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    //Constructor
    public Survey() {
        //Initialize Default Values
        this.createdAt = LocalDateTime.now();
        this.isOpen = true;
        this.isAnonymous = false;
    }

    //Getters
    public int getSurveyId() {
        return this.surveyId;
    }
    public String getSurveyName() {
        return this.surveyName;
    }
    public boolean getIsOpen() {
        return this.isOpen;
    }
    public boolean getIsAnonymous() {
        return this.isAnonymous;
    }
    public LocalDateTime getExpirationDate() {
        return this.expirationDate;
    }
    public LocalDateTime getCreationAt() {
        return this.createdAt;
    }
    //Setters
    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }
    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
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
    //Methods
    @Override
    public String toString() {
        return "Survey{" +
                "surveyId=" + surveyId +
                ", surveyName='" + surveyName + '\'' +
                ", isOpen=" + isOpen +
                ", isAnonymous=" + isAnonymous +
                ", expirationDate=" + expirationDate +
                ", createdAt=" + createdAt +
                '}';
    }
}
