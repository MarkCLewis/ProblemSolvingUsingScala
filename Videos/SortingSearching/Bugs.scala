import io.StdIn._

val a = Array.fill(10)(5)
val i = 1

def fact(n:Int):Int = if(n<2) 0 else n*fact(n-1)

println(fact(readInt))

a(i)
