import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.Label
import scalafx.scene.control.Button
import scalafx.scene.control.CheckBox
import scalafx.scene.control.RadioButton
import scalafx.scene.control.ToggleButton
import scalafx.scene.control.ToggleGroup
import scalafx.event.ActionEvent

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Button Controls"
    scene = new Scene(300,340) {
      // Label
      val label = new Label("Just used for feedback.")
      label.layoutX = 20
      label.layoutY = 20

      // Button
      val button = new Button("Button")
      button.layoutX = 20
      button.layoutY = 50

      // CheckBoxes
      val cb1 = new CheckBox("Check Box 1")
      cb1.layoutX = 20
      cb1.layoutY = 80
      val cb2 = new CheckBox("Check Box 2")
      cb2.layoutX = 20
      cb2.layoutY = 110

      // RadioButtons
      val rb1 = new RadioButton("Radio Button 1")
      rb1.layoutX = 20
      rb1.layoutY = 140
      val rb2 = new RadioButton("Radio Button 2")
      rb2.layoutX = 20
      rb2.layoutY = 170
      val rb3 = new RadioButton("Radio Button 3")
      rb3.layoutX = 20
      rb3.layoutY = 200
      val group1 = new ToggleGroup()
      group1.toggles = List(rb1, rb2, rb3)

      // Toggle Buttons
      val tb1 = new ToggleButton("Toggle Button 1")
      tb1.layoutX = 20
      tb1.layoutY = 230
      val tb2 = new ToggleButton("Toggle Button 2")
      tb2.layoutX = 20
      tb2.layoutY = 260
      val tb3 = new ToggleButton("Toggle Button 3")
      tb3.layoutX = 20
      tb3.layoutY = 290
      val group2 = new ToggleGroup()
      group2.toggles = List(tb1, tb2, tb3)

      content = List(label, button, cb1, cb2, rb1, rb2, rb3, tb1, tb2, tb3)

      button.onAction = (e:ActionEvent) => {
        label.text = "Button clicked"
      }

      cb1.onAction = (e:ActionEvent) => {
        label.text = "Check Box 1 is " + cb1.selected.apply
      }
      cb2.onAction = (e:ActionEvent) => {
        label.text = "Check Box 2 is " + cb2.selected.apply
      }

      rb1.onAction = (e:ActionEvent) => {
        label.text = "Radio Button 1 is " + rb1.selected.apply
      }
      rb2.onAction = (e:ActionEvent) => {
        label.text = "Radio Button 2 is " + rb2.selected.apply
      }
      rb3.onAction = (e:ActionEvent) => {
        label.text = "Radio Button 3 is " + rb3.selected.apply
      }
      rb1.selected.onChange(println("Radio button 1 is " + rb1.selected.apply))

      tb1.onAction = (e:ActionEvent) => {
        label.text = "Toggle Button 1 is " + tb1.selected.apply
      }
      tb2.onAction = (e:ActionEvent) => {
        label.text = "Toggle Button 2 is " + tb2.selected.apply
      }
      tb3.onAction = (e:ActionEvent) => {
        label.text = "Toggle Button 3 is " + tb3.selected.apply
      }
      tb1.selected.onChange(println("Toggle button 1 is " + tb1.selected.apply))
    }
  }
}

app.main(args)
