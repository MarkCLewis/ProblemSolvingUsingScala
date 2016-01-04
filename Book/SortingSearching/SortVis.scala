import scalafx.Includes._
import scalafx.application.{JFXApp, Platform}
import scalafx.scene.Scene
import scalafx.scene.canvas._
import scalafx.scene.control.{Button, Slider}
import scalafx.scene.layout.FlowPane
import scalafx.scene.paint.Color
import scalafx.event.ActionEvent
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val numToSort = if (args.length>0) args(0).toInt else 300
val drawHeight = 300

def renderValues(gc:GraphicsContext, a:Array[Double], i:Int, min:Int):Unit = {
  gc.clearRect(0, 0, a.length, drawHeight)
  gc.stroke = Color.Black
  for (j <- a.indices) {
    gc.strokeLine(j,drawHeight*(1.0-a(j)),j,drawHeight)
  }
  gc.stroke = Color.Green
  gc.strokeLine(i, 0, i, 10)
  gc.stroke = Color.Blue
  gc.strokeLine(min, 0, min, 10)
}

def bubbleSortVis(gc:GraphicsContext, a:Array[Double], delay:Int) = {
  for (j <- 0 until a.length-1) {
    for (i <- 0 until a.length-1-j) {
      if (a(i) > a(i+1)) {
        val tmp = a(i)
        a(i) = a(i+1)
        a(i+1) = tmp
      }
      Platform.runLater(renderValues(gc, a, i, -1))
      Thread.sleep(delay)
    }
  }
}

def minSortVis(gc:GraphicsContext, a:Array[Double], delay:Int):Unit = {
  for (j <- 0 until a.length-1) {
    var min = j
    for (i <- j+1 until a.length) {
      if (a(i) < a(min)) min = i
      Platform.runLater(renderValues(gc, a, i, min))
      Thread.sleep(delay)
    }
    if (min != j) {
      val tmp = a(j)
      a(j) = a(min)
      a(min) = tmp
    }
  }
}

def insertionSortVis(gc:GraphicsContext, a:Array[Double], delay:Int):Unit = {
  for (j <- 1 until a.length) {
    var i = j-1
    val tmp = a(j)
    while (i >= 0 && a(i) > tmp) {
      a(i+1) = a(i)
      i -= 1
      Platform.runLater(renderValues(gc, a, i, -1))
      Thread.sleep(delay)
    }
    a(i+1) = tmp
  }
}

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Sorts"
    scene = new Scene(numToSort, drawHeight+50) {
      val canvas = new Canvas(numToSort, drawHeight)
      val gc = canvas.graphicsContext2D

      val slider = new Slider(1, 20, 1)
      slider.layoutY = drawHeight+30
      slider.prefWidth = numToSort

      val bubble = new Button("Bubble Sort")
      bubble.onAction = (e:ActionEvent) => Future {
        bubbleSortVis(gc, Array.fill(numToSort)(math.random), slider.value.toInt)
      }
      val minSort = new Button("Min Sort")
      minSort.onAction = (e:ActionEvent) => Future {
        minSortVis(gc, Array.fill(numToSort)(math.random), slider.value.toInt)
      }
      val insertion = new Button("Insertion Sort")
      insertion.onAction = (e:ActionEvent) => Future {
        insertionSortVis(gc, Array.fill(numToSort)(math.random), slider.value.toInt)
      }

      val flow = new FlowPane
      flow.children = List(bubble, minSort, insertion)
      flow.layoutY = drawHeight

      content = List(canvas, flow, slider)
    }
  }
}

app.main(args)
