import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Label, TextField, TextArea, PasswordField}
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Text Controls"
		scene = new Scene(400,400) {
			val label = new Label("This is a label.")
			label.layoutX = 20
			label.layoutY = 20

			val textField = new TextField
			textField.layoutX = 20
			textField.layoutY = 50
			textField.promptText = "A field"

			val textArea = new TextArea
			textArea.layoutX = 20
			textArea.layoutY = 80
			textArea.prefHeight = 200
			textArea.prefWidth = 360
			textArea.promptText = "Area"

			val passwordField = new PasswordField
			passwordField.layoutX = 20
			passwordField.layoutY = 300
			passwordField.promptText = "Password"

			content = List(label, textField, textArea, passwordField)

			textField.onAction = (e:ActionEvent) => {
				label.text = "Field action: "+textField.text.apply
			}

			textArea.text.onChange {
				label.text = "Area changed: "+textArea.text.apply
			}

			passwordField.focused.onChange {
				label.text = "Password focus changed. "+passwordField.focused.apply
			}
		}
	}
}

app.main(args)
