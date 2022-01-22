package avdinformatica.group1.rentmycar.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import avdinformatica.group1.rentmycar.models.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getUserList();

    @Query("SELECT * FROM user WHERE sessionId=:sessionId")
    User getUser(String sessionId);

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
