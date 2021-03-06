package avdinformatica.group1.rentmycar.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import avdinformatica.group1.rentmycar.models.Car;

@Dao
public interface CarDao {

    @Query("SELECT * FROM Car")
    List<Car> getCarList();

    @Query("SELECT * FROM Car WHERE carId=:carId")
    Car getCar(Long carId);

    @Insert
    void insertCar(Car car);

    @Update
    void updateCar(Car car);

    @Delete
    void deleteCar(Car car);

}
