package project.question;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("NUMERIC_RANGE")
public class NumericRangeQuestion extends Question {

    @Column(nullable = false)
    private int lowerBound = 1;

    @Column(nullable = false)
    private int upperBound = 10;

    public NumericRangeQuestion() {}

    public int getLowerBound() {
        return this.lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
}