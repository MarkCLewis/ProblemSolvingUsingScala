import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.paint._
import scalafx.scene.input._
import scalafx.scene.text._
import scalafx.event.ActionEvent
import scalafx.animation._

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = ""
		scene = new Scene(600, 600) {
			var enemies = List(Circle(10,10,10))

			val player = Circle(300,300,20)
			player.fill = Color.Green

			content = List(enemies.head, player)

			var leftPressed = false
			var rightPressed = false
			var upPressed = false
			var downPressed = false
			onKeyPressed = (e:KeyEvent) => {
				if(e.code == KeyCode.LEFT) leftPressed = true
				if(e.code == KeyCode.RIGHT) rightPressed = true
				if(e.code == KeyCode.UP) upPressed = true
				if(e.code == KeyCode.DOWN) downPressed = true
			}
			onKeyReleased = (e:KeyEvent) => {
				if(e.code == KeyCode.LEFT) leftPressed = false
				if(e.code == KeyCode.RIGHT) rightPressed = false
				if(e.code == KeyCode.UP) upPressed = false
				if(e.code == KeyCode.DOWN) downPressed = false
			}

			var lastTime = 0L
			val enemySpeed = 20
			val playerSpeed = 25
			var spawnDelay = 5.0
			val timer:AnimationTimer = AnimationTimer(t => {
				if(lastTime>0) {
					val delta = (t-lastTime)/1e9
					for(e <- enemies) {
						val dx = player.centerX.value-e.centerX.value
						val dy = player.centerY.value-e.centerY.value
						val dist = math.sqrt(dx*dx+dy*dy)
						if(dist<e.radius.value+player.radius.value) {
							content += new Text(250, 300, "You Lose!")
							timer.stop
						}
						e.centerX = e.centerX.value+dx/dist*enemySpeed*delta
						e.centerY = e.centerY.value+dy/dist*enemySpeed*delta
					}
					if(leftPressed) {
						player.centerX = player.centerX.value-playerSpeed*delta
					}
					if(rightPressed) {
						player.centerX = player.centerX.value+playerSpeed*delta
					}
					if(upPressed) {
						player.centerY = player.centerY.value-playerSpeed*delta
					}
					if(downPressed) {
						player.centerY = player.centerY.value+playerSpeed*delta
					}
					spawnDelay -= delta
					if(spawnDelay<0) {
						val e = Circle(math.random*600,math.random*600,10)
						content += e
						enemies ::= e
						spawnDelay = 5.0
					}
				}
				lastTime = t
			})
			timer.start
		}
	}
}

app.main(args)
