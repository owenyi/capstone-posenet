import kotlin.math.abs
import kotlin.math.round

fun main() {
    println(round(abs(-1.123)))
    println(round(abs(-1.123) * 10) / 10) // 소수점 첫 번째 자리까지 반올림
}