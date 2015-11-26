import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{ChoiceBox, ComboBox, ListView, Label}
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Selection Controls"
		scene = new Scene(600, 300) {
			val label = new Label("Feedback")
			label.layoutX = 20
			label.layoutY = 20

			val comboBox = new ComboBox(List("Combo 1", "Combo 2", "Combo 3"))
			comboBox.layoutX = 20
			comboBox.layoutY = 50

			val listView = new ListView(List.tabulate(20)(i => "Option "+(i+1)))
			listView.layoutX = 300
			listView.layoutY = 20
			listView.prefHeight = 260

			content = List(label, comboBox, listView)

			comboBox.onAction = (e:ActionEvent) => {
				label.text = "Combo Selection = "+comboBox.value.apply
			}

			listView.selectionModel.apply.selectedItems.onChange {
				label.text = "List View = "+listView.selectionModel.apply.getSelectedItems
			}
		}
	}
}

app.main(args)
