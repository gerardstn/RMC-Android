package avdinformatica.group1.rentmycar;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkIfLoginFieldsAreThere(){
        onView(withId(R.id.et_email)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.et_password)).perform(click()).check(matches(isDisplayed()));
        onView(withText("Login")).check(matches(isDisplayed()));
    }

    @Test
    public void ensureLoginWorks(){
        onView(withId(R.id.et_email)).perform(typeText("kees.brood@gmail.nl"));
        onView(withId(R.id.et_password)).perform(typeText("password123"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        onView(isRoot()).perform(waitId(R.id.tv_LoginSuccess, TimeUnit.SECONDS.toMillis(10)));

        onView(withId(R.id.tv_LoginSuccess)).check(matches(withText("Welcome")));
        onView(withId(R.id.tv_Email)).check(matches(withText("Kees.brood@gmail.nl")));
    }

    public static ViewAction waitId(final int viewId, final long millis){
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for a specific view with id <" + viewId + "> during " + millis + " milliseconds";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)){
                        if (viewMatcher.matches(child)){
                            return;
                        }
                    }
                    uiController.loopMainThreadForAtLeast(50);
                }
                while(System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
}
