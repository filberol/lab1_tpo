package domain

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.Stack

/*
Ерунда, подумал Форд. Даже если предположить, что здесь когда-то существовала цивилизация, превратившаяся теперь в пыль,
даже если предположить еще много маловероятных вещей, все равно огромные богатства не могли бы сохраниться здесь в форме,
представляющей какой-либо интерес. Он пожал плечами.
 */
class Ford {
    private val logger: Logger = LogManager.getLogger(Ford::class.java)

    private val name: String = "Ford"

    private val guessThoughts = mapOf(
        "isDead" to "is dead",
        "everExisted" to "ever existed",
        "turnedDust" to "turned to dust",
        "isUnlikely" to "is unlikely"
    )

    var correctThoughts = 0
    var totalThoughts = 0
    val thoughts: Stack<FordThought> = Stack()

    fun thinkAbout(thought: FordThought): Boolean {
        if (thought.guessBoolName.isNullOrEmpty() || thought.guessValue == null) {
            thinkAbout(thought.thoughtSubject)
            return true
        }
        val thoughtCorrect: Boolean = thought.thoughtSubject.javaClass
            .getDeclaredField(thought.guessBoolName).apply { isAccessible = true }
            .get(thought.thoughtSubject).equals(thought.guessValue)
            .also {
                if (it) correctThoughts += 1
            }

        val thesis: String = guessThoughts.getOrDefault(
            thought.guessBoolName, "does not matter"
        )
        logger.info("$name thought that ${thought.thoughtSubject} $thesis.")
        if (!thought.everThought) {
            logger.info("This was the first time $name thought of it.")
        }
        logger.info("This was ${if (thoughtCorrect) "a correct" else "an incorrect"} statement.")
        totalThoughts += 1
        return thoughtCorrect
    }

    private fun thinkAbout(subject: Any) {
        logger.info("$name thought - $subject.")
    }

    fun shakeHands() {
        logger.info("$name shook his hands.")
    }

    class FordThought(
        val thoughtSubject: Any,
        val guessBoolName: String?,
        val guessValue: Any?
    ) {
        constructor(thoughtSubject: Any): this(thoughtSubject, null, null)

        private val logger: Logger = LogManager.getLogger(FordThought::class.java)

        var everThought: Boolean = false
            set(value) {
                logger.warn("Unmanaged change of thoughts!")
                field = value
            }
            get() = field.also { field = true }
    }
}