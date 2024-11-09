package project.answer;

import jakarta.persistence.Entity;

@Entity
public class MultipleChoiceAnswer extends Answer {
    //Fields
    private int choice;

    //Constructor
    public MultipleChoiceAnswer() {}
    //Methods
    //Getters
    private int getChoice() {
        return this.choice;
    }
    //Setters
    private void setChoice(int choice) {
        this.choice = choice;
    }
}
