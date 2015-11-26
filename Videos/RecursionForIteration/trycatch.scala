import io.StdIn._

def safeReadInt:Int = {
	try {
		readInt
	} catch {
		case e:NumberFormatException =>
			println("That wasn't a valid Int. Please try again.")
			safeReadInt
	}
}

val i = safeReadInt

println(i)
