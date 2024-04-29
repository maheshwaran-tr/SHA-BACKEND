package com.seating.arrangement.controllers;

import com.seating.arrangement.entity.Exam;
import com.seating.arrangement.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams(){
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Integer id){
        return ResponseEntity.ok(examService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Exam> addExam(@RequestBody Exam exam){
        exam.setId(0);
        return ResponseEntity.ok(examService.save(exam));
    }

    @PutMapping
    public ResponseEntity<Exam> updateExam(@RequestBody Exam exam){
        return ResponseEntity.ok(examService.save(exam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExam(@PathVariable Integer id){
        if(examService.delete(id)){
            return ResponseEntity.ok("Exam deleted");
        }else {
            return ResponseEntity.ok("Exam not deleted");
        }
    }
}
