import io.StdIn._

def calcConcessionCost(order:String, size:String):Double = {
	if(order == "food") {
		if(size.toLowerCase.startsWith("s")) {
			2.0
		} else if(size.toLowerCase.startsWith("m")) {
			2.5
		} else if(size.toLowerCase.startsWith("l")) {
			3.0
		} else 0.0
	} else if(order == "drink") {
		if(size.toLowerCase.startsWith("s")) {
			1.0
		} else if(size.toLowerCase.startsWith("m")) {
			1.5
		} else if(size.toLowerCase.startsWith("l")) {
			2.0
		} else 0.0
	} else if(order == "combo") {
		if(size.toLowerCase.startsWith("s")) {
			2.5
		} else if(size.toLowerCase.startsWith("m")) {
			3.0
		} else if(size.toLowerCase.startsWith("l")) {
			3.5
		} else 0.0
	} else {
		0.0
	}
}

val o = readLine
val s = readLine
val price = calcConcessionCost(o, s)
println(price)
