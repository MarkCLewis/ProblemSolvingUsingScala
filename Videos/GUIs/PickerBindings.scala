import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Label, DatePicker, ColorPicker}
import scalafx.event.ActionEvent
import java.time.LocalDate
import scalafx.scene.paint.Color
import scalafx.beans.property.StringProperty

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Pickers"
		scene = new Scene(300, 300) {
			val label = new Label("Shows date")
			label.layoutX = 20
			label.layoutY = 20

			val date = new DatePicker(LocalDate.now)
			date.layoutX = 20
			date.layoutY = 50

			val color = new ColorPicker(Color.White)
			color.layoutX = 20
			color.layoutY = 80

			content = List(label, date, color)

			label.text <== StringProperty("Date: ")+date.value.asString

			fill <== color.value
		}
	}
}

app.main(args)
