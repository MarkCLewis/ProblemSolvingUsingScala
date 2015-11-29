import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.control.ProgressBar
import scalafx.scene.control.ScrollBar
import scalafx.scene.control.Separator
import scalafx.scene.control.Slider
import scalafx.scene.control.ToolBar
import scalafx.event.ActionEvent
import scalafx.geometry.Orientation

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Other Controls"
    scene = new Scene(500, 190) {
      val toolBar = new ToolBar
      toolBar.prefWidth = 500

      val advButton = new Button("Advance")
      val decButton = new Button("Decrement")
      toolBar.items = List(advButton, decButton, new Separator, 
          new Button("tool 1"), new Button("tool 2"))

      val progress = new ProgressBar
      progress.layoutX = 20
      progress.layoutY = 70
      progress.prefWidth = 210

      val scroll = new ScrollBar
      scroll.layoutX = 20
      scroll.layoutY = 100
      scroll.min = 0
      scroll.max = 100
      scroll.prefWidth = 210

      val scrollLabel = new Label("Scroll bar value")
      scrollLabel.layoutX = 20
      scrollLabel.layoutY = 140

      val separator = new Separator
      separator.layoutX = 250
      separator.layoutY = 0
      separator.orientation = Orientation.VERTICAL
      separator.prefHeight = 300

      val slider = new Slider(0,10,0)
      slider.layoutX = 270
      slider.layoutY = 70
      slider.prefWidth = 210

      val sliderLabel = new Label("Slider value")
      sliderLabel.layoutX = 270
      sliderLabel.layoutY = 100

      content = List(progress, scroll, scrollLabel, separator, slider, toolBar, sliderLabel)

      advButton.onAction = (e:ActionEvent) => {
        progress.progress = progress.progress.apply + 0.05 min 1.0 max 0.0
      }

      decButton.onAction = (e:ActionEvent) => {
        progress.progress = progress.progress.apply - 0.05 min 1.0 max 0.0
      }

      scroll.value.onChange {
        scrollLabel.text = "Scroll bar = " + scroll.value.apply
      }

      slider.value.onChange {
        sliderLabel.text = "Slider = " + slider.value.apply
      }
    }
  }
}

app.main(args)
