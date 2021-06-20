package com.codetypo.plantmanager.controller;

import com.codetypo.plantmanager.entity.Image;

import com.codetypo.plantmanager.entity.PlantImage;
import com.codetypo.plantmanager.repository.PlantImageRepo;
import lombok.Data;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Data
@RestController
public class PlantImageController {
    private final PlantImageRepo imageRepo;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/image/plant/{userId}")
    Long uploadImage(@RequestParam MultipartFile multipartImage, @PathVariable Long userId) throws Exception {
        PlantImage dbImage = new PlantImage(userId);
        dbImage.setContent(multipartImage.getBytes());

        try {
            imageRepo.deleteByPlantId(userId);
        } catch (Exception ignored) {ignored.printStackTrace();}
        return imageRepo.save(dbImage)
                .getId();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/image/plant/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource downloadImage(@PathVariable Long imageId) {
        byte[] image = imageRepo.findByPlantId(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();
        System.out.println(image);

        return new ByteArrayResource(image);
    }
}
