import domain.Civilization
import domain.Ford
import domain.Thesis
import domain.Thing
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.Test
import kotlin.test.assertTrue

class StoryTest {
    private val civ = Civilization("Civ1").also {
        it.isDead = true
        it.isDead = true
    }
    private val things = Thing().also {
        it.isUnlikely = true
    }
    private val fordThoughts = listOf(
        Ford.FordThought(things, "isUnlikely", true),
        Ford.FordThought(civ, "turnedDust", true),
        Ford.FordThought(civ, "everExisted", true),
        Ford.FordThought(Thesis("Nonsense"))
    )
    private val ford = Ford().also {
        it.thoughts.addAll(fordThoughts)
    }

        @Test
        fun testStoryCorrect() {
            StorySolver(ford).also {
                it.proceedStory()
                assertTrue(it.wereTreasuresSaved())
            }
        }

        @Test
        fun testStoryIncorrectByThings() {
            things.isUnlikely = false
            StorySolver(ford).also {
                it.proceedStory()
                assertFalse(it.wereTreasuresSaved())
            }
        }

        @Test
        fun testStoryIncorrectByCiv() {
            civ.isDead = false
            StorySolver(ford).also {
                it.proceedStory()
                assertFalse(it.wereTreasuresSaved())
            }
            assertFalse(civ.isDead)
            assertTrue(things.isUnlikely)
        }

        @Test
        fun testUnmanagedThought() {
            fordThoughts[0].everThought = false
            assertFalse(fordThoughts[0].everThought)
        }
}