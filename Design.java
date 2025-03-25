package com.canvas.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import java.util.*;

@Entity
public class Design {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int canvasWidth;
    private int canvasHeight;
    
    @OneToMany(mappedBy = "design", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"design", "elements.page"})
    private List<Page> pages = new ArrayList<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCanvasWidth() { return canvasWidth; }
    public void setCanvasWidth(int canvasWidth) { this.canvasWidth = canvasWidth; }
    public int getCanvasHeight() { return canvasHeight; }
    public void setCanvasHeight(int canvasHeight) { this.canvasHeight = canvasHeight; }
    public List<Page> getPages() { return pages; }
    public void setPages(List<Page> pages) { this.pages = pages; }
    
    public void addPage(Page page) {
        pages.add(page);
        page.setDesign(this);
    }
}