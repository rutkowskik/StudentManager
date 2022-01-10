package com.kacper.studentmanager;

import com.kacper.studentmanager.exception.StudentNotFound;
import com.kacper.studentmanager.model.Student;
import com.kacper.studentmanager.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student addStudent(Student student){
        student.setStudentCode(UUID.randomUUID().toString());
        return studentRepo.save(student);
    }

    public List<Student> findAllStudents(){
        return studentRepo.findAll();
    }

    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student findStudentById(Long id) {
        return studentRepo.findStudentById(id)
                .orElseThrow(()->new StudentNotFound("Student with ID: " + id + " was not found"));
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepo.existsById(id);
        if(!exists){
            throw new IllegalStateException("Student with ID: " + id + " does not exist in DB!");
        }
        studentRepo.deleteStudentById(id);
    }
}

