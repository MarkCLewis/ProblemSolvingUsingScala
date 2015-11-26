import scalafx.Includes._
import scalafx.application._
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.canvas._
import scalafx.scene.layout._
import scalafx.scene.paint._
import scalafx.event.ActionEvent
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val numNumbers = 600
val drawHeight = 600
val delay = 5

def renderValues(a:Array[Double], j:Int, gc:GraphicsContext):Unit = {
	gc.clearRect(0, 0, numNumbers, drawHeight)
	gc.stroke = Color.Black
	for(i <- a.indices) {
		gc.strokeLine(i, drawHeight, i, (1-a(i))*drawHeight)
	}
	gc.stroke = Color.Red
	gc.strokeLine(j, 0, j, 100)
}

def bubbleSort(a:Array[Double], gc:GraphicsContext):Unit = {
	for(i <- 0 until a.length-1) {
		for(j <- 0 until a.length-1-i) {
			if(a(j) > a(j+1)) {
				val tmp = a(j)
				a(j) = a(j+1)
				a(j+1) = tmp
			}
			Platform.runLater(renderValues(a, j, gc))
			Thread.sleep(delay)
		}
	}
}

def minSort(a:Array[Double], gc:GraphicsContext):Unit = {
	for(i <- 0 until a.length-1) {
		var min = i
		for(j <- i+1 until a.length) {
			if(a(j) < a(min)) min = j
			Platform.runLater(renderValues(a, j, gc))
			Thread.sleep(delay)
		}
		val tmp = a(i)
		a(i) = a(min)
		a(min) = tmp
	}
}

def insertionSort(a:Array[Double], gc:GraphicsContext):Unit = {
	for(i <- 1 until a.length) {
		var j = i-1
		var tmp = a(i)
		while(j>=0 && tmp < a(j)) {
			a(j+1) = a(j)
			j -= 1
			Platform.runLater(renderValues(a, j, gc))
			Thread.sleep(delay)
		}
		a(j+1) = tmp
	}
}

def shellSort(a:Array[Double], gc:GraphicsContext):Unit = {
  var gap = a.length/2
  while(gap >= 1) {
    for(i <- gap until a.length) {
      var j = i-gap
      var tmp = a(i)
      while(j>=0 && tmp < a(j)) {
        a(j+gap) = a(j)
        j -= gap
				Platform.runLater(renderValues(a, j, gc))
				Thread.sleep(delay)
      }
      a(j+gap) = tmp
    }
    gap = (gap/2.2).round.toInt
  }
}

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Sorts"
		scene = new Scene(numNumbers, drawHeight) {
			val canvas = new Canvas(numNumbers, drawHeight)
			val gc = canvas.graphicsContext2D

			val bubbleButton = new Button("Bubble")
			val minButton = new Button("Selection")
			val insertButton = new Button("Insertion")
			val shellButton = new Button("Shell")

			content = List(canvas, new HBox(5, bubbleButton, minButton, insertButton, shellButton))

			bubbleButton.onAction = (ae:ActionEvent) => {
				Future { bubbleSort(Array.fill(numNumbers)(math.random), gc) }
			}
			minButton.onAction = (ae:ActionEvent) => {
				Future { minSort(Array.fill(numNumbers)(math.random), gc) }
			}
			insertButton.onAction = (ae:ActionEvent) => {
				Future { insertionSort(Array.fill(numNumbers)(math.random), gc) }
			}
			shellButton.onAction = (ae:ActionEvent) => {
				Future { shellSort(Array.fill(numNumbers)(math.random), gc) }
			}
		}
	}
}

app.main(args)
