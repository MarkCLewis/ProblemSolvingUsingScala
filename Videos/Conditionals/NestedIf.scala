import io.StdIn._

val order = readLine
val size = readLine

val price = if(order == "food") {
	if(size.toLowerCase.startsWith("s")) {
		2.0
	} else if(size.toLowerCase.startsWith("m")) {
		2.5
	} else if(size.toLowerCase.startsWith("l")) {
		3.0
	}
} else if(order == "drink") {
	if(size.toLowerCase.startsWith("s")) {
		1.0
	} else if(size.toLowerCase.startsWith("m")) {
		1.5
	} else if(size.toLowerCase.startsWith("l")) {
		2.0
	}
} else if(order == "combo") {
	if(size.toLowerCase.startsWith("s")) {
		3.0
	} else if(size.toLowerCase.startsWith("m")) {
		3.5
	} else if(size.toLowerCase.startsWith("l")) {
		4.0
	}
} else {
	0.0
}

println(price)
