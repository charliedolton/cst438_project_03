package com.example.easel_new;

import com.example.easel_new.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class EaselNewApplication {

    public static String BASE_URI = "https://localhost:8080/";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    @RequestMapping("/")
    public String landingPage(Model model, HttpSession session) {

        return "landingPage";
    }



    public static void main(String[] args) {
        SpringApplication.run(EaselNewApplication.class, args);
    }

}
