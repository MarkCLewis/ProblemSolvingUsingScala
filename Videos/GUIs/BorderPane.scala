import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = ""
		scene = new Scene(600, 300) {
			val border = new BorderPane
			val hbox = new HBox(10, new Label("On top"), new Button("On top"), new TextField)
			val vbox = new VBox(10, new Label("On top"), new Button("On top"), new TextField)
			val flow = new FlowPane(10, 10)
			flow.children = List(new Label("On top"), new Button("On top"), new TextField)
			border.top = hbox
			border.bottom = new Button("Bottom button")
			border.left = new ListView(List("one", "two", "three"))
			border.right = vbox
			border.center = flow

			root = border
		}
	}
}

app.main(args)
