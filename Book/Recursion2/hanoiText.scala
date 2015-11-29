val num = if(args.length>0) args(0).toInt else 7
val pegs = Array(List.tabulate(num)(i => i+2), List[Int](), List[Int]())

def moveDisk(from:Int,to:Int):Unit = {
    require(pegs(to).isEmpty || pegs(from).head < pegs(to).head)
    pegs(to) = pegs(from).head :: pegs(to)
    pegs(from) = pegs(from).tail
}

def moveNDisks(n:Int,from:Int,to:Int):Unit = {
    if(n==1) {
        moveDisk(from, to)
    } else {
        val other = 3-from-to
        moveNDisks(n-1, from, other)
        moveDisk(from, to)
        moveNDisks(n-1, other, to)
    }
}

moveNDisks(pegs(0).size, 0, 2)

println(pegs.map(a => a.mkString(" ")).mkString("\n"))

