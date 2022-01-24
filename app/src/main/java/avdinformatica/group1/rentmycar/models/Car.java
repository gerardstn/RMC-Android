package avdinformatica.group1.rentmycar.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "car")
public class Car {
    private String licensePlate;
    @Nullable
    private String hyperlink;
    @PrimaryKey
    private Long carId;
    private String carType;
    private String brand;
    private String model;
    private String pickupLocationCoordinates;
    private Long clientId;
    private String price;
    private String fuelType;
    @Nullable
    private Double fuelUsage;
    @Nullable
    private Double hydrogenUsage;
    @Nullable
    private Double batteryUsage;

    public Car(String licensePlate, Long carId, String carType, String brand, String model, String pickupLocationCoordinates, Long clientId, String price, String fuelType, @Nullable Double fuelUsage, @Nullable Double hydrogenUsage, @Nullable Double batteryUsage) {
        this.licensePlate = licensePlate;
        this.carId = carId;
        this.carType = carType;
        this.brand = brand;
        this.model = model;
        this.pickupLocationCoordinates = pickupLocationCoordinates;
        this.clientId = clientId;
        this.price = price;
        this.fuelType = fuelType;
        this.fuelUsage = fuelUsage;
        this.hydrogenUsage = hydrogenUsage;
        this.batteryUsage = batteryUsage;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
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

    public String getPickupLocationCoordinates() {
        return pickupLocationCoordinates;
    }

    public void setPickupLocationCoordinates(String pickupLocationCoordinates) {
        this.pickupLocationCoordinates = pickupLocationCoordinates;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Nullable
    public Double getFuelUsage() {
        return fuelUsage;
    }

    public void setFuelUsage(@Nullable Double fuelUsage) {
        this.fuelUsage = fuelUsage;
    }

    @Nullable
    public Double getHydrogenUsage() {
        return hydrogenUsage;
    }

    public void setHydrogenUsage(@Nullable Double hydrogenUsage) {
        this.hydrogenUsage = hydrogenUsage;
    }

    @Nullable
    public Double getBatteryUsage() {
        return batteryUsage;
    }

    public void setBatteryUsage(@Nullable Double batteryUsage) {
        this.batteryUsage = batteryUsage;
    }
}
