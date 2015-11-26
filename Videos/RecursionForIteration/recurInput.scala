import io.StdIn._

def sum(n:Int):Int = {
	if(n < 1) 0
	else readInt + sum(n-1)
}

def sumPositive():Int = {
	val input = readInt
	if(input >= 0) {
		input + sumPositive()
	} else 0
}

def sumUntilQuit():Int = {
	val input = readLine.toLowerCase.trim
	if(input == "quit") 0
	else input.toInt + sumUntilQuit()
}

def sumAndCount():(Int, Int) = {
	val input = readLine.toLowerCase.trim
	if(input == "quit") (0, 0)
	else {
		val (sum, count) = sumAndCount()
		(input.toInt+sum, count+1)
	}
}

def multAndCount():(Int, Int) = {
	val input = readLine.toLowerCase.trim
	if(input == "quit") (1, 0)
	else {
		val (prod, count) = multAndCount()
		(input.toInt*prod, count+1)
	}
}

def inputAndCount(base:Int, op: (Int,Int)=>Int):(Int, Int) = {
	val input = readLine.toLowerCase.trim
	if(input == "quit") (base, 0)
	else {
		val (value, count) = inputAndCount(base, op)
		(op(input.toInt, value), count+1)
	}
}

val (s,c) = inputAndCount(Int.MaxValue, _ min _)
println(s+"  "+c)
