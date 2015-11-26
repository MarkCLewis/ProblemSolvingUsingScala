import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "First GUI"
		scene = new Scene(400, 300) {
			val button = new Button("Click Me!")
			button.layoutX = 100
			button.layoutY = 50

			val comboBox = new ComboBox(List("Scala","Java","C++","Haskell"))
			comboBox.layoutX = 200
			comboBox.layoutY = 50

			val listView = new ListView(List("AWT","Swing","JavaFX","ScalaFX"))
			listView.layoutX = 100
			listView.layoutY = 100
			listView.prefHeight = 150

			content = List(button, comboBox, listView)

			button.onAction = (e:ActionEvent) => {
				val selected = listView.selectionModel.apply.getSelectedItems
				listView.items = listView.items.apply.diff(selected)
			}

			comboBox.onAction = (e:ActionEvent) => {
				listView.items.apply += comboBox.selectionModel.apply.getSelectedItem
			}
		}
	}
}

app.main(args)
