package com.seating.arrangement.controllers;

import com.seating.arrangement.entity.Hall;
import com.seating.arrangement.entity.Student;
import com.seating.arrangement.service.HallService;
import com.seating.arrangement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/arrange")
public class ArrangeController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private HallService hallService;


    @GetMapping
    public List<Hall> startArrange(){
        List<Student> studentList = studentService.getAllStudents();
        List<Hall> examHallList = hallService.getAllHalls();
        return arrangeSeats(studentList,examHallList);
    }


    public List<Hall> arrangeSeats(List<Student> studentList,List<Hall> examHallList ) {
        boolean isArrangeble = check(studentList, examHallList);


        if (isArrangeble) {
            int hallIndex = 0;
            for (Student theStudent : studentList) {
                Hall hall = examHallList.get(hallIndex);
                if (hall.getCapacity() > 0) {
                    boolean isArranged = perform(hall, theStudent);
                    if (!isArranged) {
                        hallIndex = (hallIndex + 1) % examHallList.size();
                        hall = examHallList.get(hallIndex);
                        perform(hall, theStudent);
                    }
                } else {
                    hallIndex = (hallIndex + 1) % examHallList.size();
                    hall = examHallList.get(hallIndex);
                    perform(hall, theStudent);
                }
            }
        }
        return examHallList;
    }

    static boolean perform(Hall hall, Student theStudent) {
        for (int col = 0; col <= (hall.getColumns()) - hall.getSeatPerBench(); col += hall.getSeatPerBench()) {
            for (int row = 0; row < hall.getRows(); row++) {
                if (hall.getHallMatrix()[row][col].student == null) {
                    hall.getHallMatrix()[row][col].student = theStudent;
                    hall.setCapacity(hall.getCapacity() - 1);
                    return true;
                }
            }
        }
        return false;
    }


    static boolean check(List<Student> studentList, List<Hall> examHallList) {

        int noOfStudents = studentList.size();

        int totalNoOfSeats = 0;
        for (Hall hall : examHallList) {
            totalNoOfSeats += hall.getCapacity();
        }

        System.out.println("No Of Students : " + noOfStudents);
        System.out.println("No Of Seats : " + totalNoOfSeats);

        return noOfStudents <= totalNoOfSeats;
    }

}
