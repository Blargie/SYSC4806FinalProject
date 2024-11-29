//package project.unit.question;
//
//import org.junit.jupiter.api.Test;
//import project.question.MultipleChoiceQuestion;
//import project.question.NumericRangeQuestion;
//import project.survey.Survey;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class NumericRangeQuestionTest {
//
//    @Test
//    void getQuestionId() {
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        nrq.setQuestionId(1);
//        assertEquals(1, nrq.getQuestionId());
//    }
//
//    @Test
//    void getQuestionText() {
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        nrq.setQuestionText("test");
//        assertEquals("test", nrq.getQuestionText());
//    }
//
//    @Test
//    void getSurvey() {
//        Survey survey = new Survey();
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        nrq.setSurvey(survey);
//        assertEquals(survey, nrq.getSurvey());
//    }
//
//    @Test
//    void setQuestionId() {
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        nrq.setQuestionId(1);
//        assertNotNull(nrq.getQuestionId());
//        assertEquals(1, nrq.getQuestionId());
//    }
//
//    @Test
//    void setQuestionText() {
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        nrq.setQuestionText("test");
//        assertNotNull(nrq.getQuestionText());
//        assertEquals("test", nrq.getQuestionText());
//    }
//
//    @Test
//    void setSurvey() {
//        Survey survey = new Survey();
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        nrq.setSurvey(survey);
//        assertNotNull(nrq.getSurvey());
//        assertEquals(survey, nrq.getSurvey());
//    }
//
//    @Test
//    void getType() {
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        assertEquals("NUMERIC_RANGE", nrq.getType());
//
//    }
//
//    @Test
//    void getLowerBound() {
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        nrq.setLowerBound(1);
//        assertEquals(1, nrq.getLowerBound());
//    }
//
//    @Test
//    void setLowerBound() {
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        nrq.setLowerBound(1);
//        assertNotNull(nrq.getLowerBound());
//        assertEquals(1, nrq.getLowerBound());
//    }
//
//    @Test
//    void getUpperBound() {
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        nrq.setUpperBound(10);
//        assertEquals(10, nrq.getUpperBound());
//    }
//
//    @Test
//    void setUpperBound() {
//        NumericRangeQuestion nrq = new NumericRangeQuestion();
//        nrq.setUpperBound(10);
//        assertNotNull(nrq.getUpperBound());
//        assertEquals(10, nrq.getUpperBound());
//    }
//}