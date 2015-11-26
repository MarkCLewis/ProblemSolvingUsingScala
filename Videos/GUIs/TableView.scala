import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{TableView, TableColumn}
import scalafx.event.ActionEvent
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.{StringProperty, ObjectProperty}

case class Student(name:String, test1:Int, test2:Int)

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Table View"
		scene = new Scene(600, 300) {
			val data = ObservableBuffer(
				Student("Jane Doe", 99, 93),
				Student("John Doe", 73, 88),
				Student("Bob Builder", 85, 91)
			)

			val table = new TableView(data)
			val col1 = new TableColumn[Student, String]("Name")
			col1.cellValueFactory = cdf => StringProperty(cdf.value.name)
			val col2 = new TableColumn[Student, Int]("Test 1")
			col2.cellValueFactory = cdf => ObjectProperty(cdf.value.test1)
			val col3 = new TableColumn[Student, Int]("Test 2")
			col3.cellValueFactory = cdf => ObjectProperty(cdf.value.test2)
			val col4 = new TableColumn[Student, Double]("Average")
			col4.cellValueFactory = cdf => ObjectProperty((cdf.value.test1+cdf.value.test2)/2.0)

			table.columns ++= List(col1, col2, col3, col4)

			root = table

			table.selectionModel.apply.selectedItem.onChange {
				println("Selected "+table.selectionModel.apply.getSelectedItems)
			}
		}
	}
}

app.main(args)
