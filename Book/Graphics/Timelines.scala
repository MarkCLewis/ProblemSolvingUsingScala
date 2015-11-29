import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.event.ActionEvent
import scalafx.animation._
import scalafx.scene.control.Button
import scalafx.scene.layout.FlowPane
import scalafx.scene.shape._
import scalafx.util.Duration

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Transitions"
    scene = new Scene(600, 400) {
      val rect1 = Rectangle(20, 50, 100, 100)

      val kv11 = KeyValue(rect1.translateX, 0)
      val kv12 = KeyValue(rect1.translateY, 0)
      val kv13 = KeyValue(rect1.rotate, 0)
      val kf1 = KeyFrame(new Duration(0), values = Set(kv11, kv12, kv13))
      val kv21 = KeyValue(rect1.translateY, 0)
      val kv22 = KeyValue(rect1.rotate, 360)
      val kf2 = KeyFrame(new Duration(1000), values = Set(kv21, kv22))
      val kv31 = KeyValue(rect1.translateX, 460)
      val kv32 = KeyValue(rect1.translateY, 230)
      val kf3 = KeyFrame(new Duration(2000), values = Set(kv31, kv32))

      val timeline = Timeline(List(kf1, kf2, kf3))

      val startButton = new Button("Start")
      startButton.onAction = (e:ActionEvent) => timeline.play
      val pauseButton = new Button("Pause")
      pauseButton.onAction = (e:ActionEvent) => timeline.pause
      val stopButton = new Button("Stop")
      stopButton.onAction = (e:ActionEvent) => timeline.stop
      val flowPane = new FlowPane
      flowPane.children = List(startButton, pauseButton, stopButton)

      
      content = List(rect1, flowPane)
    }
  }
}

app.main(args)
