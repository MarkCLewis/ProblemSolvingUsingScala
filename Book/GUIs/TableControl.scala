import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control.TableView
import scalafx.scene.control.TableColumn
import scalafx.event.ActionEvent
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.StringProperty
import scalafx.beans.property.ObjectProperty

case class Student(name:String,test1:Int,test2:Int)

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Table Control"
    scene = new Scene(500, 300) {
      val data = ObservableBuffer(
        Student("Mark Smith", 76, 89),
        Student("Lisa Doe", 97, 96),
        Student("Bob Builder", 20, 54)
      )

      val table = new TableView(data)
      val col1 = new TableColumn[Student,String]("Name")
      col1.cellValueFactory = cdf => StringProperty(cdf.value.name)
      val col2 = new TableColumn[Student,Int]("Test 1")
      col2.cellValueFactory = cdf => ObjectProperty(cdf.value.test1)
      val col3 = new TableColumn[Student,Int]("Test 2")
      col3.cellValueFactory = cdf => ObjectProperty(cdf.value.test2)
      val col4 = new TableColumn[Student,Double]("Average")
      col4.cellValueFactory = cdf => 
          ObjectProperty((cdf.value.test1+cdf.value.test2)/2.0)
      table.columns ++= List(col1, col2, col3, col4)

      root = table

      table.selectionModel.value.selectedItem.onChange(
        println("Selected " + table.selectionModel.value.selectedItem.value)
      )
    }
  }
}

app.main(args)
