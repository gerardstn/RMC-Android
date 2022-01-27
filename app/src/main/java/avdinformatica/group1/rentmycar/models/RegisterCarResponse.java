package avdinformatica.group1.rentmycar.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RegisterCarResponse implements Serializable {


    @SerializedName("brand")
    private String brand;
    @SerializedName("model")
    private String model;
    @SerializedName("pickupLocationCoordinates")
    private String distance;
    @SerializedName("carType")
    private String carType;
    @SerializedName("licensePlate")
    private String licensePlate;
    @SerializedName("clientId")
    private Long clientId;
    @SerializedName("forRent")
    private boolean forRent;

    public RegisterCarResponse(String brand, String model, String distance, String carType, String licensePlate, Long clientId, boolean forRent) {
        this.brand = brand;
        this.model = model;
        this.distance = distance;
        this.carType = carType;
        this.licensePlate = licensePlate;
        this.clientId = clientId;
        this.forRent = forRent;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}