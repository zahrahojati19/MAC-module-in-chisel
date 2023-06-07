package solutions

import chisel3._
import scala.collection.mutable.ArrayBuffer

class Adder2(val w: Int) extends Module {///
  val io = IO(new Bundle {
    val in0 = Input(UInt(w.W))
    val in1 = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })
  io.out := io.in0 + io.in1
}

class Mult(val w: Int) extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(w.W))
    val y   = Input(UInt(w.W))
    val z   = Output(UInt((2*w).W))
  })
  
  io.z := io.x * io.y

}
class MAC(val w: Int) extends Module {  ///(val w: Int)
	val io = IO(new Bundle {
		val x_i = Input(UInt(w.W))
		val y_i = Input(UInt(w.W))
		val z_i = Input(UInt((2*w).W))
		val out_o = Output(UInt((2*w).W))
	})
	val wire_s = Module(new Mult(w))
	wire_s.io.x := io.x_i
	wire_s.io.y := io.y_i
	
	val wire2_s = Module(new Adder2(2*w))
	wire2_s.io.in0 := wire_s.io.z
	wire2_s.io.in1 := io.z_i
	
	io.out_o := wire2_s.io.out
}