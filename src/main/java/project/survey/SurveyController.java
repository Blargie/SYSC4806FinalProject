package project.survey;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller  // Changed from @RestController
@RequestMapping("/api/surveys")
public class SurveyController {
    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("survey", new Survey());
        return "create-survey";
    }

    @PostMapping("/save")
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) {
        survey.setIsOpen(true); // New surveys are open by default
        Survey savedSurvey = surveyRepository.save(survey);
        return ResponseEntity.ok(savedSurvey);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<Survey> getSurvey(@PathVariable Integer surveyId) {
        List<Survey> surveys = surveyRepository.findBySurveyId(surveyId);
        if (surveys.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(surveys.get(0));
    }

    @GetMapping("/list")
    public String listSurveys(Model model) {
        Iterable<Survey> surveys = surveyRepository.findAll();
        model.addAttribute("surveys", surveys);
        return "survey-list";  // Name of the template above
    }
//test
}