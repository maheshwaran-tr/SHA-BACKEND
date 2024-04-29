package com.seating.arrangement.service;

import com.seating.arrangement.entity.Exam;
import com.seating.arrangement.repository.ExamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getById(Integer id){
        return examRepository.findById(id).orElse(null);
    }

    @Transactional
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Transactional
    public boolean delete(Integer examId) {
        try {
            examRepository.deleteById(examId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
