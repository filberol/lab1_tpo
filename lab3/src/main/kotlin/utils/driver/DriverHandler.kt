package utils.driver

import utils.ConfProperties
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver

object DriverHandler {
    val webDrivers = ArrayList<WebDriver>()

    fun initDrivers() {
        val edgeDriver = ConfProperties.getProperty(DriverConst.WEBDRIVER_EDGE_DRIVER)
        if (edgeDriver == DriverConst.EDGE_DRIVER) {
            System.setProperty(DriverConst.EDGE_DRIVER, edgeDriver)
            webDrivers.add(EdgeDriver())
        }
    }

    fun closeDrivers() {
        webDrivers.forEach {
            it.close()
        }
        webDrivers.clear()
    }

    fun areDriversEmpty() = webDrivers.isEmpty()
}
