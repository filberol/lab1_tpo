package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class LoginPage(
    webDriver: WebDriver
): AbstractPage(webDriver) {
    @FindBy(xpath = "/html/body/div[1]/div/header/div[2]/div[1]/button")
    lateinit var loginOpenButton: WebElement
    @FindBy(xpath = "/html/body/div[6]/div/div/div[3]/div[2]/div/div[2]/div/div/div/div[1]/form/div[2]/div/div/div[1]/div[1]/div[2]/input")
    lateinit var loginField: WebElement
    @FindBy(xpath = "/html/body/div[6]/div/div/div[3]/div[2]/div/div[2]/div/div/div/div[1]/form/div[3]/div/div/div[1]/div[1]/div[2]/input")
    lateinit var passwordField: WebElement
    @FindBy(xpath = "/html/body/div[6]/div/div/div[3]/div[2]/div/div[2]/div/div/div/div[1]/form/div[5]/button")
    lateinit var loginButton: WebElement
    @FindBy(xpath = "/html/body/div[1]/div/header/div[2]/div[1]/div/div/span/a/span[2]")
    lateinit var profileLabel: WebElement
}