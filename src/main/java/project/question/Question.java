package project.question;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import project.survey.Survey;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextQuestion.class, name = "TEXT"),
        @JsonSubTypes.Type(value = MultipleChoiceQuestion.class, name = "MULTIPLE_CHOICE"),
        @JsonSubTypes.Type(value = NumericRangeQuestion.class, name = "NUMERIC_RANGE")
})
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionId;

    @Column(nullable = false)
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    @JsonBackReference
    private Survey survey;

    // Getters and Setters
    public Integer getQuestionId() {
        return this.questionId;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public Survey getSurvey() {
        return this.survey;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    // getType method to help retrieve the question's type
    public String getType() {
        if (this instanceof TextQuestion) {
            return "TEXT";
        } else if (this instanceof MultipleChoiceQuestion) {
            return "MULTIPLE_CHOICE";
        } else if (this instanceof NumericRangeQuestion) {
            return "NUMERIC_RANGE";
        }
        return "UNKNOWN";
    }
}
