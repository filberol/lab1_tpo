package pages

import utils.driver.DriverConst
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

abstract class AbstractPage(
    private val webDriver: WebDriver
) {
    init {
        PageFactory.initElements(webDriver, this)
    }

    fun waitForUrl(url: String) {
        val wait = WebDriverWait(webDriver, Duration.ofSeconds(DriverConst.DRIVER_WAIT_LOAD))
        wait.until(ExpectedConditions.urlToBe(url))
    }

    fun getWebDriver() = webDriver
}