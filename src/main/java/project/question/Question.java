package project.question;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public abstract class Question {
    //Abstract Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionId;
    private String questionText; //The text of the question

    //Methods
    //Getters
    protected Integer getQuestionId() {
        return this.questionId;
    }
    protected String getQuestionText() {
        return this.questionText;
    }

    //Setters
    protected void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    protected void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
