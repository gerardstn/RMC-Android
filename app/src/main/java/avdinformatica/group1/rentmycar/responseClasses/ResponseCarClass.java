package avdinformatica.group1.rentmycar.responseClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ResponseCarClass implements Serializable {


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
    private Integer price;

//    public void setemail(String email) {
//        this.email = email;
//    }
//
//    public String getemail() {
//        return email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//   TextView tvCarBrand;
//    TextView tvCarModel;
//    TextView tvCarDistance;
//    TextView tvCarPrice;

    //get available for rent cars
    public ResponseCarClass(Boolean forRent) {
        this.forRent = forRent;
    }

}


//    private Long carId;
//    private String brand;
//    private String model;
//    private String licensePlate;
//    private String pickupLocationCoordinates;
//    private Long clientId;
//    private Boolean forRent;