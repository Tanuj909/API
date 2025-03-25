package com.canvas.controller;

import com.canvas.entities.Design;
import com.canvas.service.DesignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designs")
public class DesignController {
    private final DesignService designService;

    public DesignController(DesignService designService) {
        this.designService = designService;
    }

    @PostMapping
    public ResponseEntity<Design> createDesign(@RequestBody Design design) {
        Design createdDesign = designService.createDesignWithContent(design);
        return ResponseEntity.status(201).body(createdDesign);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Design> getDesign(@PathVariable Long id) {
        Design design = designService.getFullDesign(id);
        return ResponseEntity.ok(design);
    }

    @GetMapping
    public List<Design> getAllDesigns() {
        return designService.getAllDesigns();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Design> updateDesign(@PathVariable Long id, @RequestBody Design design) {
        design.setId(id);
        Design updatedDesign = designService.updateDesign(design);
        return ResponseEntity.ok(updatedDesign);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesign(@PathVariable Long id) {
        designService.deleteDesign(id);
        return ResponseEntity.noContent().build();
    }
}