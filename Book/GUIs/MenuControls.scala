import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.Label
import scalafx.scene.control.MenuBar
import scalafx.scene.control.Menu
import scalafx.scene.control.MenuItem
import scalafx.scene.control.MenuButton
import scalafx.scene.control.SeparatorMenuItem
import scalafx.scene.control.CheckMenuItem
import scalafx.scene.control.ContextMenu
import scalafx.scene.control.RadioMenuItem
import scalafx.scene.control.SplitMenuButton
import scalafx.scene.control.ToggleGroup
import scalafx.scene.layout.Priority
import scalafx.stage.FileChooser
import scalafx.event.ActionEvent
import scalafx.scene.input.KeyCode
import scalafx.scene.input.KeyCombination
import scalafx.scene.input.KeyCodeCombination

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Menu Controls"
    scene = new Scene(500, 200) {
      val menuBar = new MenuBar()
      val fileMenu = new Menu("File")
      val newItem = new MenuItem("New")
      newItem.accelerator = new KeyCodeCombination(KeyCode.N, KeyCombination.ControlDown)
      val openItem = new MenuItem("Open")
      openItem.accelerator = new KeyCodeCombination(KeyCode.O, KeyCombination.ControlDown)
      val saveItem = new MenuItem("Save")
      saveItem.accelerator = new KeyCodeCombination(KeyCode.S, KeyCombination.ControlDown)
      val exitItem = new MenuItem("Exit")
      exitItem.accelerator = new KeyCodeCombination(KeyCode.X, KeyCombination.ControlDown)
      fileMenu.items = List(newItem, openItem, saveItem, new SeparatorMenuItem, exitItem)

      val checkMenu = new Menu("Checks")
      val check1 = new CheckMenuItem("Check 1")
      val check2 = new CheckMenuItem("Check 2")
      checkMenu.items = List(check1, check2)

      val radioMenu = new Menu("Radios")
      val radio1 = new RadioMenuItem("Radio 1")
      val radio2 = new RadioMenuItem("Radio 2")
      val radio3 = new RadioMenuItem("Radio 3")
      val group = new ToggleGroup
      group.toggles = List(radio1, radio2, radio3)
      radioMenu.items = List(radio1, radio2, radio3)

      val typesMenu = new Menu("Types")
      typesMenu.items = List(checkMenu, radioMenu)
      menuBar.menus = List(fileMenu, typesMenu)
      menuBar.prefWidth = 500

      val menuButton = new MenuButton("Menu Button")
      menuButton.items = List(new MenuItem("Button 1"), new MenuItem("Button 2"))
      menuButton.layoutX = 20
      menuButton.layoutY = 50

      val splitMenuButton = new SplitMenuButton(new MenuItem("Split Button 1"), new MenuItem("Split Button 2"))
      splitMenuButton.text.value = "Split Menu Button"
      splitMenuButton.layoutX = 20
      splitMenuButton.layoutY = 100

      val contextMenu = new ContextMenu(new MenuItem("Context 1"), new MenuItem("Context 2"))
      val label = new Label("Right click this to get a context menu.")
      label.layoutX = 20
      label.layoutY = 150
      label.contextMenu = contextMenu

      content = List(menuBar, menuButton, splitMenuButton, label)

      exitItem.onAction = (e:ActionEvent) => {
        sys.exit(0)
      }
      saveItem.onAction = (e:ActionEvent) => {
        val fileChooser = new FileChooser
        val selectedFile = fileChooser.showSaveDialog(stage)
        label.text = "Save to : "+selectedFile
      }
      openItem.onAction = (e:ActionEvent) => {
        val fileChooser = new FileChooser
        val selectedFile = fileChooser.showOpenDialog(stage)
        label.text = "Open : "+selectedFile
      }
    }
  }
}

app.main(args)
