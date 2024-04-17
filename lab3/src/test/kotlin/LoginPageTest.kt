import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pages.LoginPage
import utils.ConfProperties
import utils.driver.DriverHandler
import java.time.Duration
import java.util.InvalidPropertiesFormatException
import kotlin.test.assertEquals

class LoginPageTest {

    private lateinit var loginPageList: ArrayList<LoginPage>

    @BeforeEach fun setUpPages() {
        DriverHandler.initDrivers()
        loginPageList = ArrayList()
        if (DriverHandler.areDriversEmpty()) throw InvalidPropertiesFormatException("No drivers!")
        DriverHandler.webDrivers.parallelStream().forEach {
            it.get(ConfProperties.getProperty("login.page"))
            it.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10))
            it.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
            loginPageList.add(LoginPage(it))
            it.manage().window().maximize()
        }
    }

    @AfterEach fun closeDrivers() {
        DriverHandler.closeDrivers()
    }

    /*
        Does not work after few tries, enables captcha on sequenced logins
        Passed just once 😶‍🌫️
     */
    @Test fun testLogin() {
        loginPageList.forEach {
            val username = ConfProperties.getProperty("login.username")
            val password = ConfProperties.getProperty("login.password")
            val homeLink = ConfProperties.getProperty("home.page")
            val profileLabel = ConfProperties.getProperty("login.profilelabel")
            it.loginOpenButton.click()
            Thread.sleep(1000)
            it.loginField.sendKeys(username)
            Thread.sleep(1000)
            it.passwordField.sendKeys(password)
            it.loginButton.click()
            it.waitForUrl(homeLink)
            assertEquals(profileLabel, it.profileLabel.text)
        }
    }

}