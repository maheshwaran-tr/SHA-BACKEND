package com.seating.arrangement.controllers;

import com.seating.arrangement.entity.Hall;
import com.seating.arrangement.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hall")
public class HallController {
    
    @Autowired
    private HallService hallService;
    
    @GetMapping
    public ResponseEntity<List<Hall>> getAllHalls(){
        return ResponseEntity.ok(hallService.getAllHalls());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Hall> getHallById(@PathVariable Integer id){
        return ResponseEntity.ok(hallService.getHallById(id));
    }

    @PostMapping
    public ResponseEntity<Hall> addHall(@RequestBody Hall hall){
        hall.setId(0);
        return ResponseEntity.ok(hallService.save(hall));
    }

    @PutMapping
    public ResponseEntity<Hall> updateHall(@RequestBody Hall hall){
        return ResponseEntity.ok(hallService.save(hall));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHall(@PathVariable Integer id){
        if(hallService.delete(id)){
            return ResponseEntity.ok("Hall deleted");
        }else {
            return ResponseEntity.ok("Hall not deleted");
        }
    }
    
}
