import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control._

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Control Panes"
    scene = new Scene(500,250) {
      val tabPane = new TabPane
      val splitPane = new SplitPane
      val tabArea = new TextArea
      val panesTab = new Tab
      panesTab.text = "Control Panes"
      panesTab.content = splitPane
      val areaTab = new Tab
      areaTab.text = "Text Area"
      areaTab.content = tabArea
      tabPane.tabs = List(panesTab, areaTab)
      val scrollPane = new ScrollPane
      val rightArea = new TextArea
      splitPane.items += scrollPane
      splitPane.items += rightArea
      val accordion = new Accordion
      for (i <- 1 to 10) {
        val titledPane = new TitledPane
        titledPane.text = "Title Pane "+i
        titledPane.content = new Button("Button "+i)
        accordion.panes += titledPane
      }
      scrollPane.content = accordion

      root = tabPane
    }
  }
}

app.main(args)
