package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class HomePage(
    webDriver: WebDriver
): AbstractPage(webDriver) {
    @FindBy(xpath = "/html/body/div[1]/div/header/div[2]/a")
    lateinit var homeLink: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/header/div[2]/div[2]/nav/a[2]")
    lateinit var menLink: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/header/div[3]/div/div/div/div/button")
    lateinit var searchButton: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/header/div[3]/div/div/div/div/input")
    lateinit var searchField: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/header/div[2]/div[1]/a")
    lateinit var checkoutLink: WebElement
}