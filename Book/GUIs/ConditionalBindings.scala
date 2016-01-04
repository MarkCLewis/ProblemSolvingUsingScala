import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.Label
import scalafx.scene.control.Slider
import scalafx.scene.paint.Color

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Conditional Bindings"
    scene = new Scene(500, 250) {
      val label = new Label("Hover to change background.")

      val slider = new Slider(0,100,0)
      slider.layoutX = 10
      slider.layoutY = 10
      slider.prefWidth = 180

      content = List(label, slider)

      label.layoutX <== when (slider.value < 50) choose 0 otherwise width-label.width
      label.layoutY <== (height-label.height)/2
      fill <== when (label.hover) choose Color.Red otherwise Color.White
    }
  }
}

app.main(args)
