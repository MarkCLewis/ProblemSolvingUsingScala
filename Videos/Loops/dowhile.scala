// do {
//   body
// } while(cond)

import io.StdIn._

def printMenu:Unit = {
	println("""
1. Enter a test grade.
2. Enter an assignment grade.
3. Print average.
4. Quit
	""")
}

def calcAverage(tests:List[Int],assns:List[Int]):Double = {
	val testAvg = if(tests.length<1) 0 else tests.sum/tests.length
	val assnAvg = if(assns.length<1) 0 else assns.sum/assns.length
	0.5*testAvg+0.5*assnAvg
}

var testGrades = List[Int]()
var assnGrades = List[Int]()
var input = 0
do {
	printMenu
	input = readInt
	input match {
		case 1 =>
			println("Enter a grade.")
			testGrades ::= readInt
		case 2 =>
			println("Enter a grade.")
			assnGrades ::= readInt
		case 3 =>
			println(s"The average is ${calcAverage(testGrades, assnGrades)}.")
		case 4 =>
		case _ => println("What was that?")
	}
} while(input!=4)
