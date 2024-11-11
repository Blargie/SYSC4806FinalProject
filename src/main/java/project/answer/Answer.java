package project.answer;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Answer {
    //Abstract Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer answerId;
    protected Integer userId;  //ToDo Milestone2: The id of the user who answered the question
    protected Integer surveyId;
    protected Integer questionId;

    //Methods
    //Getters
    protected Integer getAnswerId() {
        return this.answerId;
    }
    protected Integer getUserId() {
        return this.userId;
    }
    protected Integer getSurveyId() {
        return this.surveyId;
    }
    protected Integer getQuestionId() {
        return this.questionId;
    }
    //Setters
    protected void setAnswerId(Integer answerId) {
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
