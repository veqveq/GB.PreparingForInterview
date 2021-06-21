package ru.veqveq.lesson7;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/findAll")
    private List<Student> findAll() {
        return studentService.findAll();
    }

    @PostMapping("/del")
    private void remove(@RequestParam Long studentId) {
        studentService.remove(studentId);
    }

    @PostMapping("/add")
    private void addStudent(@RequestBody Student student) {
        studentService.saveOrUpdate(student);
    }
}
