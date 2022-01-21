package avdinformatica.group1.rentmycar.models;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class UserResponse implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("brand")
    private String brand;

    @SerializedName("model")
    private String model;

    @SerializedName("pickupLocationCoordinates")
    private String distance;

    @SerializedName("price")
    private Integer price;

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    public Integer getPrice(){
        return price;
    }
}