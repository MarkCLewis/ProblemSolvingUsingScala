import scalafx.Includes._
import scalafx.animation._
import scalafx.application._
import scalafx.scene._
import scalafx.scene.canvas._
import scalafx.scene.input._
import scalafx.scene.paint._

object Game {
  val Width = 760
  val Height = 840

  private var _lives = 3
  private var _score = 0

  def lives = _lives
  def score = _score
  def newLife():Unit = {
    _lives -= 1
  }

  val maze = new Maze

  def main(args:Array[String]):Unit = {
    val app = new JFXApp {
      stage = new JFXApp.PrimaryStage {
        title = "PacMan"
        scene = new Scene(Width, Height) {
          val canvas = new Canvas(Width, Height)
          val gc = canvas.graphicsContext2D

          content = canvas

          onKeyPressed = (e:KeyEvent) => {
            e.code match {
              case KeyCode.Left => maze.player.goLeft
              case KeyCode.Right => maze.player.goRight
              case KeyCode.Up => maze.player.goUp
              case KeyCode.Down => maze.player.goDown
              case _ =>
            }
          }

          var lastTime = 0L
          val timer:AnimationTimer = AnimationTimer(t => {
            val delay = (t-lastTime)/1e9
            if (lastTime > 0 && delay > 0.1) {
              _score += maze.updateAll()
              Renderer.render(gc, maze)
              lastTime = t-((delay-0.1)*1000000000).toInt
            }
            if (lastTime == 0) lastTime = t
            if (lives < 0 || maze.pelletCount==0) timer.stop
          })
          timer.start
        }
      }
    }

    app.main(args)
  }
}
