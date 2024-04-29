package com.seating.arrangement.service;

import com.seating.arrangement.entity.Block;
import com.seating.arrangement.entity.Hall;
import com.seating.arrangement.repository.BlockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    private final BlockRepository blockRepository;

    @Autowired
    public BlockService(BlockRepository blockRepository){
        this.blockRepository = blockRepository;
    }

    public List<Block> getAllBlocks(){
        return blockRepository.findAll();
    }

    public Block getBlockById(Integer id){
        return blockRepository.findById(id).orElse(null);
    }

    @Transactional
    public Block save(Block newBlock){
        return blockRepository.save(newBlock);
    }

    @Transactional
    public boolean delete(Integer blockId) {
        try {
            blockRepository.deleteById(blockId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
