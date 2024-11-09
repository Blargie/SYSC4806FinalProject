package project.question;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NumericRangeQuestion extends Question {
    //Fields
    private int upperBound, lowerBound; //The upper and lower bounds of the range, i.e. (1, 10) = 1-10 for range
    //Constructors
    public NumericRangeQuestion() {};
    //Methods
    //Getters
    private int getUpperBound() {
        return this.upperBound;
    }
    private int getLowerBound() {
        return this.lowerBound;
    }
    //Setters
    private void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
    private void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }
}
