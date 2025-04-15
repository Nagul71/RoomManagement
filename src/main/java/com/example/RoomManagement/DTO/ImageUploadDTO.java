package com.example.RoomManagement.DTO;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadDTO {
    private MultipartFile file;
    private String roomId;

    // Getters and Setters
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}