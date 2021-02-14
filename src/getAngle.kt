fun main() {
    println("직각이등변삼각형")
    val a = Position(0, 0)
    val b = Position(1, 0)
    val c = Position(1, 1)

    println(getAngle(a, b, c)) // 90             c
    println(getAngle(a, c, b)) // 45          .  .
    println(getAngle(b, a, c)) // 45        .  45.
    println(getAngle(b, c, a)) // 45      .      .
    println(getAngle(c, a, b)) // 45    .45    90.
    println(getAngle(c, b, a)) // 90  a . . . .  b

    println("한 직선 위의 점들")
    val d = Position(0, 0)
    val e = Position(1, 1)
    val f = Position(2, 2)

    println(getAngle(d, e, f)) // 180
    println(getAngle(d, f, e)) // 0            f
    println(getAngle(e, d, f)) // 0          .
    println(getAngle(e, f, d)) // 0        e
    println(getAngle(f, d, e)) // 0      .
    println(getAngle(f, e, d)) // 180  d
}

// 세 점 사이 각도 구하기
fun getAngle(a: Position, b: Position, c: Position): Double {
    val dx1: Double = (c.x - b.x).toDouble() // x 변화량, b -> c
    val dx2: Double = (a.x - b.x).toDouble() // x 변화량, b -> a
    val dy1: Double = (c.y - b.y).toDouble() // y 변화량, b -> c
    val dy2: Double = (a.y - b.y).toDouble() // y 변화량, b -> a

    val radians = Math.abs(Math.atan2(dy1, dx1) - Math.atan2(dy2, dx2))
    val angle : Double = Math.toDegrees(radians)

    return angle
}

class Position (var x: Int, var y: Int)