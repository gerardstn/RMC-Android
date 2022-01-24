package avdinformatica.group1.rentmycar.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CarResponse implements Serializable {


    @SerializedName("brand")
    private String brand;
    @SerializedName("model")
    private String model;
    @SerializedName("pickupLocationCoordinates")
    private String distance;
    @SerializedName("forRent")
    private Boolean forRent;
    @SerializedName("carType")
    private String carType;
    @SerializedName("price")
    private String price;
    @SerializedName("carId")
    private Long carId;
    private String licensePlate;
    private Long clientId;
    private String fuelType;
    @Nullable
    private Double fuelUsage;
    @Nullable
    private Double hydrogenUsage;
    @Nullable
    private Double batteryUsage;

    public String getLicensePlate() {
        return licensePlate;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getFuelType() {
        return fuelType;
    }

    @Nullable
    public Double getFuelUsage() {
        return fuelUsage;
    }

    @Nullable
    public Double getHydrogenUsage() {
        return hydrogenUsage;
    }

    @Nullable
    public Double getBatteryUsage() {
        return batteryUsage;
    }

    public Long getCarId() {
        return carId;
    }

    //get available for rent cars
    public CarResponse(Boolean forRent) {
        this.forRent = forRent;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getDistance() {
        return distance;
    }

    public Boolean getForRent() {
        return forRent;
    }

    public String getCarType() {
        return carType;
    }

    public String getPrice() {
        return price;
    }

    public Car toCar(){
        return new Car(this.getLicensePlate(),
                this.getCarId(),
                this.getCarType(),
                this.getBrand(),
                this.getModel(),
                this.getDistance(),
                this.getClientId(),
                this.getPrice(),
                this.getFuelType(),
                this.getFuelUsage(),
                this.getHydrogenUsage(),
                this.getBatteryUsage());
    }


}