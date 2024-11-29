package project.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {
    //Fields
    private final TestService testService;

    //Constructor
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public String getTests(Model model) {
        model.addAttribute("tests", testService.getTests());
        return "test-list";
    }

    @PostMapping("/test")
    public String createTest(Test test) {
        testService.createTest(test);
        return "redirect:/test";
    }
}
