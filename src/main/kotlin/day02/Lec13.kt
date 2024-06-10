package day02

class Lec13 {

}

fun main() {

    // 람다
    compute(5, 3) { a, b -> a + b }

    // 익명함수
    compute(5, 3, fun(a, b) = a + b)

    // fun(a, b) = a + b : 함숫값 / 함수 리터럴 이라고 한다.
    // 일반 함수와 달리 변수로 간주하거나 파라미터에 넣을 수 있는 함수

    iterate(listOf(1, 2, 3, 4, 5), fun(num) {
        if (num == 3) {
            return
        }
        println(num)
    })

    iterate(listOf(1, 2, 3, 4, 5)) {
        if (it != 3) {
            println(it)
        }
    }
}

fun compute(num1: Int, num2: Int, op: (Int, Int) -> Int): Int {
    return op(num1, num2)
}

fun iterate(numbers: List<Int>, exec: (Int) -> Unit) {
    for (number in numbers) {
        exec(number)
    }
}

fun calculate(num1: Int, num2: Int, oper: Char): Int {
    return when (oper) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> {
            if (num2 == 0) {
                throw IllegalArgumentException("0으로 나눌 수 없습니다.")
            } else {
                num1 / num2
            }
        }

        else -> throw IllegalArgumentException("들어올 수 없는 연산자($oper)입니다!")
    }
}

fun calculate2(num1: Int, num2: Int, oper: Operator) = oper.calcFun(num1, num2)

enum class Operator(
    private val oper: Char,
    val calcFun: (Int, Int) -> Int,
) {
    PLUS('+', { a, b -> a + b }),
    MINUS('-', { a, b -> a - b }),
    MULTIPLY('*', { a, b -> a * b }),
    DIVIDE('/', { a, b ->
        if (b == 0) {
            throw IllegalArgumentException("0으로 나눌 수 없습니다.")
        } else {
            a / b
        }
    }),
}
