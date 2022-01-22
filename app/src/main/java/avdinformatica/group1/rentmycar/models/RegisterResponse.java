package avdinformatica.group1.rentmycar.models;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class RegisterResponse implements Serializable {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public void setemail(String email) {
        this.email = email;
    }

    public String getemail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public RegisterResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }


}