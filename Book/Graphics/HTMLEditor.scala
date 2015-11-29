import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.BorderPane
import scalafx.scene.web._
import scalafx.stage.FileChooser

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Rich Editor"
    scene = new Scene(600,400) {
      val menuBar = new MenuBar
      val fileMenu = new Menu("File")
      val openItem = new MenuItem("Open")
      val saveItem = new MenuItem("Save")
      val exitItem = new MenuItem("Exit")
      fileMenu.items = List(openItem, saveItem, new SeparatorMenuItem, exitItem)
      menuBar.menus = List(fileMenu)
      menuBar.prefWidth = Int.MaxValue

      val editor = new HTMLEditor

      val borderPane = new BorderPane
      borderPane.top = menuBar
      borderPane.center = editor

      root = borderPane

      openItem.onAction = (ae:ActionEvent) => {
        val chooser = new FileChooser
        chooser.showOpenDialog(stage) match {
          case null =>
          case file =>
            val source = io.Source.fromFile(file)
            editor.htmlText = source.mkString
            source.close
        }
      }

      saveItem.onAction = (ae:ActionEvent) => {
        val chooser = new FileChooser
        chooser.showSaveDialog(stage) match {
          case null =>
          case file =>
            val pw = new java.io.PrintWriter(file)
            pw.print(editor.htmlText)
            pw.close
        }
      }

      exitItem.onAction = (ae:ActionEvent) => {
        sys.exit(0)
      }
    }
  }
}

app.main(args)
