import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control._
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.FlowPane
import scalafx.scene.layout.HBox
import scalafx.scene.layout.Pane
import scalafx.scene.layout.StackPane
import scalafx.scene.layout.TilePane
import scalafx.scene.layout.VBox
import scalafx.scene.layout.Background
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.paint.Color

def addControlsToPane(pane:Pane, fill:Color):Pane = {
  val button = new Button("Click Me")
  val label = new Label("Plain label")
  val field = new TextField
  field.text = "Text Field"
  val area = new TextArea
  area.text = "Text Area\nMultiple\nLines"
  area.prefWidth = 100
  area.prefHeight = 100
  val combo = new ComboBox(List("Alpha", "Beta", "Gamma"))
  val slider = new Slider
  pane.children = List(button, label, field, area, combo, slider)
  pane.background = new Background(Array(new BackgroundFill(fill, null, null)))
  pane
}

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Layouts"
    scene = new Scene(750,600) {
      val borderPane = new BorderPane
      borderPane.top = addControlsToPane(new HBox(10), Color.gray(0.25))
      borderPane.left = addControlsToPane(new VBox(10), Color.gray(0.75))
      borderPane.bottom = addControlsToPane(new FlowPane(10, 10), Color.gray(0.5))
      borderPane.right = addControlsToPane(new StackPane, Color.gray(1.0))
      val tilePane = new TilePane
      tilePane.prefRows = 2
      borderPane.center = addControlsToPane(tilePane, Color.gray(0.0))
      tilePane.children.foreach(_.managed = true)
      root = borderPane
    }
  }
}

app.main(args)
