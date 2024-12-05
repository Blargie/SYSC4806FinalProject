package project.question;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SLIDER")
public class SliderQuestion extends Question {

    @Column(nullable = false)
    private int lowerBound = 0;

    @Column(nullable = false)
    private int upperBound = 10;

    @Column(nullable = false)
    private int stepSize = 1;

    @Column(nullable = true)
    private int defaultValue = 5;

    public SliderQuestion() {}

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

    public int getStepSize() {
        return this.stepSize;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public int getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
}