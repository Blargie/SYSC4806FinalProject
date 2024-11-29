//package project.unit.question;
//
//import org.junit.jupiter.api.Test;
//import project.question.MultipleChoiceQuestion;
//import project.question.TextQuestion;
//import project.survey.Survey;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TextQuestionTest {
//
//    @Test
//    void getQuestionId() {
//        TextQuestion tq = new TextQuestion();
//        tq.setQuestionId(1);
//        assertEquals(1, tq.getQuestionId());
//    }
//
//    @Test
//    void getQuestionText() {
//        TextQuestion tq = new TextQuestion();
//        tq.setQuestionText("test");
//        assertEquals("test", tq.getQuestionText());
//    }
//
//    @Test
//    void getSurvey() {
//        TextQuestion tq = new TextQuestion();
//        Survey survey = new Survey();
//        tq.setSurvey(survey);
//        assertEquals(survey, tq.getSurvey());
//    }
//
//    @Test
//    void setQuestionId() {
//        TextQuestion tq = new TextQuestion();
//        tq.setQuestionId(1);
//        assertNotNull(tq.getQuestionId());
//        assertEquals(1, tq.getQuestionId());
//    }
//
//    @Test
//    void setQuestionText() {
//        TextQuestion tq = new TextQuestion();
//        tq.setQuestionText("test");
//        assertNotNull(tq.getQuestionText());
//        assertEquals("test", tq.getQuestionText());
//    }
//
//    @Test
//    void setSurvey() {
//        TextQuestion tq = new TextQuestion();
//        Survey survey = new Survey();
//        tq.setSurvey(survey);
//        assertNotNull(tq.getSurvey());
//        assertEquals(survey, tq.getSurvey());
//    }
//
//    @Test
//    void getType() {
//        TextQuestion tq = new TextQuestion();
//        assertEquals("TEXT", tq.getType());
//    }
//
//    @Test
//    void getCharLimit() {
//        TextQuestion tq = new TextQuestion();
//        tq.setCharLimit(1);
//        assertEquals(1, tq.getCharLimit());
//    }
//
//    @Test
//    void setCharLimit() {
//        TextQuestion tq = new TextQuestion();
//        tq.setCharLimit(1);
//        assertNotNull(tq.getCharLimit());
//        assertEquals(1, tq.getCharLimit());
//    }
//}