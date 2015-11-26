

val pegs = Array((1 to args(0).toInt).toList, Nil, Nil)

def moveOneDisk(from:Int, to:Int):Unit = {
	require(pegs(to).isEmpty || pegs(from).head < pegs(to).head)
	pegs(to) ::= pegs(from).head
	pegs(from) = pegs(from).tail
}

def moveDisks(from:Int, to:Int, n:Int):Unit = {
	if(n==1) moveOneDisk(from, to)
	else {
		val other = 3-from-to
		moveDisks(from, other, n-1)
		moveOneDisk(from, to)
		moveDisks(other, to, n-1)
	}
}

for(p <- pegs) println(p)
moveDisks(0, 2, pegs(0).length)
println()
for(p <- pegs) println(p)
