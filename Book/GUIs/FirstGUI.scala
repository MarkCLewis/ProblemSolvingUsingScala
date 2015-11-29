import scalafx.application.JFXApp

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "First GUI"
    width = 500
    height = 500
  }
}

app.main(args)
