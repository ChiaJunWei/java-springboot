package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class StudentService {
private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){this.studentRepository = studentRepository;}
        @GetMapping
        public List<Student> getStudents() {
//            return List.of(
//                    new Student(1L, "maria", "maria@gmail.com", LocalDate.of(2000, Month.JANUARY, 5), 21));
//
//
//        }
            return studentRepository.findAll();}

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.
                findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        System.out.print(student);
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException(("student" +studentId+"doesnt exist"));
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(studentId+"doesn't exist"));
        if (name !=null && !name.isEmpty() && !Objects.equals(student.getName(),name)){student.setName(name);}

        if (email != null &&
                !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional=studentRepository.
                    findStudentByEmail(email);
            if (studentOptional.isPresent()){
    throw new IllegalStateException("email taken");}

            }student.setEmail(email);
        }




    }
}




