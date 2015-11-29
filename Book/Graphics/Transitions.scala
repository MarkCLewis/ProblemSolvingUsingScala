import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.event.ActionEvent
import scalafx.animation._
import scalafx.scene.control.Button
import scalafx.scene.layout.FlowPane
import scalafx.scene.paint.Color
import scalafx.scene.shape._
import scalafx.util.Duration

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Transitions"
    scene = new Scene(600, 400) {
      val rect1 = Rectangle(20, 50, 100, 100)
      rect1.strokeWidth = 10
      val fadeTrans = new FadeTransition(new Duration(1000))
      fadeTrans.fromValue = 1.0
      fadeTrans.toValue = 0.5
      fadeTrans.autoReverse = true
      val path = new CubicCurve
      path.startX = 70
      path.startY = 100
      path.controlX1 = 600
      path.controlY1 = 400
      path.controlX2 = 0
      path.controlY2 = 400
      path.endX = 530
      path.endY = 100
      val pathTrans = new PathTransition(new Duration(1000), path)
      val strokeTrans = new StrokeTransition(new Duration(1000), Color.Black, Color.Cyan)
      val seqTrans = new SequentialTransition(rect1, List(fadeTrans, pathTrans, strokeTrans))
      seqTrans.autoReverse = true
      seqTrans.cycleCount = 4
      val startButton = new Button("Start")
      startButton.onAction = (e:ActionEvent) => seqTrans.play
      val pauseButton = new Button("Pause")
      pauseButton.onAction = (e:ActionEvent) => seqTrans.pause
      val stopButton = new Button("Stop")
      stopButton.onAction = (e:ActionEvent) => seqTrans.stop
      val flowPane = new FlowPane
      flowPane.children = List(startButton, pauseButton, stopButton)
      val rect2 = Rectangle(20, 280, 100, 100)
      val transTrans = new TranslateTransition(new Duration(2000))
      transTrans.fromX = 0
      transTrans.toX = 460
      val fillTrans = new FillTransition(new Duration(2000), Color.Black, Color.Green)
      val rotTrans = new RotateTransition(new Duration(2000))
      rotTrans.fromAngle = 0
      rotTrans.toAngle = 360
      val parallel = new ParallelTransition(rect2, List(transTrans, fillTrans, rotTrans))
      parallel.autoReverse = true
      parallel.cycleCount = 1000000

      // Uncomment to see non-eased motion
      // transTrans.interpolator = Interpolator.LINEAR
      // rotTrans.interpolator = Interpolator.LINEAR
      
      content = List(rect1, rect2, flowPane)

      parallel.play
    }
  }
}

app.main(args)
