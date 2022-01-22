package avdinformatica.group1.rentmycar.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "car")
public class Car {
    @PrimaryKey
    @NonNull
    private String licensePlate;
    private String hyperlink;

    public Car(String licensePlate, String hyperlink) {
        this.licensePlate = licensePlate;
        this.hyperlink = hyperlink;
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


}
