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

/** All of these methods have the same general pattern, which is not surprising
as they all use structural recursion. It would be nice to be able to remove the duplication.

def abstraction(end: Int, f: ???): Int =
this match {
            case End => end
            case Pair(hd, tl) => f(hd, tl.abstraction(end, f))
}
 */
//    sealed trait IntList {
//      def length: Int =
//        this match {
//          case End => 0
//          case Pair(hd, tl) => 1 + tl.length
//        }
//      def double: IntList =
//        this match {
//          case End => End
//          case Pair(hd, tl) => Pair(hd * 2, tl.double)
//        }
//      def product: Int =
//        this match {
//          case End => 1
//          case Pair(hd, tl) => hd * tl.product
//        }
//      def sum: Int =
//        this match {
//          case End => 0
//          case Pair(hd, tl) => hd + tl.sum
//        }
//    }
//    case object End extends IntList
//    case class Pair(hd: Int, tl: IntList) extends IntList

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

  }

  /**
   * Fold Pattern
      For an algebraic datatype A, fold converts it to a generic type B.
      Fold is a structural recursion with:
        • one function parameter for each case in A;
        • each function takes as parameters the fields for its associated class;
        • if A is recursive, any function parameters that refer to a recursive
          field take a parameter of type B.
      The right-hand side of pa􀂂ern matching cases, or the polymorphic methods
      as appropriate, consists of calls to the appropriate function.
   * */

  sealed trait LinkedList[A] {
    def fold[B](end: B, f: (A, B) => B): B =
      this match {
        case End() => end
        case Pair(hd, tl) => f(hd, tl.fold(end, f))
      }
  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends
    LinkedList[A]
  final case class End[A]() extends LinkedList[A]
}
