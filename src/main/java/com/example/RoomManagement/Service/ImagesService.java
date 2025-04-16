package com.example.RoomManagement.Service;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.RoomManagement.DTO.ImageUploadDTO;
import com.example.RoomManagement.Entity.Images;
import com.example.RoomManagement.Entity.Room;
import com.example.RoomManagement.Repository.ImagesRepository;
import com.example.RoomManagement.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ImagesService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImagesRepository imagesRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public Images uploadImage(ImageUploadDTO imageUploadDTO) throws IOException {
        // Upload to Cloudinary
        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                imageUploadDTO.getFile().getBytes(),
                ObjectUtils.emptyMap()
        );

        // Get the room
        Room room = roomRepository.findById(imageUploadDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Create and save image entity
        Images image = new Images();
        image.setImgUrl(uploadResult.get("url").toString());
        image.setRoom(room);

        return imagesRepository.save(image);
    }

    @Transactional(readOnly = true)
    public List<Images> getImagesByRoomId(String roomId) {
        return imagesRepository.findByRoomRoomId(roomId);
    }

    @Transactional
    public void deleteImage(String imageId) throws IOException {
        Images image = imagesRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image Not Found"));

        // Extract public ID from URL for Cloudinary deletion
        String url = image.getImgUrl();
        String publicId = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));

        // Delete from Cloudinary
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

        // Delete from database
        imagesRepository.delete(image);
    }

    private String generateImageId() {
        SecureRandom random = new SecureRandom();
        char[] alphabet = NanoIdUtils.DEFAULT_ALPHABET;
        int size = 8;
        return "img_" + NanoIdUtils.randomNanoId(random, alphabet, size);
    }
}