package com.canvas.service;

import com.canvas.entities.*;
import com.canvas.exception.DesignNotFoundException;
import com.canvas.repository.DesignRepository;
import com.canvas.repository.PageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DesignService {
    private final DesignRepository designRepository;
    private final PageRepository pageRepository;

    public DesignService(DesignRepository designRepository, 
                       PageRepository pageRepository) {
        this.designRepository = designRepository;
        this.pageRepository = pageRepository;
    }

    public Design createDesignWithContent(Design designRequest) {
        Design design = new Design();
        design.setName(designRequest.getName());
        design.setCanvasWidth(designRequest.getCanvasWidth());
        design.setCanvasHeight(designRequest.getCanvasHeight());

        if (designRequest.getPages() != null) {
            for (Page pageRequest : designRequest.getPages()) {
                Page page = new Page();
                design.addPage(page);
                
                if (pageRequest.getElements() != null) {
                    for (Element elementRequest : pageRequest.getElements()) {
                        Element element = createElementFromRequest(elementRequest);
                        page.addElement(element);
                    }
                }
            }
        } else {
            design.addPage(new Page());
        }
        
        return designRepository.save(design);
    }

    private Element createElementFromRequest(Element elementRequest) {
        if (elementRequest instanceof TextElement) {
            TextElement textElement = (TextElement) elementRequest;
            TextElement newText = new TextElement();
            newText.setX(textElement.getX());
            newText.setY(textElement.getY());
            newText.setWidth(textElement.getWidth());
            newText.setHeight(textElement.getHeight());
            newText.setText(textElement.getText());
            newText.setFontFamily(textElement.getFontFamily());
            newText.setFontSize(textElement.getFontSize());
            newText.setColor(textElement.getColor());
            return newText;
        } else if (elementRequest instanceof ImageElement) {
            ImageElement imageElement = (ImageElement) elementRequest;
            ImageElement newImage = new ImageElement();
            newImage.setX(imageElement.getX());
            newImage.setY(imageElement.getY());
            newImage.setWidth(imageElement.getWidth());
            newImage.setHeight(imageElement.getHeight());
            newImage.setSrc(imageElement.getSrc());
            return newImage;
        } else if (elementRequest instanceof ShapeElement) {
            ShapeElement shapeElement = (ShapeElement) elementRequest;
            ShapeElement newShape = new ShapeElement();
            newShape.setX(shapeElement.getX());
            newShape.setY(shapeElement.getY());
            newShape.setWidth(shapeElement.getWidth());
            newShape.setHeight(shapeElement.getHeight());
            newShape.setShapeType(shapeElement.getShapeType());
            newShape.setFillColor(shapeElement.getFillColor());
            newShape.setBorderColor(shapeElement.getBorderColor());
            return newShape;
        }
        throw new IllegalArgumentException("Unknown element type");
    }

    @Transactional(readOnly = true)
    public Design getFullDesign(Long id) {
        Design design = designRepository.findById(id)
                .orElseThrow(() -> new DesignNotFoundException("Design not found"));
        
        // Initialize collections
        List<Page> pages = pageRepository.findPagesWithElementsByDesignId(id);
        design.setPages(pages);
        
        return design;
    }

    public List<Design> getAllDesigns() {
        return designRepository.findAll();
    }

    public Design updateDesign(Design design) {
        if (!designRepository.existsById(design.getId())) {
            throw new DesignNotFoundException("Design not found");
        }
        return designRepository.save(design);
    }

    public void deleteDesign(Long id) {
        if (!designRepository.existsById(id)) {
            throw new DesignNotFoundException("Design not found");
        }
        designRepository.deleteById(id);
    }

	public Object getDesignById(Long designId) {
		// TODO Auto-generated method stub
		return null;
	}
}