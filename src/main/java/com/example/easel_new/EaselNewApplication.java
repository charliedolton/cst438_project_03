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

    @RequestMapping("/login")
    public String login(Model model, HttpSession session) {
        String response = "Please enter you username and password";
        model.addAttribute("response", response);
        return "login";
    }

    @PostMapping("/login")
    public String checkUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpServletRequest sessionLink,
                            Model model) {
        User curUser = userRepository.findUserByUsername(username);
        System.out.println(curUser.getUsername());
        List<String> sessionVar = new ArrayList<>();
        if (curUser == null) {
            String response = "Username or Password is incorrect!";
            model.addAttribute("response", response);
            return "/login";
        }

        if (curUser.getPassword().equals(password)) {
            sessionVar.add(curUser.getUsername());
            sessionLink.getSession().setAttribute("user", sessionVar);
            return "redirect:/home";
        } else {
            String response = "Username or Password is incorrect!";
            model.addAttribute("response", response);
            return "/login";
        }
    }

    @RequestMapping("/signup")
    public String signup(Model model, HttpSession session) {
        return "signup";
    }

    @RequestMapping("/home")
    public String home(Model model, HttpSession session) {
        User user = getUserFromSession(session);
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping("/listCourses")
    public String listCourses(Model model, HttpSession session) {
        User user = getUserFromSession(session);
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("courses", courses);
        return "/listCourses";
    }

    @RequestMapping("/userAdded")
    public String userAdded(Model model, HttpSession session) {
        return "userAdded";
    }

    public User getUserFromSession(HttpSession session) {
        String username = ((List<String>)session.getAttribute("user")).get(0);
        User user = userRepository.findUserByUsername(username);
        return user;
    }


    public static void main(String[] args) {
        SpringApplication.run(EaselNewApplication.class, args);
    }

}
