def fact(n:Int):Int = if(n<2) 1 else n*fact(n-1)

def sumSquares(n:Int):Int = if(n<2) 1 else n*n+sumSquares(n-1)

def countDown(n:Int):Unit = {
	if(n>0) {
		println(n)
		countDown(n-1)
	}
}

def fact2(n:Int):Int = n match {
	case 0 => 1
	case _ => n*fact2(n-1)
}

def sumSquares2(n:Int):Int = n match {
	case 1 => 1
	case _ => n*n+sumSquares2(n-1)
}

def countDown2(n:Int):Unit = n match {
	case 0 =>
	case _ =>
		println(n)
		countDown2(n-1)
}

println(sumSquares2(4))
countDown2(10)

io.StdIn.readInt match {
	case 1 => println("It's 1")
	case 2 => println("It's 2")
	case 3 => println("It's 3")
	case n => println("Something else. "+n)
}

/*
expr match {
	case pattern1 => ...
	case pattern2 => ...
	...
}

(a,b) match {
	case (0,0) =>
	case (0,1) =>
	case (1,0) =>
	case (1,n) if n%2==0 =>
	case (1,n) if n%2==1 =>
}
*/
