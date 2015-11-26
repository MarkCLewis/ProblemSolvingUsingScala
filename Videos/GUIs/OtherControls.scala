import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Label, ProgressBar, ScrollBar, Separator,
		Slider, ToolBar, Button}
import scalafx.event.ActionEvent
import scalafx.geometry.Orientation

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = ""
		scene = new Scene(600, 300) {
			val toolbar = new ToolBar
			toolbar.prefWidth = 600

			val advButton = new Button("Advance")
			val decButton = new Button("Decrement")
			toolbar.items = List(advButton, decButton, new Separator, new Button("Tool 1"), new Button("Tool 2"))

			val progress = new ProgressBar
			progress.layoutX = 20
			progress.layoutY = 70
			progress.prefWidth = 260

			val scrollBar = new ScrollBar
			scrollBar.layoutX = 20
			scrollBar.layoutY = 130
			scrollBar.prefWidth = 260
			scrollBar.min = 0
			scrollBar.max = 100

			val scrollLabel = new Label("")
			scrollLabel.layoutX = 20
			scrollLabel.layoutY = 180

			val separator = new Separator
			separator.layoutX = 300
			separator.layoutY = 70
			separator.orientation = Orientation.VERTICAL
			separator.prefHeight = 230

			val slider = new Slider(0, 10, 0)
			slider.layoutX = 320
			slider.layoutY = 70
			slider.prefWidth = 260

			val sliderLabel = new Label("")
			sliderLabel.layoutX = 320
			sliderLabel.layoutY = 130

			content = List(toolbar, progress, scrollBar, scrollLabel, separator, slider, sliderLabel)

			advButton.onAction = (e:ActionEvent) => {
				progress.progress = progress.progress.apply + 0.05 min 1.0 max 0.0
			}
			decButton.onAction = (e:ActionEvent) => {
				progress.progress = progress.progress.apply - 0.05 min 1.0 max 0.0
			}

			scrollBar.value.onChange {
				scrollLabel.text = "ScrollBar: "+scrollBar.value.apply
			}
			slider.value.onChange {
				sliderLabel.text = "Slider: "+slider.value.apply
			}
		}
	}
}

app.main(args)
