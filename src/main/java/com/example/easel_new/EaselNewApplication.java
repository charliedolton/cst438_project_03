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
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping("/listCourses")
    public String listCourses(Model model, HttpSession session) {
        User user = getUserFromSession(session);
        if (user == null) {
            return "login";
        }
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("courses", courses);
        return "/listCourses";
    }

    @RequestMapping("/createCourse")
    public String createCourse(Model model, HttpSession session) {
        User user = getUserFromSession(session);
        if (user == null) {
            return "login";
        }
        if (!(user.getProf())) {
            return "/home";
        }
        model.addAttribute("user", user);
        return "/createCourse";
    }

    @RequestMapping("/createAssignment")
    public String createAssignment(Model model, HttpSession session) {
        User user = getUserFromSession(session);
        if (user == null) {
            return "login";
        }
        if (!(user.getProf())) {
            return "/home";
        }
        model.addAttribute("user", user);
        return "/createAssignment";
    }

    @RequestMapping("/viewAssignment")
    public String viewAssignment(Model model, HttpSession session,
                                 @RequestParam Integer assignmentId) {
        User user = getUserFromSession(session);
        if (user == null) {
            return "login";
        }
        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);
        Course course = courseRepository.findCourseByAssignmentsContains(assignment);
        model.addAttribute("user", user);
        model.addAttribute("assignment", assignment);
        model.addAttribute("course", course);
        return "/viewAssignment";
    }

    @RequestMapping("/viewCourse")
    public String viewCourse(Model model, HttpSession session,
                             @RequestParam Integer courseId) {
        User user = getUserFromSession(session);
        if (user == null) {
            return "login";
        }
        Course course = courseRepository.findCourseByCourseId(courseId);
        model.addAttribute("user", user);
        model.addAttribute("course", course);
        return "/viewCourse";
    }

    @RequestMapping("/editUser")
    public String editUser(Model model, HttpSession session) {
        User user = getUserFromSession(session);
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "/editUser";
    }

    @RequestMapping("/editCourse")
    public String editCourse(Model model, HttpSession session,
                             @RequestParam Integer courseId) {
        User user = getUserFromSession(session);
        if (user == null) {
            return "login";
        }
        if (!(user.getProf())) {
            return "/home";
        }
        Course course = courseRepository.findCourseByCourseId(courseId);
        model.addAttribute("course", course);
        return "editCourse";
    }

    @RequestMapping("/editAssignment")
    public String editAssignment(Model model, HttpSession session,
                                 @RequestParam Integer assignmentId) {
        User user = getUserFromSession(session);
        if (user == null) {
            return "login";
        }
        if (!(user.getProf())) {
            return "/home";
        }
        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);
        model.addAttribute("assignmnet", assignment);
        return "editAssignment";
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
