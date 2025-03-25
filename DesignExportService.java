package com.canvas.service;

import org.springframework.stereotype.Service;

import com.canvas.entities.Design;
import com.canvas.exception.DesignNotFoundException;
import com.canvas.exception.ExportException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class DesignExportService {

    private final DesignService designService;
    private final ObjectMapper objectMapper;

    public DesignExportService(DesignService designService, ObjectMapper objectMapper) {
        this.designService = designService;
        this.objectMapper = objectMapper;
    }

    public String exportDesignToJson(Long designId) {
        try {
            Design design = designService.getDesignById(designId)
                    .orElseThrow(() -> new DesignNotFoundException("Design not found with id: " + designId));
            return objectMapper.writeValueAsString(design);
        } catch (JsonProcessingException e) {
            throw new ExportException("Failed to export design to JSON", e);
        }
    }

    public Design importDesignFromJson(String json) {
        try {
            return objectMapper.readValue(json, Design.class);
        } catch (JsonProcessingException e) {
            throw new ExportException("Failed to import design from JSON", e);
        }
    }
}