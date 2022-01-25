package avdinformatica.group1.rentmycar.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import avdinformatica.group1.rentmycar.MainActivity;
import avdinformatica.group1.rentmycar.R;

@RunWith(AndroidJUnit4.class)
public class CameraFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void before(){
        FragmentScenario.launchInContainer(CameraFragment.class);
    }


    @Test
    public void textViewLoadedTest()
    {
        onView(withId(R.id.tv_camera_title)).check(matches(withText("Lets see if the camera is working")));
    }


}
