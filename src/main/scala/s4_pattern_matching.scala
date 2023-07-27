object s4_pattern_matching {

  def main(args: Array[String]): Unit = {

// Pattern Matching
// Pattern matching is like an extended if expression that
// allows us to evaluate an expression depending on the “shape” of the data.

    case class Person(firstName: String, lastName: String) {
      def name = firstName + " " + lastName
    }

    object Stormtrooper {
      def inspect(person: Person): String =
        person match {
          case Person("Eren", "Ozel") => "Stop, rebel scum!"
          case Person("Han", "Solo") => "Stop, rebel scum!"
          case Person(first, last) => s"Move along, $first"
        }
    }

    val eren = Person("Eren", "Ozel")

    val result = Stormtrooper.inspect(eren)
    println(result)

//Samples

///
    def matchInt(i:Int) = i match {
      case 1 => "bir"
      case 0 => "sifir"
      case x if x>=2 => "buyuktur iki"
      case _ => s"negatif"  //An underscore (_), which matches any value
    }

    println(matchInt(-10))

///

    def matchList(l:List[Person]) :Int = l match {
      case Person("Eren",_) :: Nil => 0
      case Person("Eren",_) :: _ :: Nil => 1
      case _ => 2
    }
    println(matchList(List(eren)))

  }

}
