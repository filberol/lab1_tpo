import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pages.ItemCheckoutPage
import pages.NewItemsPage
import utils.ConfProperties
import utils.driver.DriverHandler
import java.time.Duration
import java.util.*
import kotlin.collections.ArrayList
import kotlin.test.assertEquals

class CheckoutTest {

    private lateinit var loginPageList: ArrayList<NewItemsPage>

    @BeforeEach fun setUpPages() {
        DriverHandler.initDrivers()
        loginPageList = ArrayList()
        if (DriverHandler.areDriversEmpty()) throw InvalidPropertiesFormatException("No drivers!")
        DriverHandler.webDrivers.parallelStream().forEach {
            it.get(ConfProperties.getProperty("newitems.home.page"))
            it.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10))
            it.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
            loginPageList.add(NewItemsPage(it))
            it.manage().window().maximize()
        }
    }

    @AfterEach fun closeDrivers() {
        DriverHandler.closeDrivers()
    }

    @Test fun testCheckout() {
        loginPageList.forEach {
            val firstItemUrl = ConfProperties.getProperty("newitems.firstitem")
            val itemSearch = ConfProperties.getProperty("newitems.search.field")
            val searchUrl = ConfProperties.getProperty("newitems.search.url")
            val itemName = ConfProperties.getProperty("newitems.search.item")
            it.searchButton.click()
            Thread.sleep(100)
            it.searchField.sendKeys(itemSearch)
            it.searchButton.click()
            it.waitForUrl(searchUrl)
            it.firstItem.click()
            it.waitForUrl(firstItemUrl)
            assertEquals(it.itemName.text, itemName)
            Thread.sleep(200)
            it.popupClose.click()
            val itemPage = ItemCheckoutPage(it.getWebDriver())
            itemPage.checkoutButton.click()
            itemPage.sizeTakeButton.click()
            itemPage.toCheckoutPage.click()
            val checkoutName = ConfProperties.getProperty("checkout.name")
            val checkoutSize = ConfProperties.getProperty("checkout.size")
            assertEquals(checkoutName, itemPage.itemName.text)
            assertEquals(checkoutSize, itemPage.itemSize.text)
        }
    }
}