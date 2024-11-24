package project.unit.answer;

import org.junit.jupiter.api.Test;
import project.answer.NumericRangeAnswer;

import static org.junit.jupiter.api.Assertions.*;

class NumericRangeAnswerTest {


    @Test
    void getAnswerId() {
        NumericRangeAnswer nra = new NumericRangeAnswer();
        nra.setAnswerId(1);
        assertNotNull(nra.getAnswerId());
        assertEquals(1, nra.getAnswerId());
    }

    @Test
    void getUserId() {
        NumericRangeAnswer nra = new NumericRangeAnswer();
        nra.setUserId(1);
        assertNotNull(nra.getUserId());
        assertEquals(1, nra.getUserId());
    }

    @Test
    void getSurveyId() {
        NumericRangeAnswer nra = new NumericRangeAnswer();
        nra.setSurveyId(1);
        assertNotNull(nra.getSurveyId());
        assertEquals(1, nra.getSurveyId());
    }

    @Test
    void getQuestionId() {
        NumericRangeAnswer nra = new NumericRangeAnswer();
        nra.setQuestionId(1);
        assertNotNull(nra.getQuestionId());
        assertEquals(1, nra.getQuestionId());
    }

    @Test
    void setAnswerId() {
        NumericRangeAnswer nra = new NumericRangeAnswer();
        nra.setAnswerId(1);
        assertNotNull(nra.getAnswerId());
        assertEquals(1, nra.getAnswerId());
    }

    @Test
    void setUserId() {
        NumericRangeAnswer nra = new NumericRangeAnswer();
        nra.setUserId(1);
        assertNotNull(nra.getUserId());
        assertEquals(1, nra.getUserId());
    }

    @Test
    void setSurveyId() {
        NumericRangeAnswer nra = new NumericRangeAnswer();
        nra.setSurveyId(1);
        assertNotNull(nra.getSurveyId());
        assertEquals(1, nra.getSurveyId());
    }

    @Test
    void setQuestionId() {
        NumericRangeAnswer nra = new NumericRangeAnswer();
        nra.setQuestionId(1);
        assertNotNull(nra.getQuestionId());
        assertEquals(1, nra.getQuestionId());
    }

    @Test
    void getChoice() {
        NumericRangeAnswer nra = new NumericRangeAnswer();
        nra.setChoice(1);
        assertNotNull(nra.getChoice());
        assertEquals(1, nra.getChoice());
    }

    @Test
    void setChoice() {
        NumericRangeAnswer nra = new NumericRangeAnswer();
        nra.setChoice(1);
        assertNotNull(nra.getChoice());
        assertEquals(1, nra.getChoice());
    }
}