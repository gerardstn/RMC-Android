package avdinformatica.group1.rentmycar.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RegisterCarResponse implements Serializable {

    @SerializedName("brand")
    private String et_car_register_brand;

    @SerializedName("model")
    private String et_car_register_model;

    @SerializedName("licensePlate")
    private String et_car_register_license_plate;

    @SerializedName("engineType")
    private String engineType;

    @SerializedName("location")
    private String et_car_register_location;

    public RegisterCarResponse(String brand, String model, String licensePlate, String engineType, String location) {
        this.et_car_register_brand = brand;
        this.et_car_register_model = model;
        this.et_car_register_license_plate = licensePlate;
        this.engineType = engineType;
        this.et_car_register_location = location;
    }

    public String getBrand() {
        return et_car_register_brand;
    }

    public void setBrand(String brand) {
        this.et_car_register_brand = brand;
    }

    public String getModel() {
        return et_car_register_model;
    }

    public void setModel(String model) {
        this.et_car_register_model = model;
    }

    public String getLicensePlate() {
        return et_car_register_license_plate;
    }

    public void setLicensePlate(String licensePlate) {
        this.et_car_register_license_plate = licensePlate;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getLocation() {
        return et_car_register_location;
    }

    public void setLocation(String location) {
        this.et_car_register_location = location;
    }
}