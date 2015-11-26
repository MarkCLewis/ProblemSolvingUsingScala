import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Button, CheckBox, RadioButton, ToggleButton,
	ToggleGroup, Label}
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Button Controls"
		scene = new Scene(400,600) {
			val label = new Label("Feedback")
			label.layoutX = 20
			label.layoutY = 20

			val button = new Button("A Button")
			button.layoutX = 20
			button.layoutY = 50

			val cb1 = new CheckBox("Check 1")
			cb1.layoutX = 20
			cb1.layoutY = 80
			val cb2 = new CheckBox("Check 2")
			cb2.layoutX = 20
			cb2.layoutY = 110

			val rb1 = new RadioButton("Radio 1")
			rb1.layoutX = 20
			rb1.layoutY = 140
			val rb2 = new RadioButton("Radio 2")
			rb2.layoutX = 20
			rb2.layoutY = 170
			val rb3 = new RadioButton("Radio 3")
			rb3.layoutX = 20
			rb3.layoutY = 200
			val toggle1 = new ToggleGroup
			toggle1.toggles = List(rb1, rb2, rb3)

			val tb1 = new ToggleButton("Toggle 1")
			tb1.layoutX = 20
			tb1.layoutY = 230
			val tb2 = new ToggleButton("Toggle 2")
			tb2.layoutX = 20
			tb2.layoutY = 260
			val tb3 = new ToggleButton("Toggle 3")
			tb3.layoutX = 20
			tb3.layoutY = 290
			val toggle2 = new ToggleGroup
			toggle2.toggles = List(tb1, tb2, tb3)

			content = List(label, button, cb1, cb2, rb1, rb2, rb3, tb1, tb2, tb3)

			button.onAction = (e:ActionEvent) => {
				label.text = "Button clicked"
			}
			cb1.onAction = (e:ActionEvent) => {
				label.text = "cb1 clicked "+cb1.selected.apply
			}
			rb1.onAction = (e:ActionEvent) => {
				label.text = "rb1 clicked "+rb1.selected.apply
			}
			rb1.selected.onChange {
				label.text = "rb1 selection changed "+rb1.selected.apply
			}
			tb1.onAction = (e:ActionEvent) => {
				label.text = "tb1 clicked "+tb1.selected.apply
			}
		}
	}
}

app.main(args)
