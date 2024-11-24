package project.unit.question;

import org.junit.jupiter.api.Test;
import project.question.MultipleChoiceQuestion;
import project.survey.Survey;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceQuestionTest {

    @Test
    void getQuestionId() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setQuestionId(1);
        assertEquals(1, mcq.getQuestionId());
    }

    @Test
    void getQuestionText() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setQuestionText("test");
        assertEquals("test", mcq.getQuestionText());
    }

    @Test
    void getSurvey() {
        Survey survey = new Survey();
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setSurvey(survey);
        assertEquals(survey, mcq.getSurvey());
    }

    @Test
    void setQuestionId() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setQuestionId(1);
        assertNotNull(mcq.getQuestionId());
        assertEquals(1, mcq.getQuestionId());
    }

    @Test
    void setQuestionText() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setQuestionText("test");
        assertNotNull(mcq.getQuestionText());
        assertEquals("test", mcq.getQuestionText());
    }

    @Test
    void setSurvey() {
        Survey survey = new Survey();
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setSurvey(survey);
        assertNotNull(mcq.getSurvey());
        assertEquals(survey, mcq.getSurvey());
    }

    @Test
    void getType() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        assertEquals("MULTIPLE_CHOICE", mcq.getType());
    }

    @Test
    void getNumAnswers() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setNumAnswers(1);
        assertEquals(1, mcq.getNumAnswers());
    }

    @Test
    void setNumAnswers() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setNumAnswers(1);
        assertNotNull(mcq.getNumAnswers());
        assertEquals(1, mcq.getNumAnswers());
    }

    @Test
    void getCorrectAnswer() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setCorrectAnswer(1);
        assertEquals(1, mcq.getCorrectAnswer());
    }

    @Test
    void setCorrectAnswer() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setCorrectAnswer(1);
        assertNotNull(mcq.getCorrectAnswer());
        assertEquals(1, mcq.getCorrectAnswer());
    }

    @Test
    void getOptions() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        List options = new ArrayList();
        options.add("test");
        mcq.setOptions(options);
        List expected = new ArrayList();
        expected.add("test");
        assertArrayEquals(expected.toArray(), mcq.getOptions().toArray());
    }

    @Test
    void setOptions() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        List options = new ArrayList();
        options.add("test");
        mcq.setOptions(options);
        List expected = new ArrayList();
        expected.add("test");
        assertNotNull(mcq.getOptions());
        assertArrayEquals(expected.toArray(), mcq.getOptions().toArray());
    }
}