package com.canvas.service;

import com.canvas.entities.*;
import com.canvas.exception.ElementNotFoundException;
import com.canvas.repository.ElementRepository;
import com.canvas.repository.PageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ElementService {

    private final ElementRepository elementRepository;
    private final PageRepository pageRepository;

    public ElementService(ElementRepository elementRepository, PageRepository pageRepository) {
        this.elementRepository = elementRepository;
        this.pageRepository = pageRepository;
    }

    // Generic element operations
    @Transactional(readOnly = true)
    public Element getElementById(Long id) {
        return elementRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Element not found with id: " + id));
    }

    public void deleteElement(Long elementId) {
        Element element = getElementById(elementId);
        elementRepository.delete(element);
    }

    public Element updateElementPosition(Long elementId, double x, double y) {
        Element element = getElementById(elementId);
        element.setX(x);
        element.setY(y);
        return elementRepository.save(element);
    }

    public Element updateElementSize(Long elementId, double width, double height) {
        Element element = getElementById(elementId);
        element.setWidth(width);
        element.setHeight(height);
        return elementRepository.save(element);
    }

    // Text element operations
    public TextElement createTextElement(Long pageId, String text, String fontFamily, 
                                       int fontSize, String color, double x, double y, 
                                       double width, double height) {
        Page page = pageRepository.findById(pageId)
                .orElseThrow(() -> new ElementNotFoundException("Page not found with id: " + pageId));
        
        TextElement element = new TextElement();
        element.setText(text);
        element.setFontFamily(fontFamily);
        element.setFontSize(fontSize);
        element.setColor(color);
        element.setX(x);
        element.setY(y);
        element.setWidth(width);
        element.setHeight(height);
        
        page.addElement(element);
        return (TextElement) elementRepository.save(element);
    }

    public TextElement updateTextElement(Long elementId, String text, String fontFamily, 
                                       Integer fontSize, String color) {
        TextElement element = (TextElement) getElementById(elementId);
        if (text != null) element.setText(text);
        if (fontFamily != null) element.setFontFamily(fontFamily);
        if (fontSize != null) element.setFontSize(fontSize);
        if (color != null) element.setColor(color);
        return (TextElement) elementRepository.save(element);
    }

    // Image element operations
    public ImageElement createImageElement(Long pageId, String imageUrl, 
                                         double x, double y, double width, double height) {
        Page page = pageRepository.findById(pageId)
                .orElseThrow(() -> new ElementNotFoundException("Page not found with id: " + pageId));
        
        ImageElement element = new ImageElement();
        element.setSrc(imageUrl);
        element.setX(x);
        element.setY(y);
        element.setWidth(width);
        element.setHeight(height);
        
        page.addElement(element);
        return (ImageElement) elementRepository.save(element);
    }

    public ImageElement updateImageElement(Long elementId, String newImageUrl) {
        ImageElement element = (ImageElement) getElementById(elementId);
        if (newImageUrl != null) element.setSrc(newImageUrl);
        return (ImageElement) elementRepository.save(element);
    }

    // Shape element operations
    public ShapeElement createShapeElement(Long pageId, String shapeType, String fillColor, 
                                         String borderColor, double x, double y, 
                                         double width, double height) {
        Page page = pageRepository.findById(pageId)
                .orElseThrow(() -> new ElementNotFoundException("Page not found with id: " + pageId));
        
        ShapeElement element = new ShapeElement();
        element.setShapeType(shapeType);
        element.setColor(fillColor);
        element.setBorderColor(borderColor);
        element.setX(x);
        element.setY(y);
        element.setWidth(width);
        element.setHeight(height);
        
        page.addElement(element);
        return (ShapeElement) elementRepository.save(element);
    }

    public ShapeElement updateShapeStyle(Long elementId, String newFillColor, String newBorderColor) {
        ShapeElement element = (ShapeElement) getElementById(elementId);
        if (newFillColor != null) element.setColor(newFillColor);
        if (newBorderColor != null) element.setBorderColor(newBorderColor);
        return (ShapeElement) elementRepository.save(element);
    }
}