import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.input._
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Circle Move"
		scene = new Scene(600, 600) {
			val circle = Circle(20, 20, 10)
			var rectangles = List[Rectangle]()

			content = circle

			onKeyPressed = (e:KeyEvent) => {
				val oldX = circle.centerX.value
				val oldY = circle.centerY.value
				if(e.code == KeyCode.UP) circle.centerY = circle.centerY.value - 2
				if(e.code == KeyCode.DOWN) circle.centerY = circle.centerY.value + 2
				if(e.code == KeyCode.LEFT) circle.centerX = circle.centerX.value - 2
				if(e.code == KeyCode.RIGHT) circle.centerX = circle.centerX.value + 2

				val clear = rectangles.forall(r => {
					Shape.intersect(circle, r).boundsInLocal.value.isEmpty
				})

				if(!clear) {
					circle.centerX = oldX
					circle.centerY = oldY
				}
			}

			onMouseClicked = (e:MouseEvent) => {
				val r = Rectangle(e.x-10, e.y-10, 20, 20)
				rectangles ::= r
				content += r
			}
			onMouseDragged = (e:MouseEvent) => {
				val r = Rectangle(e.x-10, e.y-10, 20, 20)
				rectangles ::= r
				content += r
			}
		}
	}
}

app.main(args)
