object s9_generic_functions {

  def main(args: Array[String]): Unit = {

    /***
     * Functions allow us to abstract over methods
     *
     * Function Type Declaration
     *
     * (A, B, ...) => C
     * or
     * A=>B
     */

    val sayHi = () => "Hi!"
    // sayHi: () => String = <function0>
    sayHi()
    // res1: String = Hi!
    val addOne = (x: Int) => x + 1
    // addOne: Int => Int = <function1>
    addOne(10)
    // res2: Int = 11
    val sum = (x: Int, y:Int) => x + y
    // sum: (Int, Int) => Int = <function2>
    sum(10, 20)
    // res3: Int = 30


    sealed trait IntList {
      def fold(end: Int, f: (Int, Int) => Int): Int =
        this match {
          case End => end
          case Pair(hd, tl) => f(hd, tl.fold(end, f))
        }

      def length: Int =
        fold(0, (_, tl) => 1 + tl)

      def product: Int =
        fold(1, (hd, tl) => hd * tl)

      def sum: Int =
        fold(0, (hd, tl) => hd + tl)
    }
    case object End extends IntList
    final case class Pair(head: Int, tail: IntList) extends IntList


    val example = Pair(1, Pair(2, Pair(3, End)))

    example.length
    example.sum
    example.product

    example.fold(0,sum)


//    sealed trait IntList {
//      def fold[A](end: A, f: (Int, A) => A): A =
//        this match {
//          case End => end
//          case Pair(hd, tl) => f(hd, tl.fold(end, f))
//        }
//      def length: Int =
//        fold[Int](0, (_, tl) => 1 + tl)
//      def product: Int =
//        fold[Int](1, (hd, tl) => hd * tl)
//      def sum: Int =
//        fold[Int](0, (hd, tl) => hd + tl)
//      def double: IntList =
//        fold[IntList](End, (hd, tl) => Pair(hd * 2, tl))
//    }
//    case object End extends IntList
//    final case class Pair(head: Int, tail: IntList) extends IntList




  }
}
