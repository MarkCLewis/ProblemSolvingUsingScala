import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.shape._
import scalafx.scene.control.Label
import scalafx.scene.paint.Color
import scalafx.scene.input._
import scalafx.animation.AnimationTimer
import scalafx.beans.property._

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Pong"
    scene = new Scene(500, 500) {
      val paddle1 = Rectangle(0, 200, 20, 100)
      val paddle2 = Rectangle(480, 200, 20, 100)
      val ball = Circle(250, 250, 20)
      var speed = 100.0
      var theta = 0.0
      val score1 = IntegerProperty(0)
      val score2 = IntegerProperty(0)
      val scoreDisplay = Label("")
      scoreDisplay.layoutY = 30
      scoreDisplay.layoutX <== (width-scoreDisplay.width)/2

      scoreDisplay.text <== StringProperty("")+score1.asString+" : "+score2.asString

      content = List(paddle1, paddle2, ball, scoreDisplay)

      onKeyPressed = (e:KeyEvent) => {
        if(e.code == KeyCode.W) {
          paddle1.y = paddle1.y.apply - 5
        }
        if(e.code == KeyCode.S) {
          paddle1.y = paddle1.y.apply + 5
        }
        if(e.code == KeyCode.UP) {
          paddle2.y = paddle2.y.apply - 5
        }
        if(e.code == KeyCode.DOWN) {
          paddle2.y = paddle2.y.apply + 5
        }
      }

      var lastTime = 0L
      val timer = AnimationTimer(t => {
        if(lastTime!=0) {
          val vx = speed*math.cos(theta)*(t-lastTime)/1e9
          val vy = speed*math.sin(theta)*(t-lastTime)/1e9
          ball.centerX = ball.centerX.apply + vx
          ball.centerY = ball.centerY.apply + vy
          speed += (t-lastTime)/1e8
        }
        lastTime = t
        if(!Shape.intersect(ball,paddle1).boundsInLocal.apply.isEmpty) {
          val offset = (paddle1.y.apply+paddle1.height.apply/2-ball.centerY.apply)
          theta = -offset/100*math.Pi/2
        }
        if(!Shape.intersect(ball,paddle2).boundsInLocal.apply.isEmpty) {
          val offset = (paddle2.y.apply+paddle2.height.apply/2-ball.centerY.apply)
          theta = math.Pi + offset/100*math.Pi/2
        }
        if(ball.centerY.apply < ball.radius.apply && math.sin(theta) < 0.0) {
          theta = math.atan2(-math.sin(theta),math.cos(theta))
        }
        if(ball.centerY.apply > height.apply-ball.radius.apply && math.sin(theta) > 0.0) {
          theta = math.atan2(-math.sin(theta),math.cos(theta))
        }
        if(ball.centerX.apply < -ball.radius.apply) {
          speed = 100
          score2.value = score2.apply+1
          ball.centerX = 250
          ball.centerY = 250
          theta = 0
        }
        if(ball.centerX.apply > 500+ball.radius.apply) {
          speed = 100
          score1.value = score1.apply+1
          ball.centerX = 250
          ball.centerY = 250
          theta = math.Pi
        }
      })

      timer.start
    }
  }
}

app.main(args)
