import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pages.HomePage
import utils.ConfProperties
import utils.driver.DriverHandler
import java.time.Duration
import java.util.InvalidPropertiesFormatException
import kotlin.test.assertEquals

class HomePageTest {

    private lateinit var homePageList: ArrayList<HomePage>

    @BeforeEach fun setUpPages() {
        DriverHandler.initDrivers()
        homePageList = ArrayList()
        if (DriverHandler.areDriversEmpty()) throw InvalidPropertiesFormatException("No drivers!")
        DriverHandler.webDrivers.parallelStream().forEach {
            it.get(ConfProperties.getProperty("home.page"))
            it.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10))
            it.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
            homePageList.add(HomePage(it))
            it.manage().window().maximize()
        }
    }

    @AfterEach fun closeDrivers() {
        DriverHandler.closeDrivers()
    }

    @Test fun testHomeRedirect() {
        homePageList.forEach {
            val homeClickUrl = ConfProperties.getProperty("home.click.page")
            it.homeLink.click()
            it.waitForUrl(homeClickUrl)
            assertEquals(homeClickUrl, it.getWebDriver().currentUrl)
        }
    }

    @Test fun testClickForMen() {
        homePageList.forEach {
            val menClickUrl = ConfProperties.getProperty("home.click.men")
            it.menLink.click()
            it.waitForUrl(menClickUrl)
            assertEquals(menClickUrl, it.getWebDriver().currentUrl)
        }
    }

    @Test fun testSearchRedirect() {
        homePageList.forEach {
            val abobaSearch = ConfProperties.getProperty("home.search.aboba")
            it.searchField.sendKeys("aboba")
            it.searchButton.click()
            it.waitForUrl(abobaSearch)
            assertEquals(abobaSearch, it.getWebDriver().currentUrl)
        }
    }

    @Test fun testRedirectCheckout() {
        homePageList.forEach {
            val checkoutUrl = ConfProperties.getProperty("home.account.checkout")
            it.checkoutLink.click()
            it.waitForUrl(checkoutUrl)
            assertEquals(checkoutUrl, it.getWebDriver().currentUrl)
        }
    }

}