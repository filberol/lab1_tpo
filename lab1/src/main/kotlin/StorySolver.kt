import domain.Ford
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class StorySolver(
    private val ford: Ford
) {
    private val logger: Logger = LogManager.getLogger(StorySolver::class.java)

    private var treasuresSaved = false

    fun wereTreasuresSaved(): Boolean = treasuresSaved

    fun proceedStory() {
        while (!ford.thoughts.empty()) {
            ford.thinkAbout(ford.thoughts.pop())
        }
        if (ford.correctThoughts == ford.totalThoughts) {
            treasuresSaved = true
            logger.info("All the thoughts were correct.")
        } else {
            logger.warn("Not all of the thoughts were correct.")
        }
        if (treasuresSaved) {
            logger.info("Treasures could have been saved.")
            ford.shakeHands()
        } else {
            logger.error("No treasures?! :::///")
        }
    }
}