package om.example.android.androidproject;

import android.widget.Toast;

import androidx.test.espresso.Root;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class LoginUiTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @BeforeClass
    public static void setUp()
    {
        logOut();
    }

    public static void logOut()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            FirebaseAuth.getInstance().signOut();
        }
    }


    @Test
    public void testUserNameInputFieldExists()
    {
        onView(withId(R.id.usernameText)).perform().check(matches(isDisplayed()));
    }


    @Test
    public void testPasswordInputFieldExists()
    {
        onView(withId(R.id.passwordText)).perform().check(matches(isDisplayed()));
    }

    @Test
    public void testLoginButtonExists()
    {
        onView(withId(R.id.LoginButton)).perform().check(matches(isDisplayed()));
    }



    @Test
    public void testIfLoginSuccessful()
    {
       onView(withId(R.id.usernameText)).perform(typeText("dora@dora.com"), closeSoftKeyboard());
       onView(withId(R.id.passwordText)).perform(typeText("dora123#"),closeSoftKeyboard());
       onView(withId(R.id.LoginButton)).perform(click());
    }

    @Test
    public void emptyFieldsToastTest() throws InterruptedException {
        onView(withId(R.id.usernameText)).perform(typeText("dora@dora.com"), closeSoftKeyboard());
//        onView(withId(R.id.passwordText)).perform(typeText("dora123#"),closeSoftKeyboard());
        onView(withId(R.id.LoginButton)).perform(click());
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(Matchers.not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

}
