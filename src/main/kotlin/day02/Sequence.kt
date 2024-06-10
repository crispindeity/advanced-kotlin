package day02

fun main() {
    val fruit = listOf(
        MyFruit("사과", 1000L),
        MyFruit("바나나", 3000L)
    )

    val avg = fruit.asSequence()
        .filter { it.name == "사과" }
        .map { it.price }
        .take(10_000)
        .average()
}

data class MyFruit(
    val name: String,
    val price: Long
)
