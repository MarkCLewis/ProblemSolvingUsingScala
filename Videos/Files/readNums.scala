import scala.io.Source
import java.io._

try {
	val source = Source.fromFile("fiveNums.txt")
	try {
		val lines = source.getLines

		val nums = lines.filter(_.nonEmpty).map(_.toInt).toArray
		println(nums.sum)
	} catch {
		case e:NumberFormatException => println("The file contained a non-number.")
	} finally {
		source.close
	}
} catch {
	case e:FileNotFoundException => println("The file wasn't found.")
}
