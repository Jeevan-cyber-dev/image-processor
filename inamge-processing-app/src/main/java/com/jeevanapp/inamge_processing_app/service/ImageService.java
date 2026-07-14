package com.jeevanapp.inamge_processing_app.service;

import com.jeevanapp.inamge_processing_app.entity.Image;
import com.jeevanapp.inamge_processing_app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(MultipartFile file, String extractedText) throws Exception{
        Image image = new Image();

        image.setFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setExtractedText(extractedText);
        image.setData(file.getBytes());

        return imageRepository.save(image);
    }

    public Image getImage(Long id){
        return imageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Image not found with id: " + id));
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public ResponseEntity<String> deleteImage(Long id) {
        Image img = this.getImage(id);
        if(img != null){
            imageRepository.deleteById(id);
            return new ResponseEntity<>("Image deleted successfully",HttpStatus.OK);
        }else
            return new ResponseEntity<>("Image not found", HttpStatus.BAD_REQUEST);
    }
}
