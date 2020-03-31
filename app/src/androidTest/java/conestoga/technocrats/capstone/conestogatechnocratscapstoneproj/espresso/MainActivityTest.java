package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.espresso;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.MainActivity;


/*https://developer.android.com/training/testing/espresso*/

/*Under Settings -> Developing Options turn off the following:

        Window animation scale
        Transition animation scale
        Animator duration scale*/

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
public class MainActivityTest
{
    @Rule
    public androidx.test.rule.ActivityTestRule<MainActivity> mainActivityTestRule=
            new androidx.test.rule.ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void registerNewPropertyTestCase()
    {
        Espresso.onView(ViewMatchers.withId(R.id.floatingButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.editTitle))
                .perform(ViewActions.typeText("Property Title"))
                .check(ViewAssertions.matches(ViewMatchers.withText("Property Title")));

        Espresso.onView(ViewMatchers.withId(R.id.editRent))
                .perform(ViewActions.scrollTo(),ViewActions.typeText("100000"));

        Espresso.onView(ViewMatchers.withId(R.id.editSize))
                .perform(ViewActions.scrollTo(),ViewActions.typeText("500"));

        Espresso.onView(ViewMatchers.withId(R.id.editDuration))
                .perform(ViewActions.scrollTo(),ViewActions.typeText("12"));

        Espresso.onView(ViewMatchers.withId(R.id.editBedroomCount))
                .perform(ViewActions.scrollTo(),ViewActions.typeText("3"));

        Espresso.onView(ViewMatchers.withId(R.id.editBathroomCount))
                .perform(ViewActions.scrollTo(),ViewActions.typeText("2"));

        Espresso.onView(ViewMatchers.withId(R.id.hydroId))
                .perform(ViewActions.scrollTo(),ViewActions.click());
    }
}
