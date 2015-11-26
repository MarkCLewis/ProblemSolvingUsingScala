import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Label, Slider}
import scalafx.scene.paint.Color
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Conditional Bindings"
		scene = new Scene(600, 300) {
			val label = new Label("Hover to change background.")
			val slider = new Slider(0, 10, 0)

			content = List(label, slider)

			label.layoutY <== (scene.height-label.height)/2
			label.layoutX <== when (slider.value<5) choose 0 otherwise scene.width-label.width
			fill <== when (label.hover) choose Color.Green otherwise Color.White
		}
	}
}

app.main(args)
