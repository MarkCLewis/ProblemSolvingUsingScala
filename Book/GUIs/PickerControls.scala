import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.Label
import scalafx.scene.control.ColorPicker
import scalafx.scene.control.DatePicker
import scalafx.scene.paint.Color
import scalafx.event.ActionEvent
import java.time.LocalDate

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Picker Controls"
    scene = new Scene(250,130) {
      // Label
      val label = new Label("Shows date once selected.")
      label.layoutX = 20
      label.layoutY = 20

      // ColorPicker
      val colorPicker = new ColorPicker(Color.White)
      colorPicker.layoutX = 20
      colorPicker.layoutY = 50

      // DatePicker
      val datePicker = new DatePicker(LocalDate.now)
      datePicker.layoutX = 20
      datePicker.layoutY = 80

      content = List(label, colorPicker, datePicker)

      colorPicker.onAction = (e:ActionEvent) => {
        fill = colorPicker.value.value
      }

      datePicker.onAction = (e:ActionEvent) => {
        label.text = "Date is : " + datePicker.value.value
      }
    }
  }
}

app.main(args)
