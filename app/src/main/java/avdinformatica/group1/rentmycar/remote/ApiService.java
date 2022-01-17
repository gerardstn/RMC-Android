package avdinformatica.group1.rentmycar.remote;

import avdinformatica.group1.rentmycar.responseClasses.ResponseCarClass;
import avdinformatica.group1.rentmycar.responseClasses.ResponseClass;
import avdinformatica.group1.rentmycar.responseClasses.ResponseRegisterClass;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {

    @PUT("/api/v1/clients/register")
    Call<ResponseClass> addUser(@Body ResponseRegisterClass responseRegisterClass);

    @POST("/api/v1/clients/login")
    Call<ResponseClass> getUser(@Body ResponseRegisterClass responseRegisterClass);

    @POST("/api/v1/cars/available")
    Call<ResponseClass> getAvailableCars(@Body ResponseCarClass responseCarClass);
}