package project.answer;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Answer {
    //Abstract Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer answerId;
    public Integer userId;  //ToDo Milestone2: The id of the user who answered the question
    public Integer surveyId;
    public Integer questionId;

    //Methods
    //Getters
    public Integer getAnswerId() {
        return this.answerId;
    }
    public Integer getUserId() {
        return this.userId;
    }
    public Integer getSurveyId() {
        return this.surveyId;
    }
    public Integer getQuestionId() {
        return this.questionId;
    }
    //Setters
    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

}
