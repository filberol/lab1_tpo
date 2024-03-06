package domain

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/*
Ерунда, подумал Форд. Даже если предположить, что здесь когда-то существовала цивилизация, превратившаяся теперь в пыль,
даже если предположить еще много маловероятных вещей, все равно огромные богатства не могли бы сохраниться здесь в форме,
представляющей какой-либо интерес. Он пожал плечами.
 */
class Civilization(
    private var name: String
) {
    private val logger: Logger = LogManager.getLogger(Civilization::class.java)

    private var everExisted: Boolean = true
    private var turnedDust: Boolean = false

    var isDead: Boolean = false
        set(value) {
            if (field) {
                turnedDust = true
                logger.info("Civilization '$name' has turned to ashes ...")
            }
            field = value
            if (value) {
                logger.info("Civilization '$name' is now dead :/")
            } else {
                everExisted = true
                turnedDust = false
                logger.info("Civilization '$name' has returned to life ^/")
            }
        }

    override fun toString(): String {
        return name
    }
}