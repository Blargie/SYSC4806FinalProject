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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import project.answer.*;
import project.question.*;
import project.survey.Survey;
import project.survey.SurveyController;
import project.survey.SurveyRepository;
import project.user.UserRepository;
import project.user.User;
import org.springframework.http.MediaType;


import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
    
    @MockBean
    private UserRepository userRepository;
    private User adminUser;
    private User regularUser;


    Survey survey;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(
            new SurveyController(
                surveyRepository, 
                answerRepository, 
                userRepository,    // Added userRepository
                questionRepository
            )
        ).build();

        // Clear repositories
        answerRepository.deleteAll();
        questionRepository.deleteAll();
        surveyRepository.deleteAll();

        // Setup test users
        adminUser = new User();
        adminUser.setId(1L);
        adminUser.setUsername("admin");
        adminUser.setRole(User.Role.ADMIN);

        regularUser = new User();
        regularUser.setId(2L);
        regularUser.setUsername("user");
        regularUser.setRole(User.Role.USER);

        // Setup test survey
        this.survey = new Survey();
        survey.setSurveyName("Test Survey");
        survey.setIsOpen(true);
        survey.setSurveyDescription("This is a test survey");
        survey.setIsAnonymous(true);
        survey.setExpirationDate(null);
        survey.setCreatedAt(new Date());
        survey.setCreatorId(1);    // Added creatorId
        surveyRepository.save(survey);
        survey.setSurveyId(1);
        surveyRepository.save(survey);
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
    void getSurvey() throws Exception {
        when(surveyRepository.findBySurveyId(1)).thenReturn(List.of(survey));

        mockMvc.perform(get("/api/surveys/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(surveyRepository, times(1)).findBySurveyId(1);
    }

    @Test
    void listSurveysAsUser() throws Exception {
        User user = new User();
        user.setRole(User.Role.USER);
        
        when(surveyRepository.findByIsOpenTrue()).thenReturn(List.of(survey));
    
        mockMvc.perform(get("/api/surveys/list-open")
                .sessionAttr("user", user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("surveys"))
                .andExpect(model().attribute("surveys", List.of(survey)))
                .andExpect(view().name("survey-list-user"))
                .andExpect(status().isOk());
    
        verify(surveyRepository, times(1)).findByIsOpenTrue();
    }
    @Test
    void getSurveyToAnswer() throws Exception {
        when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
    
        mockMvc.perform(get("/api/surveys/1/answer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("survey"))
                .andExpect(model().attributeExists("creator"))
                .andExpect(view().name("answer-survey"))
                .andExpect(status().isOk());
    
        verify(surveyRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findById(1L);
    }
    
    
    @Test
    void viewSurveyPageAsUser() throws Exception {
        User regularUser = new User();
        regularUser.setRole(User.Role.USER);
    
        mockMvc.perform(get("/api/surveys/survey-list-admin")
                .sessionAttr("user", regularUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(view().name("redirect:/api/surveys/list-open"));
    }
    
    @Test
    void getAllSurveysAsAdmin() throws Exception {
        User adminUser = new User();
        adminUser.setId(1L);  // Set the ID to prevent NullPointerException
        adminUser.setRole(User.Role.ADMIN);
        adminUser.setUsername("admin");
    
        when(surveyRepository.findByCreatorId(1)).thenReturn(List.of(survey));
    
        mockMvc.perform(get("/api/surveys/list-json")
                .sessionAttr("user", adminUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    
        verify(surveyRepository, times(1)).findByCreatorId(1);
    }
    
    @Test
void deleteSurveyAsAdmin() throws Exception {
    // Setup admin user
    User adminUser = new User();
    adminUser.setId(1L);
    adminUser.setRole(User.Role.ADMIN);
    
    // Setup survey with matching creatorId
    survey.setCreatorId(1);
    when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));
    
    mockMvc.perform(delete("/api/surveys/delete/1")
            .sessionAttr("user", adminUser)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
            
    verify(surveyRepository, times(1)).deleteById(1);
}

@Test
void deleteSurveyAsAdminNotOwner() throws Exception {
    // Setup admin user with different ID
    User adminUser = new User();
    adminUser.setId(2L);
    adminUser.setRole(User.Role.ADMIN);
    
    // Setup survey with different creatorId
    survey.setCreatorId(1);
    when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));
    
    mockMvc.perform(delete("/api/surveys/delete/1")
            .sessionAttr("user", adminUser)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());
}

    @Test
    void deleteSurveyAsUser() throws Exception {
        User regularUser = new User();
        regularUser.setRole(User.Role.USER);
    
        mockMvc.perform(delete("/api/surveys/delete/1")
                .sessionAttr("user", regularUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
    
    @Test
    void generateReportAsAdmin() throws Exception {
        User adminUser = new User();
        adminUser.setRole(User.Role.ADMIN);
    
        when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));
    
        mockMvc.perform(get("/api/surveys/1/generate")
                .sessionAttr("user", adminUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("survey"))
                .andExpect(view().name("survey-report"))
                .andExpect(status().isOk());
    
        verify(surveyRepository, times(1)).findById(1);
    }
    
    @Test
    void generateReportAsUser() throws Exception {
        User regularUser = new User();
        regularUser.setRole(User.Role.USER);
    
        mockMvc.perform(get("/api/surveys/1/generate")
                .sessionAttr("user", regularUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(view().name("redirect:/api/surveys/list-open"));
    }
    
    @Test
    void createSurveyAsAdmin() throws Exception {
        User adminUser = new User();
        adminUser.setId(1L);
        adminUser.setRole(User.Role.ADMIN);
    
        Survey newSurvey = new Survey();
        newSurvey.setSurveyName("Test Survey");
        newSurvey.setIsOpen(true);
        newSurvey.setCreatorId(1);
    
        mockMvc.perform(post("/api/surveys/save")
                .sessionAttr("user", adminUser)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newSurvey)))
                .andExpect(status().isOk());
    }
    
    @Test
    void updateSurveyAsAdmin() throws Exception {
        User adminUser = new User();
        adminUser.setRole(User.Role.ADMIN);
    
        Survey updatedSurvey = new Survey();
        updatedSurvey.setSurveyName("Updated Survey");
        
        when(surveyRepository.findById(1)).thenReturn(Optional.of(survey));
    
        mockMvc.perform(post("/api/surveys/1/update")
                .sessionAttr("user", adminUser)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedSurvey)))
                .andExpect(status().isOk());
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
