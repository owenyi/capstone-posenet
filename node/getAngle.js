const getAngle = (a, b, c) => {
    const dx1 = c.x - b.x;
    const dx2 = a.x - b.x;
    const dy1 = c.y - b.y;
    const dy2 = a.y - b.y;

    const radians = Math.abs(Math.atan2(dy1, dx1) - Math.atan2(dy2, dx2));
    const angle = radians * 180 / Math.PI;

    return angle;
}

const a = { x: 0, y: 0 };
const b = { x: 1, y: 0 };
const c = { x: 1, y: 1 };

console.log(getAngle(a, b, c)) // 90             c
console.log(getAngle(a, c, b)) // 45          .  .
console.log(getAngle(b, a, c)) // 45        .  45.
console.log(getAngle(b, c, a)) // 45      .      .
console.log(getAngle(c, a, b)) // 45    .45    90.
console.log(getAngle(c, b, a)) // 90  a . . . .  b

const d = { x: 0, y: 0 };
const e = { x: 1, y: 1 };
const f = { x: 2, y: 2 };

console.log(getAngle(d, e, f)) // 180
console.log(getAngle(d, f, e)) // 0            f
console.log(getAngle(e, d, f)) // 0          .
console.log(getAngle(e, f, d)) // 0        e
console.log(getAngle(f, d, e)) // 0      .
console.log(getAngle(f, e, d)) // 180  d