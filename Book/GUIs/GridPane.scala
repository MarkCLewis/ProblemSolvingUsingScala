import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control._
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.Priority

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "GridPane"
    scene = new Scene(300,300) {
      val gridPane = new GridPane
      for (i <- 1 to 9) {
        val button = new Button(i.toString)
        gridPane.children += button
        GridPane.setColumnIndex(button, (i-1)%3)
        GridPane.setRowIndex(button, (i-1)/3+1)
        GridPane.setHgrow(button, Priority.Always)
        GridPane.setVgrow(button, Priority.Always)
        button.maxWidth = Int.MaxValue
        button.maxHeight = Int.MaxValue
      }
      val textField = new TextField
      gridPane.children += textField
      GridPane.setConstraints(textField,0,0,4,1)
      val zeroButton = new Button("0")
      gridPane.children += zeroButton
      GridPane.setConstraints(zeroButton,0,4,2,1)
      GridPane.setVgrow(zeroButton, Priority.Always)
      zeroButton.maxWidth = Int.MaxValue
      zeroButton.maxHeight = Int.MaxValue
      val periodButton = new Button(".")
      gridPane.children += periodButton
      GridPane.setConstraints(periodButton,2,4)
      GridPane.setVgrow(periodButton, Priority.Always)
      periodButton.maxWidth = Int.MaxValue
      periodButton.maxHeight = Int.MaxValue
      val plusButton = new Button("+")
      gridPane.children += plusButton
      GridPane.setConstraints(plusButton,3,1)
      GridPane.setHgrow(plusButton, Priority.Always)
      plusButton.maxWidth = Int.MaxValue
      plusButton.maxHeight = Int.MaxValue
      val minusButton = new Button("-")
      gridPane.children += minusButton
      GridPane.setConstraints(minusButton,3,2)
      minusButton.maxWidth = Int.MaxValue
      minusButton.maxHeight = Int.MaxValue
      val multButton = new Button("*")
      gridPane.children += multButton
      GridPane.setConstraints(multButton,3,3)
      multButton.maxWidth = Int.MaxValue
      multButton.maxHeight = Int.MaxValue
      val divButton = new Button("/")
      gridPane.children += divButton
      GridPane.setConstraints(divButton,3,4)
      divButton.maxWidth = Int.MaxValue
      divButton.maxHeight = Int.MaxValue

      root = gridPane
    }
  }
}

app.main(args)
