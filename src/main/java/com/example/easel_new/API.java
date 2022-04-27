package com.example.easel_new;

import com.example.easel_new.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class API {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    //user API routes
    @RequestMapping("/getAllUsers")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String username,
                             @RequestParam String password,
                             @RequestParam Boolean isProf) {
        User user = new User(firstName, lastName, username, password, isProf);
        userRepository.save(user);
        return "redirect:/userAdded";
    }

//    @RequestMapping("/checkUser")
//    public String checkUser(@RequestParam String username,
//                            @RequestParam String password) {
//        User user = userRepository.findUserByUsername(username);
//        if (user.getPassword().equals(password)) {
//            HttpS
//        }
//    }

    //course API routes
    @RequestMapping("/getAllCourses")
    public @ResponseBody Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    //assignment API routes
    @RequestMapping("/getAllAssignments")
    public @ResponseBody Iterable<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }


}