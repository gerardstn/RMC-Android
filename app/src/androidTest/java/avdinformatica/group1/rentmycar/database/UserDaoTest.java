package avdinformatica.group1.rentmycar.database;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;

import static com.google.android.gms.common.util.ArrayUtils.contains;

import static org.hamcrest.Matchers.equalTo;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.filters.SmallTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import avdinformatica.group1.rentmycar.models.User;
import kotlinx.coroutines.ExperimentalCoroutinesApi;

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4.class)
@SmallTest
public class UserDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = InstantTaskExecutorRule();

    public UserDao dao;
    public User user;
    public AppDatabase database;

    private InstantTaskExecutorRule InstantTaskExecutorRule() {
        return null;
    }

    @Before
    public void setup(){
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase.class
        ).allowMainThreadQueries().build();
        dao = database.userDao();
    }

    @After
    public void teardown() throws IOException {
        database.close();
    }

    @Test
    public void insertUser() throws Exception{
        user = new User("kees.brood@gmail.nl", "Kees", "Brood", "00000000-0000-0000-00000001", 4L);
        dao.insertUser(user);

        User sut = dao.getUser("00000000-0000-0000-00000001");
        assertThat(sut.getSessionId(), equalTo(user.getSessionId()));
        assertThat(sut.getEmail(), equalTo(user.getEmail()));

    }

}
