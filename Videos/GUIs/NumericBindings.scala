import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Label, Button, Slider, ScrollBar, TextField}
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = ""
		scene = new Scene(600, 300) {
			val label = new Label("Centered")
			label.layoutY = 50
			val button = new Button("Click Me!")
			button.layoutY = 100
			val slider = new Slider(0, 500, 0)
			val scrollBar = new ScrollBar
			scrollBar.min = 0
			scrollBar.max = 500
			val field = new TextField
			field.text = label.text.value

			content = List(label, button, slider, scrollBar, field)

			button.layoutX <== slider.value
			scrollBar.value <==> slider.value

			label.layoutX <== (scene.width-label.width)/2
			label.text <== field.text

			scrollBar.layoutY <== scene.height-scrollBar.height
			field.layoutX <== scene.width-field.width
		}
	}
}

app.main(args)
