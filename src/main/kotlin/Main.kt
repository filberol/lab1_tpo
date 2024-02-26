import domain.Civilization
import domain.Ford
import domain.Thesis
import domain.Thing

/*
Ерунда, подумал Форд. Даже если предположить, что здесь когда-то существовала цивилизация, превратившаяся теперь в пыль,
даже если предположить еще много маловероятных вещей, все равно огромные богатства не могли бы сохраниться здесь в форме,
представляющей какой-либо интерес. Он пожал плечами.
 */
fun main() {
    val civ = Civilization("Civ1").also {
        it.isDead = true
        it.isDead = true
    }
    val things = Thing().also {
        it.isUnlikely = true
    }
    val fordThoughts = listOf(
        Ford.FordThought(things, "isUnlikely", true),
        Ford.FordThought(civ, "turnedDust", true),
        Ford.FordThought(civ, "everExisted", true),
        Ford.FordThought(Thesis("Nonsense"))
    )
    val ford = Ford().also {
        it.thoughts.addAll(fordThoughts)
    }
    StorySolver(ford).also {
        it.proceedStory()
    }
}