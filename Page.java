package com.canvas.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import java.util.*;

@Entity
public class Page {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "design_id")
    @JsonIgnoreProperties("pages")
    private Design design;
    
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("page")
    private List<Element> elements = new ArrayList<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Design getDesign() { return design; }
    public void setDesign(Design design) { this.design = design; }
    public List<Element> getElements() { return elements; }
    public void setElements(List<Element> elements) { this.elements = elements; }
    
    public void addElement(Element element) {
        elements.add(element);
        element.setPage(this);
    }
}