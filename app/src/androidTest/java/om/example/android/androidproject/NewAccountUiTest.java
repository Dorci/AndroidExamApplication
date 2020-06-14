package om.example.android.androidproject;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
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
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class NewAccountUiTest {
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

    @Before
    public void changeToCreateAcc() {
        onView(withId(R.id.CreateAccountButton)).perform(click());

    }

    @Test
    public void testUserNameInputFieldExists()
    {
        onView(withId(R.id.usernameForCreateAccountText)).perform().check(matches(isDisplayed()));
    }

    @Test
    public void testEmailInputFieldExists()
    {
        onView(withId(R.id.emailForCreateAccountText)).perform().check(matches(isDisplayed()));
    }

    @Test
    public void testPasswordInputFieldExists()
    {
        onView(withId(R.id.passwordForCreateAccountText)).perform().check(matches(isDisplayed()));
    }

    @Test
    public void testConfirmButtonExists()
    {
        onView(withId(R.id.ConfirmCreateButton)).perform().check(matches(isDisplayed()));
    }

    @Test
    public void testIfNewAccountCreationIsSuccessful()
    {
        onView(withId(R.id.usernameForCreateAccountText)).perform(typeText("Elle"), closeSoftKeyboard());
        onView(withId(R.id.emailForCreateAccountText)).perform(typeText("elle@elle.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordForCreateAccountText)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.ConfirmCreateButton)).perform(click());
    }

    @Test
    public void emptyFieldsToastTest()
    {
        onView(withId(R.id.emailForCreateAccountText)).perform(typeText("lol@lol.com"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmCreateButton)).perform(click());
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
    @Test
    public void emptyUsernameToastTest()
    {
        onView(withId(R.id.emailForCreateAccountText)).perform(typeText("lol@lol.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordForCreateAccountText)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmCreateButton)).perform(click());

    }
    @Test
    public void emptyEmailToastTest() {
        onView(withId(R.id.usernameForCreateAccountText)).perform(typeText("Elle"), closeSoftKeyboard());
        onView(withId(R.id.passwordForCreateAccountText)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmCreateButton)).perform(click());
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void emptyPasswordToastTest()
    {
        onView(withId(R.id.usernameForCreateAccountText)).perform(typeText("Elle"), closeSoftKeyboard());
        onView(withId(R.id.emailForCreateAccountText)).perform(typeText("lol@lol.com"), closeSoftKeyboard());
        onView(withId(R.id.ConfirmCreateButton)).perform(click());
        onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }







}
