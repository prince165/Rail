package com.example.bhartiyarail.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PnrStatus {

    @SerializedName("train_name")
    @Expose
    private String trainName;
    @SerializedName("train_number")
    @Expose
    private String trainNumber;
    @SerializedName("passengers")
    @Expose
    private Integer passengers;
    @SerializedName("time")
    @Expose
    private String time;

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

