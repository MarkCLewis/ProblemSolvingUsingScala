// 3*x^2+2*x-5
// Array(3,2,-5)


def evalPoly(coeffs:Array[Double],x:Double):Double = {
	var sum = 0.0
	for(i <- coeffs.indices) {
		sum += coeffs(i)*math.pow(x, coeffs.length-1-i)
	}
	sum
}

def evalPolyRev(coeffs:Array[Double],x:Double):Double = {
	var sum = 0.0
	var power = 1.0
	for(c <- coeffs.reverse) {
		sum += c*power
		power *= x
	}
	sum
}

def evalPolyStep(coeffs:Array[Double],x:Double):Double = {
	var sum = 0.0
	var power = 1.0
	for(i <- coeffs.length-1 to 0 by -1) {
		sum += coeffs(i)*power
		power *= x
	}
	sum
}

def evalPolyYield(coeffs:Array[Double],x:Double):Double = {
	(for(i <- coeffs.indices) yield {
		coeffs(i)*math.pow(x, coeffs.length-1-i)
	}).sum
}

println(evalPoly(Array(3,2,-5),1))
println(evalPolyRev(Array(3,2,-5),1))
println(evalPolyStep(Array(3,2,-5),1))
println(evalPolyYield(Array(3,2,-5),1))
