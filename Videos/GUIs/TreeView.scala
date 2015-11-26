import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{TreeView, TreeItem}
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = ""
		scene = new Scene(600, 300) {
			val texas = new TreeItem("Texas")
			texas.children = List(new TreeItem("Houston"), new TreeItem("Dallas"), new TreeItem("San Antonio"))
			val cali = new TreeItem("California")
			cali.children = List(new TreeItem("LA"), new TreeItem("San Francisco"), new TreeItem("Sacramento"))
			val us = new TreeItem("US")
			us.children = List(texas, cali)

			val tree = new TreeView(us)

			root = tree

			tree.selectionModel.apply.selectedItem.onChange {
				println("Selected "+tree.selectionModel.apply.getSelectedItems)
			}
		}
	}
}

app.main(args)
