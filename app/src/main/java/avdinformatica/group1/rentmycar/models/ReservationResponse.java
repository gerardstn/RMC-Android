package avdinformatica.group1.rentmycar.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ReservationResponse implements Serializable {

    @SerializedName("carId")
    private Long carId;

    @SerializedName("clientId")
    private Long clientId;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("endTime")
    private String endTime;

    public ReservationResponse(Long carId, Long clientId, String startTime, String endTime) {
        this.carId = carId;
        this.clientId = clientId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getCarId() {
        return carId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

}