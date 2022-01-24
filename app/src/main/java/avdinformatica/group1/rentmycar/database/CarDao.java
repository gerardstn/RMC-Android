package avdinformatica.group1.rentmycar.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import avdinformatica.group1.rentmycar.models.Car;
import avdinformatica.group1.rentmycar.models.CarResponse;

@Dao
public interface CarDao {

    @Query("Select * from Car")
    List<Car> getCarList();

    @Query("Select * from Car WHERE carId=:carId")
    Car getCar(Long carId);

    @Insert
    void insertCar(Car car);

    @Update
    void updateCar(Car car);

    @Delete
    void deleteCar(Car car);

}
