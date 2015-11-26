import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.canvas._
import scalafx.scene.input._
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Canvas"
		scene = new Scene(600, 600) {
			val canvas = new Canvas(600,600)

			val gc = canvas.graphicsContext2D

			content = canvas

			canvas.onMouseMoved = (e:MouseEvent) => {
				gc.clearRect(0,0,canvas.width.value, canvas.height.value)
				gc.fillOval(e.x,e.y,10,10)
			}
		}
	}
}

app.main(args)
