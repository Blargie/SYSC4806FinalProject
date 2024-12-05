package project.unit.answer;

import org.junit.jupiter.api.Test;
import project.answer.SliderAnswer;

import static org.junit.jupiter.api.Assertions.*;

class SliderAnswerTest {

    @Test
    void getSliderValue() {
        SliderAnswer sa = new SliderAnswer();
        sa.setSliderValue(5);
        assertEquals(5, sa.getSliderValue());
    }

    @Test
    void setSliderValue() {
        SliderAnswer sa = new SliderAnswer();
        sa.setSliderValue(5);
        assertNotNull(sa.getSliderValue());
        assertEquals(5, sa.getSliderValue());
    }

    @Test
    void getAnswerId() {
        SliderAnswer sa = new SliderAnswer();
        sa.setAnswerId(1);
        assertNotNull(sa.getAnswerId());
        assertEquals(1, sa.getAnswerId());
    }

    @Test
    void setAnswerId() {
        SliderAnswer sa = new SliderAnswer();
        sa.setAnswerId(1);
        assertNotNull(sa.getAnswerId());
        assertEquals(1, sa.getAnswerId());
    }

    @Test
    void getUserId() {
        SliderAnswer sa = new SliderAnswer();
        sa.setUserId(1);
        assertNotNull(sa.getUserId());
        assertEquals(1, sa.getUserId());
    }

    @Test
    void setUserId() {
        SliderAnswer sa = new SliderAnswer();
        sa.setUserId(1);
        assertNotNull(sa.getUserId());
        assertEquals(1, sa.getUserId());
    }

    @Test
    void getSurveyId() {
        SliderAnswer sa = new SliderAnswer();
        sa.setSurveyId(1);
        assertNotNull(sa.getSurveyId());
        assertEquals(1, sa.getSurveyId());
    }

    @Test
    void setSurveyId() {
        SliderAnswer sa = new SliderAnswer();
        sa.setSurveyId(1);
        assertNotNull(sa.getSurveyId());
        assertEquals(1, sa.getSurveyId());
    }

    @Test
    void getQuestionId() {
        SliderAnswer sa = new SliderAnswer();
        sa.setQuestionId(1);
        assertNotNull(sa.getQuestionId());
        assertEquals(1, sa.getQuestionId());
    }

    @Test
    void setQuestionId() {
        SliderAnswer sa = new SliderAnswer();
        sa.setQuestionId(1);
        assertNotNull(sa.getQuestionId());
        assertEquals(1, sa.getQuestionId());
    }
}