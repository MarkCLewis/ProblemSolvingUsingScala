import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.{Image, ImageView, WritableImage, PixelReader, PixelWriter}
import scalafx.scene.control.Label
import scalafx.scene.layout.TilePane
import scalafx.scene.paint.Color

if (args.length < 1) {
  println("You must provide an argument with the name of a file to load.")
  sys.exit(0)
}

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    scene = new Scene(800,200) {
      val original = new Image("file:"+args(0), 200, 200, true, true)
      val (red, green, blue) = original.pixelReader match {
        case Some(reader) =>
          val rimg = new WritableImage(original.width.value.toInt, original.height.value.toInt)
          val rwriter = rimg.pixelWriter
          val gimg = new WritableImage(original.width.value.toInt, original.height.value.toInt)
          val gwriter = gimg.pixelWriter
          val bimg = new WritableImage(original.width.value.toInt, original.height.value.toInt)
          val bwriter = bimg.pixelWriter
          for (i <- 0 until original.width.value.toInt; 
              j <- 0 until original.height.value.toInt) {
            val c = reader.getColor(i, j)
            rwriter.setColor(i, j, Color(c.red, 0, 0, 1.0))
            gwriter.setColor(i, j, Color(0, c.green, 0, 1.0))
            bwriter.setColor(i, j, Color(0, 0, c.blue, 1.0))
          }
          (new ImageView(rimg), new ImageView(gimg), new ImageView(bimg))
        case None =>
          (new Label("No Reader"), new Label("No Reader"), new Label("No Reader"))
      }
      val tilePane = new TilePane
      tilePane.children = List(new ImageView(original), red, green, blue)

      root = tilePane
    }
  }
}

app.main(args)
