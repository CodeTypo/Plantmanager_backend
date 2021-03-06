package com.codetypo.plantmanager.controller;

import com.codetypo.plantmanager.entity.Image;
import com.codetypo.plantmanager.repository.ImageRepo;
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
public class ImageController {
    private final ImageRepo imageRepo;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/image/{userId}")
    Long uploadImage(@RequestParam MultipartFile multipartImage, @PathVariable Long userId) throws Exception {
        Image dbImage = new Image(userId);
        dbImage.setContent(multipartImage.getBytes());

        try {
            imageRepo.deleteByUserId(userId);
        } catch (Exception ignored) {ignored.printStackTrace();}
        return imageRepo.save(dbImage)
                .getId();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource downloadImage(@PathVariable Long imageId) {
        byte[] image = imageRepo.findByUserId(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();
        System.out.println(image);

        return new ByteArrayResource(image);
    }
}
