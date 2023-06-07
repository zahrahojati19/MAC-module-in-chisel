// See LICENSE.txt for license details.
package solutions

import chisel3.iotesters.PeekPokeTester

class MACTest(c: MAC) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {
    val x = rnd.nextInt(1 << c.w)
    val y = rnd.nextInt(1 << c.w)
	val z = rnd.nextInt(1 << c.w)
    poke(c.io.x_i, x)
    poke(c.io.y_i, y)
	poke(c.io.z_i, z)
    step(1)
	expect(c.io.out_o, (x*y)+z)
  }
}
