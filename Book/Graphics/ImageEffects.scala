import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.TilePane
import scalafx.scene.effect._
import scalafx.scene.control._
import scalafx.event.ActionEvent

if (args.length < 1) {
  println("You must provide arguments with the names of files to load.")
  sys.exit(0)
}

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    scene = new Scene(680,420) {
      val tilePane = new TilePane
      for (file <- args) {
        val img = new Image("file:"+file, 300, 200, true, true)
        val view = new ImageView(img)
        val colorAdjust = new MenuItem("Color Adjust")
        colorAdjust.onAction = (e:ActionEvent) => {
          view.effect = new ColorAdjust(-0.05, 0.2, 0.1, 0.1)
        }
        val glow = new MenuItem("Glow")
        glow.onAction = (e:ActionEvent) => {
          view.effect = new Glow(0.5)
        }
        val sepia = new MenuItem("Sepia")
        sepia.onAction = (e:ActionEvent) => {
          view.effect = new SepiaTone(0.5)
        }
        val button = new MenuButton("",view)
        button.items = List(colorAdjust, glow, sepia)
        tilePane.children += button
      }

      root = tilePane
    }
  }
}

app.main(args)
