import scalafx.Includes._
import scalafx.application._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.paint._
import scalafx.scene.shape._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Towers of Hanoi"
    scene = new Scene(600,300) {
      def makeDisk(peg:Int, h:Int, size:Int):Rectangle = {
        val s = size*10
        val r = Rectangle(100+peg*200-s/2, height.value-h*30, s, 20)
        r.fill = Color.Red
        r.stroke = Color.Black
        content += r
        r
      }

      val num = if (args.length>0) args(0).toInt else 7
      val pegs = Array(List.tabulate(num)(i => makeDisk(0, num-i, i+2)), List[Rectangle](), List[Rectangle]())

      def moveDisk(from:Int,to:Int) {
        require(pegs(to).isEmpty || pegs(from).head.width.value < pegs(to).head.width.value)
        pegs(to) = pegs(from).head :: pegs(to)
        pegs(from) = pegs(from).tail
        Platform.runLater {
          val p = pegs(to).head
          p.x = 100+to*200-p.width.value/2
          p.y = height.value-pegs(to).length*30
        }
        Thread.sleep(100)
      }

      def moveNDisks(n:Int,from:Int,to:Int) {
        val other = 3-from-to
        if (n==1) {
          moveDisk(from, to)
        } else {
          moveNDisks(n-1, from, other)
          moveDisk(from, to)
          moveNDisks(n-1, other, to)
        }
      }

      val button = new Button("Start")
      content += button
      button.onAction = (e:ActionEvent) => Future {
        moveNDisks(pegs(0).size, 0, 2)
      }
    }
  }
}

app.main(args)
