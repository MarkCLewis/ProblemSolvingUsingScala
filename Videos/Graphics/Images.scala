import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.image._
import scalafx.scene.paint._
import scalafx.event.ActionEvent
import javax.imageio.ImageIO
import scalafx.embed.swing._

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Images"
		scene = new Scene(600, 600) {
			val img = new Image("file:castle.jpg")

			val node = img.pixelReader match {
				case None => new Label("Can't read pixels.")
				case Some(pr) =>
					val wimg = new WritableImage(pr, img.width.value.toInt, img.height.value.toInt)
					val pw = wimg.pixelWriter
					for(i <- 0 until img.width.value.toInt/2; j <- 0 until img.height.value.toInt) {
						val c = pr.getColor(i,j)
						pw.setColor(i,j,Color(c.red, c.red, c.red, 1.0))
					}
					ImageIO.write(SwingFXUtils.fromFXImage(wimg, null), "png", new java.io.File("castle2.png"))
					new ImageView(wimg)
			}

			content = node
		}
	}
}

app.main(args)
