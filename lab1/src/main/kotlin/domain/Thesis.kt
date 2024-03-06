package domain

data class Thesis(
    private val content: String
) {
    override fun toString(): String {
        return content
    }
}