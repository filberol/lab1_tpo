package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class ItemCheckoutPage(
    webDriver: WebDriver
): AbstractPage(webDriver) {
    @FindBy(xpath = "/html/body/div[1]/div/main/div/div[2]/div[2]/div/div[4]/div/div/div/div/div[1]/button")
    lateinit var checkoutButton: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/main/div/div[2]/div[2]/div/div[2]/div/div[1]/div/div[2]/div[2]/div/div[1]")
    lateinit var sizeTakeButton: WebElement
    @FindBy(xpath = "/html/body/div[6]/div/div/div[4]/a")
    lateinit var toCheckoutPage: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/main/div/div[1]/div[1]/form/div/div/div[4]/div/div[2]/div/div/div/div/div[2]/div[2]/div[1]/div[1]/div[1]/span[1]/span")
    lateinit var itemName: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/main/div/div[1]/div[1]/form/div/div/div[4]/div/div[2]/div/div/div/div/div[2]/div[2]/div[1]/div[1]/div[1]/span[2]")
    lateinit var itemSize: WebElement
}