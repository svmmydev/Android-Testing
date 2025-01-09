package es.lasalle.pr2

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ResultActivityTest {


    // -----------------------------------------------------
    // Tests
    // -----------------------------------------------------

    @Test
    fun test_emailFromIntent_displayed() {

        val testEmail = "samuel@lasalle.es"

        val intent = Intent(ApplicationProvider.getApplicationContext(), ResultActivity::class.java).apply {
            putExtra("email", testEmail)
        }

        ActivityScenario.launch<ResultActivity>(intent)

        onView(withId(R.id.textViewEmail))
            .check(matches(withText(testEmail)))
    }
}