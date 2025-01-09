package es.lasalle.pr2

import android.net.Uri
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.espresso.intent.Intents.intended
import org.hamcrest.Matchers.allOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Test
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import org.junit.After

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    // -----------------------------------------------------
    // Constantes
    // -----------------------------------------------------

    private val validEmail = "samuel@lasalle.es"
    private val invalidEmail = "invalidEmail"
    private val correctPassword = "admin1"
    private val incorrectPassword = "1nimda"


    // -----------------------------------------------------
    // Métodos de apertura y cierre de Intents
    // -----------------------------------------------------

    @Before
    fun setUp() {
        Intents.init()
    }


    @After
    fun tearDown() {
        Intents.release()
    }


    // -----------------------------------------------------
    // Métodos auxiliares reutilizables
    // -----------------------------------------------------

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    private fun clickRegisterButton() {
        onView(withId(R.id.registerButton)).perform(click())
    }


    private fun fillEmailField(email: String) {
        onView(withId(R.id.emailFieldEditText)).perform(typeText(email), closeSoftKeyboard())
    }


    private fun fillPasswordField(password: String) {
        onView(withId(R.id.passwordFieldEditText)).perform(typeText(password), closeSoftKeyboard())

    }


    private fun fillConfirmPasswordField(password: String) {
        onView((withId(R.id.confirmPasswordFieldEditText))).perform(typeText(password), closeSoftKeyboard())

    }


    // -----------------------------------------------------
    // Tests
    // -----------------------------------------------------

    @Test
    fun test_invalidEmail_notNavigating() {

        fillEmailField(invalidEmail)
        fillPasswordField(correctPassword)
        fillConfirmPasswordField(correctPassword)

        clickRegisterButton()

        Intents.assertNoUnverifiedIntents()
    }


    @Test
    fun test_nullPassword_notNavigating() {

        fillEmailField(validEmail)
        fillPasswordField("")
        fillConfirmPasswordField("")

        clickRegisterButton()

        Intents.assertNoUnverifiedIntents()
    }


    @Test
    fun test_bothPasswordUnmatch_notNavigating() {

        fillEmailField(validEmail)
        fillPasswordField(correctPassword)
        fillConfirmPasswordField(incorrectPassword)

        clickRegisterButton()

        Intents.assertNoUnverifiedIntents()
    }


    @Test
    fun test_validInputs_navigating() {

        fillEmailField(validEmail)
        fillPasswordField(correctPassword)
        fillConfirmPasswordField(correctPassword)

        clickRegisterButton()

        intended(allOf(hasComponent(ResultActivity::class.java.name)))
    }


    @Test
    fun test_linkLaunches() {

        onView(withId(R.id.needHelpButton)).perform(click())

        intended(hasData(Uri.parse("https://lasallefp.com/contactar")))
    }


    @Test
    fun test_emailFromIntent_displayed() {

        fillEmailField(validEmail)
        fillPasswordField(correctPassword)
        fillConfirmPasswordField(correctPassword)

        clickRegisterButton()

        onView(withId(R.id.textViewEmail)).check(matches(withText(validEmail)))
    }
}