package project.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import project.answer.AnswerRepository;
import project.question.QuestionRepository;
import project.survey.Survey;
import project.survey.SurveyRepository;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class Integration {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    public Integration(MockMvc mockMvc) {
        this.mockMvc = mockMvc;

    }

    @BeforeEach
    public void setup() {
        answerRepository.deleteAll();
        questionRepository.deleteAll();
        surveyRepository.deleteAll();

        Survey survey = new Survey();
        survey.setSurveyName("Test Survey");
        survey.setIsOpen(true);
        survey.setUserId(1);
        survey.setSurveyDescription("This is a test survey");
        survey.setIsAnonymous(true);
        survey.setExpirationDate(null);
        survey.setCreatedAt(null);
        surveyRepository.save(survey);

        survey.setSurveyId(1);
        surveyRepository.save(survey);
    }

    @Test
    public void testDeleteSurvey() throws Exception{
        //Perform the DELETE request
        mockMvc.perform(delete("/survey/1"))
                .andExpect(status().isOk());

        //Verify the survey and questions were deleted
        assertFalse(surveyRepository.existsById(1));
        assertTrue(answerRepository.findBySurveyId(1).isEmpty());
        //assertTrue(questionRepository.findBySurveyId(1).isEmpty());
    }
}
