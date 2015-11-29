import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.control._
import scalafx.scene.shape._

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "First GUI"
    scene = new Scene(500,300) {
      fill = Color.Coral

      // Create and place elements
      val button = new Button("Click me!")
      button.layoutX = 75
      button.layoutY = 45
      val rectangle = Rectangle(200,150)
      rectangle.layoutX = 10
      rectangle.layoutY = 140
      val label = new Label("A label")
      label.layoutX = 250
      label.layoutY = 10
      val checkBox = new CheckBox("Would you like to play a game?")
      checkBox.layoutX = 250
      checkBox.layoutY = 40
      val comboBox = new ComboBox(List("Scala","Java","C++"))
      comboBox.layoutX = 250
      comboBox.layoutY = 70
      val listView = new ListView("AWT Swing JavaFX ScalaFX".split(" "))
      listView.layoutX = 250
      listView.layoutY = 100
      listView.prefHeight = 190

      // Add elements to contents
      content = List(button, rectangle, label, checkBox, comboBox, listView)
    }
  }
}

app.main(args)
