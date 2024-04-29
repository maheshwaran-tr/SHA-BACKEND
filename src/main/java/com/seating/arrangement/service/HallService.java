package com.seating.arrangement.service;

import com.seating.arrangement.entity.Hall;
import com.seating.arrangement.repository.HallRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    private final HallRepository hallRepository;

    @Autowired
    public HallService(HallRepository hallRepository){
        this.hallRepository = hallRepository;
    }

    public List<Hall> getAllHalls(){
        return hallRepository.findAll();
    }

    public Hall getHallById(Integer hallId){
        return hallRepository.findById(hallId).orElse(null);
    }

    @Transactional
    public Hall save(Hall newHall){
        return hallRepository.save(newHall);
    }

    @Transactional
    public boolean delete(Integer hallId) {
        try {
            hallRepository.deleteById(hallId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
