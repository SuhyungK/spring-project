package hw.responseobj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/api/student";
    }

    @GetMapping("/api/student")
    public String index() {
        return "index";
    }

    @GetMapping("/api/student/grade")
    public String inputScore() {
        return "grade";
    }


}
