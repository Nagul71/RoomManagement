package com.example.RoomManagement.DTO;

public class RoomCreationDTO {
    private Double squareFeet;
    private String location;
    private String status;
    private Integer beds;
    private Boolean available;
    private String acOrNonAc;
    private Double price;

    public String getAcOrNonAc() {
        return acOrNonAc;
    }

    public void setAcOrNonAc(String acOrNonAc) {
        this.acOrNonAc = acOrNonAc;
    }

    public Double getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(Double squareFeet) {
        this.squareFeet = squareFeet;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
