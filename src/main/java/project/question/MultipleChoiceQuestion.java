package project.question;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
public class MultipleChoiceQuestion extends Question {

    @Column(nullable = false)
    private int numAnswers;

    @ElementCollection
    @CollectionTable(
            name = "multiple_choice_options",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "option_text")
    private List<String> options = new ArrayList<>();

    public MultipleChoiceQuestion() {
        this.numAnswers = 4; // Default value
    }

    public int getNumAnswers() {
        return this.numAnswers;
    }

    public void setNumAnswers(int numAnswers) {
        this.numAnswers = numAnswers;
    }

    public List<String> getOptions() {
        return this.options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
        this.numAnswers = options.size();
    }
}