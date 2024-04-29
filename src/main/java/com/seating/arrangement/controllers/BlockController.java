package com.seating.arrangement.controllers;

import com.seating.arrangement.entity.Block;
import com.seating.arrangement.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/block")
public class BlockController {

    @Autowired
    private BlockService blockService;

    @GetMapping
    public ResponseEntity<List<Block>> getAllBlocks(){
        return ResponseEntity.ok(blockService.getAllBlocks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Block> getBlockById(@PathVariable Integer id){
        return ResponseEntity.ok(blockService.getBlockById(id));
    }

    @PostMapping
    public ResponseEntity<Block> addBlock(@RequestBody Block newBlock){
        newBlock.setId(0);
        return ResponseEntity.ok(blockService.save(newBlock));
    }

    @PutMapping
    public ResponseEntity<Block> updateBlock(@RequestBody Block block){
        return ResponseEntity.ok(blockService.save(block));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlock(@PathVariable Integer id){
        if(blockService.delete(id)){
            return ResponseEntity.ok("Block Deleted");
        }
        return ResponseEntity.ok("Block Not Deleted");
    }

}
