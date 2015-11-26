import scalafx.Includes._
import scalafx.application._
import scalafx.scene._
import scalafx.scene.input._
import scalafx.scene.shape._

val num = args(0).toInt

def makeRectangle(s:Int):Rectangle = {
	val width = s*20+20
	Rectangle(100-width/2, 300-(num-s)*30-30, width, 30)
}

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Towers of Hanoi"
		scene = new Scene(600, 300) {
			val pegs = Array((1 to num).map(makeRectangle).toList, Nil, Nil)
			for(r <- pegs(0)) {
				content += r
				var mx = 0.0
				var my = 0.0
				var sx = 0.0
				var sy = 0.0
				var speg = 0
				r.onMousePressed = (e:MouseEvent) => {
					mx = e.x
					my = e.y
					sx = r.x.value
					sy = r.y.value
					speg = ((r.x.value + r.width.value/2)/200).toInt
				}
				r.onMouseDragged = (e:MouseEvent) => {
					r.x = r.x.value + (e.x-mx)
					r.y = r.y.value + (e.y-my)
					mx = e.x
					my = e.y
				}
				r.onMouseReleased = (e:MouseEvent) => {
					r.x = r.x.value + (e.x-mx)
					r.y = r.y.value + (e.y-my)
					val peg = ((r.x.value + r.width.value/2)/200).toInt
					if(pegs(peg).nonEmpty && (pegs(peg).head == r || pegs(peg).head.width.value < r.width.value)) {
						r.x = sx
						r.y = sy
					} else {
						r.x = 100+peg*200-r.width.value/2
						r.y = 300-30*pegs(peg).length-30
						pegs(speg) = pegs(speg).tail
						pegs(peg) ::= r
					}
				}
			}
		}
	}
}

app.main(args)
