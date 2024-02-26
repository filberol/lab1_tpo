package domain

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class Thing {
    private val logger: Logger = LogManager.getLogger(Thing::class.java)

    private val name: String = "Things"

    var isUnlikely: Boolean = false
        set(value) {
            field = value
            if (value) {
                logger.info("$name are surely unlikely.")
            } else {
                logger.info("$name are really likely.")
            }
        }

    override fun toString(): String {
        return name
    }
}