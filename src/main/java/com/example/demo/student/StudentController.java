package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){this.studentService = studentService;}
    @GetMapping
    public List<Student> getStudents() {
return  studentService.getStudents();
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
@DeleteMapping(path="{studentID")
    public void deleteStudent(@PathVariable("studentID") Long id){
        studentService.deleteStudent (id);
    }

@putMapping(path="{studentID")
public void updateStudent(@PathVariable("studentID") Long studentId,
    @RequestParam(required = false) string name,
    @RequestParam(required = false) string email){
        studentService.updateStudent(studentId,name,email);

}
}
