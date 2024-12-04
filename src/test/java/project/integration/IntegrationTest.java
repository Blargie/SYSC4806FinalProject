package project.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.answer.*;
import project.question.*;
import project.survey.Survey;
import project.survey.SurveyController;
import project.survey.SurveyRepository;
import org.springframework.http.MediaType;


import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyRepository surveyRepository;

    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private AnswerRepository answerRepository;

    @Autowired
    public IntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;

    }

    Survey survey;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new SurveyController(surveyRepository, answerRepository, questionRepository)).build();

        answerRepository.deleteAll();
        questionRepository.deleteAll();
        surveyRepository.deleteAll();

        this.survey = new Survey();
        survey.setSurveyName("Test Survey");
        survey.setIsOpen(true);
        survey.setUserId(1);
        survey.setSurveyDescription("This is a test survey");
        survey.setIsAnonymous(true);
        survey.setExpirationDate(null);
        survey.setCreatedAt(new Date());
        surveyRepository.save(survey);

        survey.setSurveyId(1);
        surveyRepository.save(survey);
    }

    @Test
    public void testDeleteSurvey() throws Exception {
        //Mock the surveyRepository
        when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));
        //Perform the DELETE request
        mockMvc.perform(delete("/api/surveys/delete/1"))
                .andExpect(status().isOk());

        //Verify Interactions
        verify(surveyRepository, times(1)).deleteById(1);
    }

    @Test
    public void testEditSurvey() throws Exception {
        //Mock the surveyRepository
        when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));
        //Perform the GET request
        mockMvc.perform(get("/api/surveys/1/edit"))
                .andExpect(view().name("edit-survey"))
                .andExpect(model().attributeExists("survey"))
                .andExpect(model().attribute("survey", this.survey))
                .andExpect(status().isOk());
        //Verify Interactions
        verify(surveyRepository, times(1)).findById(1);
    }

    @Test
    void updateSurvey() throws Exception {
        //Update the survey
        Survey updatedSurvey = new Survey();
        updatedSurvey.setSurveyName("Updated Survey");
        updatedSurvey.setSurveyDescription("Updated description");
        updatedSurvey.setIsOpen(true);
        updatedSurvey.setIsAnonymous(false);
        updatedSurvey.setExpirationDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)); // 7 days from now

        //Mock the surveyRepository
        when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));

        //Perform the POST request to update the survey
        mockMvc.perform(MockMvcRequestBuilders.post("/api/surveys/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedSurvey)))
                .andExpect(status().isOk());

        // Verify the repository method call
        verify(surveyRepository, times(1)).findById(1);
        verify(surveyRepository, times(3)).save(any(Survey.class));
        //save() is called twice in setup(), once by endpoint

        //Get the updated survey
        Survey updated = surveyRepository.findById(1).get();

        //Assertions
        assertEquals(updatedSurvey.getSurveyName(), updated.getSurveyName());
        assertEquals(updatedSurvey.getSurveyDescription(), updated.getSurveyDescription());
        assertEquals(updatedSurvey.getIsOpen(), updated.getIsOpen());
        assertEquals(updatedSurvey.getIsAnonymous(), updated.getIsAnonymous());
        assertEquals(updatedSurvey.getExpirationDate(), updated.getExpirationDate());
    }

    @Test
    void showIndex() throws Exception {
        mockMvc.perform(get("/api/surveys/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }


    @Test
    void showCreateForm() throws Exception {
        mockMvc.perform(get("/api/surveys/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("survey"))
                .andExpect(view().name("create-survey"));
    }

    @Test
    void createSurvey() throws Exception {
        Survey newSurvey = new Survey();
        newSurvey.setSurveyName("Test Survey");
        newSurvey.setIsOpen(true);
        newSurvey.setUserId(1);
        newSurvey.setSurveyDescription("This is a test survey");
        newSurvey.setIsAnonymous(true);
        newSurvey.setExpirationDate(null);
        newSurvey.setCreatedAt(new Date());

        when(surveyRepository.findById(2)).thenReturn(Optional.of(newSurvey));

        mockMvc.perform(post("/api/surveys/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newSurvey)))
                .andExpect(status().isOk());

        verify(surveyRepository, times(3)).save(any(Survey.class));
        //same as update, setup calls save() twice, creating calls once

        //Get New Survey
        Survey foundSurvey = surveyRepository.findById(2).get();

        //Assertions
        assertEquals(foundSurvey.getSurveyName(), newSurvey.getSurveyName());
        assertEquals(foundSurvey.getSurveyDescription(), newSurvey.getSurveyDescription());
        assertEquals(foundSurvey.getIsOpen(), newSurvey.getIsOpen());
        assertEquals(foundSurvey.getIsAnonymous(), newSurvey.getIsAnonymous());
        assertEquals(foundSurvey.getExpirationDate(), newSurvey.getExpirationDate());
    }

    @Test
    void getSurvey() throws Exception {
        when(surveyRepository.findBySurveyId(1)).thenReturn(List.of(survey));

        mockMvc.perform(get("/api/surveys/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(surveyRepository, times(1)).findBySurveyId(1);
    }

    @Test
    void listSurveys() throws Exception {
        when(surveyRepository.findAll()).thenReturn(List.of(survey));

        mockMvc.perform(get("/api/surveys/list-open")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("surveys"))
                .andExpect(model().attribute("surveys", List.of(survey)))
                .andExpect(view().name("survey-list-user"))
                .andExpect(status().isOk());

        verify(surveyRepository, times(1)).findAll();
    }

    @Test
    void getSurveyToAnswer() throws Exception {
        when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));

        mockMvc.perform(get("/api/surveys/1/answer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("survey"))
                .andExpect(model().attribute("survey", survey))
                .andExpect(view().name("answer-survey"))
                .andExpect(status().isOk());

        verify(surveyRepository, times(1)).findById(1);
    }

    @Test
    void listOpenSurveys() throws Exception {
        when(surveyRepository.findByIsOpenTrue()).thenReturn(List.of(survey));

        mockMvc.perform(get("/api/surveys/list-open")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("surveys"))
                .andExpect(model().attribute("surveys", List.of(survey)))
                .andExpect(view().name("survey-list-user"))
                .andExpect(status().isOk());

        verify(surveyRepository, times(1)).findByIsOpenTrue();
    }

    @Test
    void viewSurveyPage() throws Exception {
        mockMvc.perform(get("/api/surveys/survey-list-admin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(view().name("survey-list-admin"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllSurveys() throws Exception {
        when(surveyRepository.findAll()).thenReturn(List.of(survey));

        mockMvc.perform(get("/api/surveys/list-json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(surveyRepository, times(1)).findAll();
    }

    @Test
    void generateReport() throws Exception {
        when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));

        mockMvc.perform(get("/api/surveys/1/generate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("survey"))
                .andExpect(model().attribute("survey", survey))
                .andExpect(view().name("survey-report"))
                .andExpect(status().isOk());

        verify(surveyRepository, times(1)).findById(1);
    }

    @Test
    void toggleSurveyStatus() throws Exception {
        when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));

        mockMvc.perform(post("/api/surveys/1/toggle-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(Collections.singletonMap("isOpen", true))))
                .andExpect(status().isOk());

        verify(surveyRepository, times(1)).findById(1);
    }

    @Test
    void submitSurveyAnswers() throws Exception {
        //ToDo: Milestone 3
    }
}
