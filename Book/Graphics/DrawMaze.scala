import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape._
import scalafx.scene.input.MouseEvent
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Draw Maze"
    scene = new Scene(500,500) {
      val polyline = Polyline(0, 0, 500, 0, 500, 500, 0, 500, 0, 0)
      var walls = List(polyline)

      val ball = Circle(21, 21, 20)

      content = List(polyline, ball)

      onKeyPressed = (e:KeyEvent) => {
				println("Pressed")
        val oldX = ball.centerX.apply
        val oldY = ball.centerY.apply
        if(e.code == KeyCode.UP) ball.centerY = ball.centerY.apply - 2
        if(e.code == KeyCode.DOWN) ball.centerY = ball.centerY.apply + 2
        if(e.code == KeyCode.LEFT) ball.centerX = ball.centerX.apply - 2
        if(e.code == KeyCode.RIGHT) ball.centerX = ball.centerX.apply + 2

        // Collision detection with walls
        val clear = walls.forall(shape => {
          Shape.intersect(ball,shape).boundsInLocal.apply.isEmpty
        })

        // If it collided, go back to old location
        if(!clear) {
          ball.centerX = oldX
          ball.centerY = oldY
        }
      }

      onMousePressed = (e:MouseEvent) => {
        walls ::= Polyline()
        content += walls.head
        walls.head.points ++= List(e.x,e.y)
      }
      onMouseDragged = (e:MouseEvent) => {
        walls.head.points ++= List(e.x,e.y)
      }
      onMouseReleased = (e:MouseEvent) => {
        walls.head.points ++= List(e.x,e.y)
      }
    }
  }
}

app.main(args)
