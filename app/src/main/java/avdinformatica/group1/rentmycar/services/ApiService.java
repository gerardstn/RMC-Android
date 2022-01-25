package avdinformatica.group1.rentmycar.services;

import java.util.List;

import avdinformatica.group1.rentmycar.models.CarResponse;
import avdinformatica.group1.rentmycar.models.ReservationResponse;
import avdinformatica.group1.rentmycar.models.RegisterCarResponse;
import avdinformatica.group1.rentmycar.models.UserResponse;
import avdinformatica.group1.rentmycar.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {

    @PUT("/api/v1/reservations/reservation")
    Call<ReservationResponse> addReservation(@Body ReservationResponse reservationResponse);

    @PUT("/api/v1/clients/register")
    Call<UserResponse> addUser(@Body RegisterResponse registerResponse);

    @POST("/api/v1/clients/login")
    Call<UserResponse> getUser(@Body RegisterResponse registerResponse);

    @POST("/api/v1/cars/available")
    Call<List<CarResponse>> getAvailableCars(@Body CarResponse carResponse);

    @PUT("/api/v1/cars/available")
    Call<UserResponse> addCar(@Body RegisterCarResponse registerCarResponse);
}