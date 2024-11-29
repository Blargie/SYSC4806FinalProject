package project.survey;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project.question.Question;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/survey")
public class SurveyController2 {
    //Fields
    private final SurveyService surveyService;

    //Constructor
    public SurveyController2(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    //Home Page
    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    
    //Create Survey
    @RequestMapping("/create-survey")
    public String createSurvey(Survey survey) {
        return "create-survey";
    }

    @PostMapping("/save-survey")
    public ResponseEntity<Map<String, String>> saveSurvey(@RequestBody Survey survey) {
        survey.setIsOpen(true);
        survey.setIsAnonymous(true);
        survey.setCreatedAt(LocalDateTime.now());
    
        Survey savedSurvey = surveyService.createSurvey(survey);
    
        Map<String, String> response = new HashMap<>();
        response.put("message", "Survey created successfully with " + savedSurvey.getQuestionCount() + " questions");
        return ResponseEntity.ok(response);
    }

    //View Surveys
    @RequestMapping("/view-survey")
    public String viewSurvey() {
        return "ViewSurvey";
    }

    //List Surveys
    @RequestMapping("/list-survey")
    public String getSurveys(Model model) {
        model.addAttribute("surveys", surveyService.getSurveys());
        return "survey-list";
    }
}
