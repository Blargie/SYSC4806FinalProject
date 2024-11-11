package project.answer;

import jakarta.persistence.Entity;

@Entity
public class NumericRangeAnswer extends Answer {
    //Fields
    private int choice;

    //Constructor
    public NumericRangeAnswer() {}
    //Methods
    //Getters
    public int getChoice() {
        return this.choice;
    }
    //Setters
    public void setChoice(int choice) {
        this.choice = choice;
    }
}
