import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.Label
import scalafx.scene.control.ChoiceBox
import scalafx.scene.control.ComboBox
import scalafx.scene.control.ListView
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Selection Controls"
    scene = new Scene(667,200) {
      // Label
      val label = new Label("For display purposes.")
      label.layoutX = 20
      label.layoutY = 20

      // ChoiceBox
      val choiceBox = new ChoiceBox(ObservableBuffer("Choice 1", "Choice 2", "Choice 3"))
      choiceBox.layoutX = 20
      choiceBox.layoutY = 50

      // ComboBox
      val comboBox = new ComboBox(List("Combo 1", "Combo 2", "Combo 3"))
      comboBox.layoutX = 20
      comboBox.layoutY = 80

      // ListView
      val listView = new ListView(List("List 1", "List 2", "List 3"))
      listView.layoutX = 353
      listView.layoutY = 20
      listView.prefHeight = 160

      content = List(label, choiceBox, comboBox, listView)

      choiceBox.value.onChange {
        label.text = "Choice selected : " + choiceBox.value.apply
      }

      comboBox.onAction = (e:ActionEvent) => {
        label.text = "Combo box selected : " + comboBox.value.apply
      }

      listView.selectionModel.apply.selectedItem.onChange {
        label.text = "List view selected : " + listView.selectionModel.apply.getSelectedItems
      }
    }
  }
}

app.main(args)
