import io.StdIn._

val howMany = 5
var n0 = 0
var n1 = 0
var n2 = 0
var n3 = 0
var n4 = 0

def store(i:Int,num:Int):Unit = i match {
  case 0 => n0 = num
  case 1 => n1 = num
  case 2 => n2 = num
  case 3 => n3 = num
  case 4 => n4 = num
}

def pull(i:Int):Int = i match {
  case 0 => n0
  case 1 => n1
  case 2 => n2
  case 3 => n3
  case 4 => n4
}

def inputValues(i:Int):Unit = {
  if(i<howMany) {
    store(i, readInt)
    inputValues(i+1)
  }
}

def sumValues(i:Int):Int = {
  if(i<howMany) pull(i)+sumValues(i+1) else 0
}

println(s"Enter $howMany values.")
inputValues(0)
println(s"The sum is ${sumValues(0)}.")
