package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class NewItemsPage(
    webDriver: WebDriver
): AbstractPage(webDriver) {
    @FindBy(xpath = "/html/body/div[1]/div/main/div/div[2]/div/div[2]/div[2]/div[2]/div/div[1]/div/div[1]/a")
    lateinit var firstItem: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/header/div[3]/div/div/div/div/button")
    lateinit var searchButton: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/header/div[3]/div/div/div/div/input")
    lateinit var searchField: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/main/div/div[2]/div[2]/div/h1/a/span")
    lateinit var itemName: WebElement
    @FindBy(xpath = "/html/body/div[6]/div/div/div[1]")
    lateinit var popupClose: WebElement
}