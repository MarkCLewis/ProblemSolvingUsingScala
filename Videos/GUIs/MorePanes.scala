import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "More Panes"
		scene = new Scene(600, 300) {
			val tilePane = new TilePane
			for(i <- 1 to 9) {
				val thing = new Button("Button "+i)
				thing.minWidth = 200
				tilePane.children += thing
			}
			tilePane.children += new TextField

			val label = new Label("On Stack")
			val slider = new Slider(0, 10, 0)
			val anchor = new AnchorPane
			anchor.children = List(label, slider)
			AnchorPane.setBottomAnchor(label, 0)
			AnchorPane.setBottomAnchor(slider, 0)
			AnchorPane.setLeftAnchor(label, 0)
			AnchorPane.setRightAnchor(slider, 0)

			val stack = new StackPane
			stack.children = List(tilePane, anchor)

			root = stack
		}
	}
}

app.main(args)
