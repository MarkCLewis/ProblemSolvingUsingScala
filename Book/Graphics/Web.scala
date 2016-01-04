import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.TextField
import scalafx.scene.layout.BorderPane
import scalafx.scene.web._

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Web"
    scene = new Scene(750,600) {
      val urlField = new TextField
      val webView = new WebView
      webView.engine.load("http://www.scala-lang.org")
      urlField.text = webView.location.value

      val borderPane = new BorderPane
      borderPane.top = urlField
      borderPane.center = webView

      root = borderPane

      urlField.onAction = (ae:ActionEvent) => {
        webView.engine.load(urlField.text.value)
      }
      webView.location.onChange(urlField.text = webView.location.value)
    }
  }
}

app.main(args)
