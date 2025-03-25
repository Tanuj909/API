package com.canvas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("IMAGE")
public class ImageElement extends Element {
    private String src;

    // Getters and Setters
    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }
}