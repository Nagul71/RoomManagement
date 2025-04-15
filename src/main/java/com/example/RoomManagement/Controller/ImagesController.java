package com.example.RoomManagement.Controller;
import com.example.RoomManagement.DTO.ImageUploadDTO;
import com.example.RoomManagement.Entity.Images;
import com.example.RoomManagement.Service.ImagesService;
import com.example.RoomManagement.Service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImagesController {

    @Autowired
    private ImagesService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Images> uploadImage(@ModelAttribute ImageUploadDTO imageUploadDTO) {
        try {
            Images image = imageService.uploadImage(imageUploadDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Images>> getRoomImages(@PathVariable String roomId) {
        List<Images> images = imageService.getImagesByRoomId(roomId);
        return ResponseEntity.ok(images);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageId) {
        try {
            imageService.deleteImage(imageId);
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image", e);
        }
    }
}
