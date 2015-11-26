// while(cond) {
//  body
// }

import io.StdIn._

var i = 0
while(i < 10) {
	println(i)
	i += 1
}

def readInts:List[Int] = {
	var input = readLine
	var lst = List[Int]()
	while(input != "quit") {
		lst ::= input.toInt
		input = readLine
	}
	lst.reverse
}

println(readInts)
