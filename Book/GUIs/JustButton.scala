import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.control.Button

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "First GUI"
    scene = new Scene(300,200) {
      fill = Color.Coral
      val button = new Button("Click me!")
      content = button
    }
  }
}

app.main(args)
