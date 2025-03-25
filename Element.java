package com.canvas.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "element_type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = TextElement.class, name = "TEXT"),
    @JsonSubTypes.Type(value = ImageElement.class, name = "IMAGE"),
    @JsonSubTypes.Type(value = ShapeElement.class, name = "SHAPE")
})
public abstract class Element {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double x;
    private double y;
    private double width;
    private double height;
    
    @ManyToOne
    @JoinColumn(name = "page_id")
    @JsonIgnore
    private Page page;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    public Page getPage() { return page; }
    public void setPage(Page page) { this.page = page; }
}