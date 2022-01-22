package avdinformatica.group1.rentmycar.models;

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

}