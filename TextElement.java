package com.canvas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("TEXT")
public class TextElement extends Element {
    private String text;
    private String fontFamily;
    private int fontSize;
    private String color;

    // Getters and Setters
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getFontFamily() { return fontFamily; }
    public void setFontFamily(String fontFamily) { this.fontFamily = fontFamily; }
    public int getFontSize() { return fontSize; }
    public void setFontSize(int fontSize) { this.fontSize = fontSize; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}