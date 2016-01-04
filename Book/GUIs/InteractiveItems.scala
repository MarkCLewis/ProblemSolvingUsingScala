import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.event.ActionEvent
import scalafx.scene.input._

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "First GUI"
    scene = new Scene(500,300) {
      fill = Color.Coral

      // Make and place elements
      val button = new Button("Remove Item")
      button.layoutX = 75
      button.layoutY = 45
      val rectangle = Rectangle(30,30)
      rectangle.layoutX = 10
      rectangle.layoutY = 140
      val label = new Label(s"Location is ${rectangle.layoutX()}, ${rectangle.layoutY()}.")
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

      // Add contents
      content = List(button, rectangle, label, checkBox, comboBox, listView)

      // Add event handlers
      button.onAction = (e:ActionEvent) => {
        val selected = listView.selectionModel.value.selectedItems
        listView.items = listView.items.value.diff(selected)
      }
      comboBox.onAction = (e:ActionEvent) => {
        listView.items.value += comboBox.selectionModel.value.selectedItem.value
      }
      onMouseClicked = (e:MouseEvent) => {
        if (checkBox.selected.value) {
          rectangle.layoutX = e.x
          rectangle.layoutY = e.y
          label.text = s"Location is ${rectangle.layoutX.value}, ${rectangle.layoutY.value}."
        }
      }
    }
  }
}

app.main(args)
