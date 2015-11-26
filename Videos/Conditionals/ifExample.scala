import io.StdIn._

println("How old are you?")
val age = readInt
val response = if(age < 21) {
	"Get lost"
} else {
	println(age + " is old enough")
	"Come in"
}

println(response)

val s1 = "hi there"
val s2 = readLine+"there"

if(s1 == s2) println("equal") else println("not equal")

