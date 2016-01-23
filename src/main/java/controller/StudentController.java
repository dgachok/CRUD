package controller;

import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.StudentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String allStudents(HttpServletRequest request, Model model) {
        Integer page;
        if(request.getParameter("page") == null) {
            page = 1;
        } else {
            page = Integer.valueOf(request.getParameter("page"));
        }
        int current = page+1;
        int begin = Math.max(1, current - 10);
        int end = Math.min(begin + 10, studentService.getCountPages());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("students", studentService.getAllStudent(page));
        model.addAttribute("pages", studentService.getCountPages());
        model.addAttribute("current", page);
        return "students";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUserById(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") Student student, Model model,
                             BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
        }
        studentService.addStudent(student);
        return "redirect:/index";
    }
}


