import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.TreeView
import scalafx.scene.control.TreeItem
import scalafx.event.ActionEvent

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Tree Control"
    scene = new Scene(500, 300) {
      val imperative = new TreeItem("Imperative")
      imperative.children = List(new TreeItem("Java"), new TreeItem("C"), new TreeItem("C++"))
      val functional = new TreeItem("Functional")
      functional.children = List(new TreeItem("Lisp"), new TreeItem("Haskell"), new TreeItem("Scala"))
      val treeRoot = new TreeItem("Languages")
      treeRoot.children = List(imperative, functional)

      val tree = new TreeView(treeRoot)
      tree.layoutX = 20
      tree.layoutY = 20
      tree.prefWidth = 460
      tree.prefHeight = 260

      content = tree

      tree.selectionModel.value.selectedItem.onChange(
        println("Selected " + tree.selectionModel.value.selectedItem.value)
      )
    }
  }
}

app.main(args)
