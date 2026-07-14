package com.jeevanapp.inamge_processing_app.controller;


import com.jeevanapp.inamge_processing_app.entity.Image;
import com.jeevanapp.inamge_processing_app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam(value = "extractedText", required = false) String extractedText) throws Exception{

        Image image = imageService.saveImage(file, extractedText);

        return "Uploaded Successfully, ID = " + image.getId();
    }


    @GetMapping("/{id}")
    public ResponseEntity<byte[]>  download(@PathVariable Long id){
        Image image = imageService.getImage(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getContentType()))
                .body(image.getData());
    }

    @GetMapping("/getAll")
    public List<Image> getAllImages(){
        return imageService.getAllImages();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id){
        return imageService.deleteImage(id);
    }


}
