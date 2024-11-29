//package project.unit.survey;
//
//import org.junit.jupiter.api.Test;
//import project.question.MultipleChoiceQuestion;
//import project.question.Question;
//import project.question.TextQuestion;
//import project.survey.Survey;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SurveyTest {
//
//    @Test
//    void getSurveyId() {
//        Survey survey = new Survey();
//        survey.setSurveyId(1);
//        assertEquals(1, survey.getSurveyId());
//    }
//
////    @Test
////    void getUserId() {
////        Survey survey = new Survey();
////        survey.setUserId(1);
////        assertEquals(1, survey.getUserId());
////    }
//
//    @Test
//    void getSurveyName() {
//        Survey survey = new Survey();
//        survey.setSurveyName("test");
//        assertEquals("test", survey.getSurveyName());
//    }
//
//    @Test
//    void getSurveyQuestions() {
//        Survey survey = new Survey();
//        TextQuestion question = new TextQuestion();
//        List questions = new ArrayList<Question>();
//        survey.addQuestion(question);
//        questions.add(question);
//        assertNotNull(survey.getSurveyQuestions());
//        assertEquals(questions, survey.getSurveyQuestions());
//    }
//
//    @Test
//    void getIsOpen() {
//        Survey survey = new Survey();
//        survey.setIsOpen(true);
//        assertTrue(survey.getIsOpen());
//    }
//
//    @Test
//    void setSurveyId() {
//        Survey survey = new Survey();
//        survey.setSurveyId(1);
//        assertNotNull(survey.getSurveyId());
//    }
//
////    @Test
////    void setUserId() {
////        Survey survey = new Survey();
////        survey.setUserId(1);
////        assertNotNull(survey.getUserId());
////        assertEquals(1, survey.getUserId());
////    }
//
//    @Test
//    void setSurveyName() {
//        Survey survey = new Survey();
//        survey.setSurveyName("test");
//        assertNotNull(survey.getSurveyName());
//        assertEquals("test", survey.getSurveyName());
//    }
//
//    @Test
//    void setIsOpen() {
//        Survey survey = new Survey();
//        survey.setIsOpen(true);
//        assertTrue(survey.getIsOpen());
//    }
//
//    @Test
//    void setSurveyQuestions() {
//        Survey survey = new Survey();
//        TextQuestion question = new TextQuestion();
//        List questions = new ArrayList<Question>();
//        survey.setSurveyQuestions(questions);
//        assertNotNull(survey.getSurveyQuestions());
//        assertEquals(questions, survey.getSurveyQuestions());
//    }
//
//    @Test
//    void addQuestion() {
//        Survey survey = new Survey();
//        TextQuestion question = new TextQuestion();
//        MultipleChoiceQuestion question2 = new MultipleChoiceQuestion();
//        survey.addQuestion(question);
//        assertNotNull(survey.getSurveyQuestions());
//        assertEquals(1, survey.getSurveyQuestions().size());
//        survey.addQuestion(question2);
//        assertEquals(2, survey.getSurveyQuestions().size());
//    }
//
//    @Test
//    void removeQuestion() {
//        Survey survey = new Survey();
//        TextQuestion question = new TextQuestion();
//        MultipleChoiceQuestion question2 = new MultipleChoiceQuestion();
//        survey.addQuestion(question);
//        survey.addQuestion(question2);
//        assertEquals(2, survey.getSurveyQuestions().size());
//        survey.removeQuestion(question);
//        assertEquals(1, survey.getSurveyQuestions().size());
//        assertFalse(survey.getSurveyQuestions().contains(question));
//    }
//
//    @Test
//    void testRemoveQuestion() {
//        Survey survey = new Survey();
//        TextQuestion question = new TextQuestion();
//        MultipleChoiceQuestion question2 = new MultipleChoiceQuestion();
//        survey.addQuestion(question);
//        survey.addQuestion(question2);
//        assertEquals(2, survey.getSurveyQuestions().size());
//        survey.removeQuestion(0);
//        assertEquals(1, survey.getSurveyQuestions().size());
//        assertFalse(survey.getSurveyQuestions().contains(question));
//    }
//
//    @Test
//    void removeAllQuestions() {
//        Survey survey = new Survey();
//        TextQuestion question = new TextQuestion();
//        MultipleChoiceQuestion question2 = new MultipleChoiceQuestion();
//        survey.addQuestion(question);
//        survey.addQuestion(question2);
//        assertEquals(2, survey.getSurveyQuestions().size());
//        survey.removeAllQuestions();
//        assertEquals(0, survey.getSurveyQuestions().size());
//        assertFalse(survey.getSurveyQuestions().contains(question));
//        assertFalse(survey.getSurveyQuestions().contains(question2));
//    }
//
//    @Test
//    void getQuestionCount() {
//        Survey survey = new Survey();
//        TextQuestion question = new TextQuestion();
//        MultipleChoiceQuestion question2 = new MultipleChoiceQuestion();
//        survey.addQuestion(question);
//        assertEquals(1, survey.getQuestionCount());
//        survey.addQuestion(question2);
//        assertEquals(2, survey.getQuestionCount());
//    }
//
//    @Test
//    void testToString() {
//        Survey survey = new Survey();
//        survey.setSurveyId(1);
//        survey.setSurveyName("test");
//        survey.setSurveyDescription("test");
//        survey.setIsOpen(true);
//        survey.setIsAnonymous(false);
//        survey.setCreatedAt(LocalDateTime.now());
//        survey.setExpirationDate(null);
//        survey.setIsOpen(true);
//        assertEquals("Survey{surveyId=1, surveyName='test', description='test', isOpen=true, isAnonymous=false, questionCount=0, createdAt=" + survey.getCreatedAt() + ", expirationDate=null}", survey.toString());
//    }
//
//    @Test
//    void testEquals() {
//        Survey survey = new Survey();
//        Survey survey2 = new Survey();
//        survey.setSurveyId(1);
//    }
//
//    @Test
//    void testHashCode() {
//        Survey survey = new Survey();
//        survey.setSurveyId(1);
//        assertEquals(1, survey.hashCode());
//    }
//    //ToDo Milestone3: Add more tests
//}