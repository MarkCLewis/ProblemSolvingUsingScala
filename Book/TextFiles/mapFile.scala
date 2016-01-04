import scala.io.Source
import java.io.PrintWriter 

def mapFile(inFile:String,outFile:String,trans:Char=>Char):Unit = {
  val pw = new PrintWriter(outFile)
  val in = Source.fromFile(inFile)
  for (c <- in) {
    pw.print(if (c>='a' && c<='z' || c>='A' && c<='Z') trans(c) else c)
  }
  in.close
  pw.close
} 

args(2) match {
  case "copy" =>
    mapFile(args(0),args(1),c => c)
  case "offset" =>
    val offset = args(3).toInt
    mapFile(args(0),args(1),c => {
      if (c.isLower) ('a'+(c-'a'+offset+26)%26).toChar
      else ('A'+(c-'A'+offset+26)%26).toChar
    })
  case "flip" =>
    mapFile(args(0),args(1),c => {
      if (c.isLower) ('a'+(25-(c-'a'))).toChar
      else ('A'+(25-(c-'A'))).toChar
    })
  case "key" =>
    val key = args(3)
    val factor = args(4).toInt
    var keyIndex = 0
    mapFile(args(0),args(1),c => {
      val offset = factor*(key(keyIndex)-'a')+26
      keyIndex = (keyIndex+1)%key.length
      if (c.isLower) ('a'+(c-'a'+offset+26)%26).toChar
      else ('A'+(c-'A'+offset+26)%26).toChar
    })
} 

