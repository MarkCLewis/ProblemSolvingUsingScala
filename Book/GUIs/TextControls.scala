import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.Label
import scalafx.scene.control.TextField
import scalafx.scene.control.TextArea
import scalafx.scene.control.PasswordField
import scalafx.event.ActionEvent

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Text Controls"
    scene = new Scene(500,300) {
      // Label
      val label = new Label("Generally non-interactive text.")
      label.layoutX = 20
      label.layoutY = 20

      // TextField
      val textField = new TextField()
      textField.layoutX = 20
      textField.layoutY = 50
      textField.promptText = "Single-line field"

      // TextArea
      val textArea = new TextArea()
      textArea.layoutX = 20
      textArea.layoutY = 80
      textArea.promptText = "Multi-line field"
      textArea.prefWidth = 460
      textArea.prefHeight = 120

      // Password Field
      val passwordField = new PasswordField()
      passwordField.layoutX = 20
      passwordField.layoutY = 205
      passwordField.promptText = "Password field"

      content = List(label, textField, textArea, passwordField)

      textField.onAction = (e:ActionEvent) => {
        label.text = "Field action : "+textField.text.apply
      }
      textField.focused.onChange {
        if(!textField.focused.apply) label.text = 
          "Field focus : "+textField.text.apply
      }

      textArea.focused.onChange {
        if(!textArea.focused.apply) label.text = 
          "Area focus : "+textArea.text.apply
      }

      passwordField.onAction = (e:ActionEvent) => {
        label.text = "Password action : "+passwordField.text.apply
      }
      passwordField.focused.onChange {
        if(!passwordField.focused.apply) label.text = 
          "Password focus : "+passwordField.text.apply
      }
    }
  }
}

app.main(args)
