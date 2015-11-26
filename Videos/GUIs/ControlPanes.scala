import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Control Pane"
		scene = new Scene(600, 300) {
			val tabPane = new TabPane
			val tab1 = new Tab
			tab1.text = "Split and Accordion"
			val accordion = new Accordion
			for(i <- 1 to 10) {
				val titled = new TitledPane
				titled.text = "Titled Pane "+i
				titled.content = new Button("Button "+i)
				accordion.panes += titled
			}
			val splitPane = new SplitPane
			splitPane.items ++= List(accordion,new TextArea)
			tab1.content = splitPane
			val tab2 = new Tab
			tab2.text = "Scroll"
			val tiles = new TilePane
			for(i <- 1 to 100) tiles.children += new Button("Button "+i)
			val scroll = new ScrollPane
			scroll.content = tiles
			tab2.content = scroll

			tabPane.tabs = List(tab1, tab2)

			root = tabPane
		}
	}
}

app.main(args)
