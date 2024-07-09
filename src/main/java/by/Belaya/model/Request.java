package by.Belaya.model;

import java.sql.Date;

public class Request {
    private int requestId;
    private int clientId;
    private int night;
    private int manCounter;
    private Date arrivalDate;
    private Date endDate;
    private String status;
    private String type;
    private int roomId;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    private double finalPrice;

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getClientId() {
        return clientId;
    }

    public int getNight() {
        return night;
    }

    public int getManCounter() {
        return manCounter;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNight(int night) {
        this.night = night;
    }

    public void setManCounter(int manCounter) {
        this.manCounter = manCounter;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}