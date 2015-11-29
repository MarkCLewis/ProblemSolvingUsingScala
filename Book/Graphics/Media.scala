import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.media._
import scalafx.scene.layout._
import scalafx.scene.control.Button
import scalafx.event.ActionEvent

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Media"
    scene = new Scene(600, 350) {
      val audioClip = new AudioClip("file:Laser_Cannon-Mike_Koenig-797224747.mp3")

      val fireButton = new Button("Fire!")
      fireButton.onAction = (ae:ActionEvent) => audioClip.play
      fireButton.prefWidth = Int.MaxValue

      val media = new Media("http://techslides.com/demos/sample-videos/small.mp4")
      val mediaPlayer = new MediaPlayer(media)
      val mediaView = new MediaView(mediaPlayer)

      val playButton = new Button("Play")
      playButton.onAction = (ae:ActionEvent) => mediaPlayer.play
      val pauseButton = new Button("Pause")
      pauseButton.onAction = (ae:ActionEvent) => mediaPlayer.pause
      val stopButton = new Button("Stop")
      stopButton.onAction = (ae:ActionEvent) => mediaPlayer.stop

      val borderPane = new BorderPane
      borderPane.top = fireButton
      borderPane.left = new VBox(playButton, pauseButton, stopButton)
      borderPane.center = mediaView

      root = borderPane
    }
  }
}

app.main(args)
