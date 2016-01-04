import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.animation.AnimationTimer
import scalafx.scene.canvas._
import scalafx.scene.control._
import scalafx.scene.input._
import scalafx.scene.layout.BorderPane
import scalafx.scene.paint.Color
import scalafx.event.ActionEvent

case class Enemy(x:Double, y:Double, time:Double)
case class Puddle(x:Double, y:Double, size:Double)
case class Player(x:Double, y:Double)

val CanvasSize = 600

var enemies = List[Enemy]()
var puddles = List[Puddle]()
var player = Player(300,300)
var leftPressed = false
var rightPressed = false
var upPressed = false
var downPressed = false
var regenDelay = 0.0
var enemyLifespan = 10.0
var lastTime = 0L
var playerDead = false

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Puddle Dash"
    scene = new Scene(CanvasSize, CanvasSize+30) {

      val startItem = new MenuItem("Start")
      val exitItem = new MenuItem("Exit")
      val fileMenu = new Menu("File")
      fileMenu.items = List(startItem, new SeparatorMenuItem, exitItem)
      val menuBar = new MenuBar
      menuBar.menus = List(fileMenu)

      val canvas = new Canvas(CanvasSize, CanvasSize)
      val border = new BorderPane
      border.top = menuBar
      border.center = canvas
      content = border
      
      canvas.onMousePressed = (me:MouseEvent) => { puddles ::= Puddle(me.x, me.y, 5) }
      canvas.onMouseDragged = (me:MouseEvent) => { puddles ::= Puddle(me.x, me.y, 5) }
      canvas.onKeyPressed = (ke:KeyEvent) => {
        if (ke.code==KeyCode.Left) leftPressed = true
        if (ke.code==KeyCode.Right) rightPressed = true
        if (ke.code==KeyCode.Up) upPressed = true
        if (ke.code==KeyCode.Down) downPressed = true
      }
      canvas.onKeyReleased = (ke:KeyEvent) => {
        if (ke.code==KeyCode.Left) leftPressed = false
        if (ke.code==KeyCode.Right) rightPressed = false
        if (ke.code==KeyCode.Up) upPressed = false
        if (ke.code==KeyCode.Down) downPressed = false
      }
      val gt = canvas.graphicsContext2D

      def inMud(x:Double,y:Double):Boolean = {
        puddles.exists(p => {
          val dx = p.x-x
          val dy = p.y-y
          dx*dx+dy*dy<p.size*p.size
        })
      }

      def movePlayer(dt:Double):Unit = {
        val speed = if (inMud(player.x,player.y)) 10 else 30
        if (leftPressed) player = player.copy(x = player.x-speed*dt)
        if (rightPressed) player = player.copy(x = player.x+speed*dt)
        if (upPressed) player = player.copy(y = player.y-speed*dt)
        if (downPressed) player = player.copy(y = player.y+speed*dt)
      }

      def moveEnemies(t:Double, dt:Double):Unit = {
        enemies = for (e <- enemies; if e.time+enemyLifespan>t) yield {
          val speed = if (inMud(e.x,e.y)) 15 else 40
          val dx = if (e.x < player.x) speed else if (e.x > player.x) -speed else 0
          val dy = if (e.y < player.y) speed else if (e.y > player.y) -speed else 0
          e.copy(x = e.x+(dx+util.Random.nextInt(5)-2)*dt,
                 y = e.y+(dy+util.Random.nextInt(5)-2)*dt)
        }
      }

      def checkKill():Unit = {
        for (enemy <- enemies) {
          val dx = enemy.x-player.x
          val dy = enemy.y-player.y
          if (dx*dx+dy*dy < 25) {
            playerDead = true
            timer.stop
          }
        }
      }

      def updatePuddles(dt:Double):Unit = {
        puddles = puddles.filter(_.size > 1).map(p => p.copy(size = math.sqrt(p.size*p.size-5*dt)))
      }

      val timer = AnimationTimer(t => {
        if (lastTime>0) {
          val dt = (t-lastTime)/1e9
          movePlayer(dt)
          moveEnemies(t/1e9, dt)
          checkKill()
          updatePuddles(dt)
          regenDelay -= dt
          if (regenDelay < 0) {
            val cx = util.Random.nextInt(2)
            val cy = util.Random.nextInt(CanvasSize-10)
            enemies ::= Enemy(10+cx*(CanvasSize-20), cy, t/1e9)
            if (math.random<0.1) enemyLifespan += 1
            regenDelay = 10.0
          }
        }
        lastTime = t

        // Draw stuff
        gt.fill = Color.Black
        gt.fillRect(0, 0, canvas.width.value, canvas.height.value)
        for (p <- puddles) {
          gt.fill = Color.Brown
          gt.fillOval(p.x-p.size, p.y-p.size, p.size*2, p.size*2)
        }
        for (enemy <- enemies) {
          gt.fill = Color(1f, 0f, 0f, (t/1e9-enemy.time)/enemyLifespan.toFloat)
          gt.fillOval(enemy.x-5, enemy.y-5, 10, 10)
        }
        gt.fill = Color.Green
        gt.fillOval(player.x-5, player.y-5, 10, 10)
        if (playerDead) {
          gt.fill = Color.White
          gt.fillText("You Lose!", 200, 200)
        }
      })

      startItem.onAction = (e:ActionEvent) => {
        canvas.requestFocus
        timer.start
      }
      exitItem.onAction = (e:ActionEvent) => sys.exit(0)

    }
  }
}

app.main(args)
