package com.project3.easel;

import com.project3.easel.database.AssignmentRepository;
import com.project3.easel.database.CourseRepository;
import com.project3.easel.database.User;
import com.project3.easel.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class EaselApplication {

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

    @RequestMapping("/login")
    public String login(Model model, HttpSession session) {
        return "login";
    }

    @PostMapping("/login")
    public String checkUser(@ModelAttribute("user") User user, HttpServletRequest sessionLink) {
        User curUser = userRepository.findUserByUserId(user.getUserId());
        List<User> sessionVar = new ArrayList<>();
        if (curUser == null) {
            return "Username or Password is incorrect!";
        }

        if (curUser.getPassword().equals(user.getPassword())) {
            sessionVar.add(user);
            sessionLink.getSession().setAttribute("user", sessionVar);
            return "redirect:/home";
        } else {
            return "Username or Password is incorrect!";
        }
    }

    @RequestMapping("/signup")
    public String signup(Model model, HttpSession session) {
        return "signup";
    }

    @RequestMapping("/home")
    public String home(Model model, HttpSession session) {
        return "home";
    }


    public static void main(String[] args) {
        SpringApplication.run(EaselApplication.class, args);
    }

}
