package com.seating.arrangement.repository;

import com.seating.arrangement.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block,Integer> {
}
