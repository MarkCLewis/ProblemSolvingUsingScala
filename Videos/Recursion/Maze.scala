

val maze = Array(Array( 0,-1, 0, 0, 0, 0, 0, 0, 0, 0),
                 Array( 0,-1, 0,-1,-1,-1,-1,-1,-1, 0),
                 Array( 0,-1, 0,-1, 0, 0,-1, 0, 0, 0),
                 Array( 0,-1, 0,-1,-1, 0,-1, 0,-1, 0),
                 Array( 0, 0, 0,-1, 0, 0, 0, 0,-1, 0),
                 Array( 0,-1, 0,-1, 0,-1,-1,-1,-1,-1),
                 Array( 0,-1, 0,-1, 0, 0, 0, 0, 0, 0),
                 Array( 0,-1, 0,-1, 0,-1, 0,-1, 0, 0),
                 Array( 0,-1, 0,-1, 0,-1, 0,-1,-1,-1),
                 Array( 0,-1, 0, 0, 0,-1, 0, 0, 0, 0))

def shortestPath(m:Array[Array[Int]], x:Int, y:Int, ex:Int, ey:Int):Int = {
	if(x==ex && y==ey) 0
	else if(x<0 || x>=maze.length || y<0 || y>=maze(x).length || maze(x)(y)<0) {
		1000000000
	} else {
		maze(x)(y) = -2
		val answer = (shortestPath(m, x+1, y, ex, ey) min
									shortestPath(m, x-1, y, ex, ey) min
									shortestPath(m, x, y+1, ex, ey) min
									shortestPath(m, x, y-1, ex, ey))+1
		maze(x)(y) = 0
		answer
	}
}

println(shortestPath(maze, 0, 0, 9, 9))
