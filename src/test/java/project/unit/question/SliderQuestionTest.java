package project.unit.question;

import org.junit.jupiter.api.Test;
import project.question.SliderQuestion;

import static org.junit.jupiter.api.Assertions.*;

class SliderQuestionTest {

    @Test
    void getLowerBound() {
        SliderQuestion sq = new SliderQuestion();
        sq.setLowerBound(0);
        assertEquals(0, sq.getLowerBound());
    }

    @Test
    void setLowerBound() {
        SliderQuestion sq = new SliderQuestion();
        sq.setLowerBound(0);
        assertNotNull(sq.getLowerBound());
        assertEquals(0, sq.getLowerBound());
    }

    @Test
    void getUpperBound() {
        SliderQuestion sq = new SliderQuestion();
        sq.setUpperBound(10);
        assertEquals(10, sq.getUpperBound());
    }

    @Test
    void setUpperBound() {
        SliderQuestion sq = new SliderQuestion();
        sq.setUpperBound(10);
        assertNotNull(sq.getUpperBound());
        assertEquals(10, sq.getUpperBound());
    }

    @Test
    void getStepSize() {
        SliderQuestion sq = new SliderQuestion();
        sq.setStepSize(1);
        assertEquals(1, sq.getStepSize());
    }

    @Test
    void setStepSize() {
        SliderQuestion sq = new SliderQuestion();
        sq.setStepSize(1);
        assertNotNull(sq.getStepSize());
        assertEquals(1, sq.getStepSize());
    }

    @Test
    void getDefaultValue() {
        SliderQuestion sq = new SliderQuestion();
        sq.setDefaultValue(5);
        assertEquals(5, sq.getDefaultValue());
    }

    @Test
    void setDefaultValue() {
        SliderQuestion sq = new SliderQuestion();
        sq.setDefaultValue(5);
        assertNotNull(sq.getDefaultValue());
        assertEquals(5, sq.getDefaultValue());
    }
}