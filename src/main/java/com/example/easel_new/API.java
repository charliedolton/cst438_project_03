package com.example.easel_new;

import com.example.easel_new.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
                             @RequestParam Boolean isProf,
                             Model model) {
        if (userRepository.existsUserByUsername(username)) {
            String response = "User already exists!";
            model.addAttribute("response", response);
            return "/signup";
        }
        User user = new User(firstName, lastName, username, password, isProf);
        userRepository.save(user);
        return "redirect:/login";
    }

    @RequestMapping("/getUserByUserId")
    public @ResponseBody
    User getUserByUserId(@RequestParam Integer userId){
        return userRepository.findUserByUserId(userId);
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam Integer userId,
                           @RequestParam String username,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String password) {
        User user = userRepository.findUserByUserId(userId);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/home";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    //course API routes
    @RequestMapping("/getAllCourses")
    public @ResponseBody Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/addCourseToUser")
    public String addCourseToUser(@RequestParam Integer userId,
                                  @RequestParam Integer courseId) {
        User user = userRepository.findUserByUserId(userId);
        List<Course> courseList = user.getCourses();
        Course newCourse = courseRepository.findCourseByCourseId(courseId);
        courseList.add(newCourse);
        user.setCourses(courseList);
        return "redirect:/home";
    }

    @PostMapping("/createCourse")
    public String createCourse(@RequestParam String courseName,
                               @RequestParam String courseTime,
                               @RequestParam Integer userId) {
        User user = userRepository.findUserByUserId(userId);
        String fullName = user.getFirstName() + " " + user.getLastName();
        Course course = new Course(courseName, courseTime, fullName);
        courseRepository.save(course);
        List<Course> courseList = user.getCourses();
        courseList.add(course);
        user.setCourses(courseList);
        return "redirect:/home";
    }

    @RequestMapping("/getCourseByCourseId")
    public @ResponseBody Course getCourseByCourseId(@RequestParam Integer courseId) {
        return courseRepository.findCourseByCourseId(courseId);
    }

    @PostMapping("/deleteCourse")
    public String deleteCourse(@RequestParam Integer courseId) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        courseRepository.delete(course);
        return "redirect:/home";
    }

    @PostMapping("/editCourse")
    public String editCourse(@RequestParam Integer courseId,
                             @RequestParam String courseName,
                             @RequestParam String courseTime,
                             @RequestParam String professor) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        course.setCourseName(courseName);
        course.setCourseTime(courseTime);
        course.setProfessor(professor);
        courseRepository.save(course);
        return "redirect:/home";
    }

    //assignment API routes
    @RequestMapping("/getAllAssignments")
    public @ResponseBody Iterable<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    @PostMapping("/createAssignment")
    public String createAssignment(@RequestParam String assignmentName,
                                   @RequestParam Integer courseId,
                                   @RequestParam String assignmentDescription,
                                   @RequestParam String dueDate) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        Assignment assignment = new Assignment(assignmentName, assignmentDescription, dueDate, false);
        assignmentRepository.save(assignment);
        List<Assignment> assignmentList = course.getAssignments();
        assignmentList.add(assignment);
        course.setAssignments(assignmentList);
        return "redirect:/home";
    }

    @PostMapping("/completeAssignment")
    public String completeAssignment(@RequestParam Integer assignmentId) {
        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);
        if (assignment.getComplete()) {
            assignment.setComplete(false);
        }
        else {
            assignment.setComplete(true);
        }
        assignmentRepository.save(assignment);
        return "redirect:/viewAssignment?assignmentId=" + assignmentId;
    }

    @PostMapping("/deleteAssignment")
    public String deleteAssignment(@RequestParam Integer assignmentId) {
        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);
        assignmentRepository.delete(assignment);
        return "redirect:/home";
    }

    @RequestMapping("/getAssignmentByAssignmentId")
    public @ResponseBody Assignment getAssignmentByAssignmentId(@RequestParam Integer assignmentId) {
        return assignmentRepository.findAssignmentByAssignmentId(assignmentId);
    }

    @PostMapping("/editAssignment")
    public String editAssignment(@RequestParam Integer assignmentId,
                                 @RequestParam String assignmentName,
                                 @RequestParam String assignmentDescription,
                                 @RequestParam String dueDate) {
        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);
        assignment.setAssignmentName(assignmentName);
        assignment.setAssignmentDescription(assignmentDescription);
        assignment.setDueDate(dueDate);
        assignmentRepository.save(assignment);
        return "redirect:/home";
    }
}