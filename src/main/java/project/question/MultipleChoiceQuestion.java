package project.question;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MultipleChoiceQuestion extends Question {
    //Fields
    int numAnswers; //The number of possible answers to the question, i.e. (4 = A, B, C, and D)
    //Constructors
    public MultipleChoiceQuestion() {};
    //Methods
    //Getters
    private int getNumAnswers() {
        return this.numAnswers;
    }
    //Setters
    private void setNumAnswers(int numAnswers) {
        this.numAnswers = numAnswers;
    }
}
