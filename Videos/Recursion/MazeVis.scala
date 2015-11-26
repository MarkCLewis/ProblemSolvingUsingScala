import scalafx.Includes._
import scalafx.application._
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.canvas._
import scalafx.scene.input._
import scalafx.scene.paint._
import scalafx.event.ActionEvent
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val size = 600

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

def renderMaze(m:Array[Array[Int]], x:Int, y:Int, gc:GraphicsContext):Unit = {
	for(r <- 0 until m.length; c <- 0 until m(r).length) {
		m(r)(c) match {
			case 0 => gc.fill = Color.White
			case -1 => gc.fill = Color.Black
			case -2 => gc.fill = Color.Blue
		}
		gc.fillRect(c*size/m(r).length, r*size/m.length, size/m(r).length, size/m.length)
	}
	gc.fill = Color.Red
	gc.fillOval(y*size/m(x).length, x*size/m.length, size/m(x).length, size/m.length)
}

def shortestPath(m:Array[Array[Int]], x:Int, y:Int, ex:Int, ey:Int, gc:GraphicsContext):Int = {
	if(x==ex && y==ey) 0
	else if(x<0 || x>=maze.length || y<0 || y>=maze(x).length || maze(x)(y)<0) {
		1000000000
	} else {
		maze(x)(y) = -2
		Platform.runLater(renderMaze(m, x, y, gc))
		Thread.sleep(200)
		val answer = (shortestPath(m, x+1, y, ex, ey, gc) min
									shortestPath(m, x-1, y, ex, ey, gc) min
									shortestPath(m, x, y+1, ex, ey, gc) min
									shortestPath(m, x, y-1, ex, ey, gc))+1
		Platform.runLater(renderMaze(m, x, y, gc))
		Thread.sleep(200)
		maze(x)(y) = 0
		answer
	}
}


val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Canvas"
		scene = new Scene(size, size) {
			val canvas = new Canvas(size, size)

			val gc = canvas.graphicsContext2D

			content = canvas

			Future { 
				println(shortestPath(maze, 0, 0, 9, 9, gc))
			}
		}
	}
}

app.main(args)
