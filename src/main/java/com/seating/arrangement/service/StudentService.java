package com.seating.arrangement.service;

import com.seating.arrangement.entity.Student;
import com.seating.arrangement.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Student save(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @Transactional
    public boolean delete(Integer studentId) {
        try {
            studentRepository.deleteById(studentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}