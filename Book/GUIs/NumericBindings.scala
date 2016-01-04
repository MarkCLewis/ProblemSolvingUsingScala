import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.Label
import scalafx.scene.control.ScrollBar
import scalafx.scene.control.Slider
import scalafx.scene.shape.Rectangle

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Numeric Bindings"
    scene = new Scene(600, 250) {
      val label = new Label("This stays centered.")

      val xSlider = new Slider(0,100,0)
      xSlider.layoutX = 10
      xSlider.layoutY = 10
      xSlider.prefWidth = 180

      val ySlider = new Slider(0,100,0)
      ySlider.layoutX = 210
      ySlider.layoutY = 10
      ySlider.prefWidth = 180

      val sizeSlider = new Slider(0,100,0)
      sizeSlider.layoutX = 410
      sizeSlider.layoutY = 10
      sizeSlider.prefWidth = 180

      val scroll = new ScrollBar
      scroll.layoutX = 210
      scroll.layoutY = 220
      scroll.min = 0
      scroll.max = 100
      scroll.prefWidth = 180

      val rectangle = Rectangle(10,10)
      rectangle.layoutX = 0
      rectangle.layoutY = 40

      content = List(label, xSlider, ySlider, sizeSlider, scroll, rectangle)

      label.layoutX <== (width-label.width)/2
      label.layoutY <== (height-label.height)/2
      rectangle.layoutX <== xSlider.value*6
      rectangle.layoutY <== ySlider.value+40
      rectangle.width <== (sizeSlider.value*2)+10
      rectangle.height <== (sizeSlider.value*2)+10
      scroll.value <==> ySlider.value
    }
  }
}

app.main(args)
