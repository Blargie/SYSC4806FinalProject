package project.question;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)  // Change to JOINED strategy
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

    public Integer getQuestionId() {
        return this.questionId;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    // getType needed to help retrieve questions type
    public String getType() {
        if (this instanceof TextQuestion) {
            return "TEXT";
        } else if (this instanceof MultipleChoiceQuestion) {
            return "MULTIPLE_CHOICE";
        } else if (this instanceof NumericRangeQuestion) {
            return "NUMERIC_RANGE";
        }
        return "UNKNOWN";  // IF none match return UNKNOWN type but should not happen
    }
}