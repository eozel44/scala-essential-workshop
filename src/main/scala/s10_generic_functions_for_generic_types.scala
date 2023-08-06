object s10_generic_functions_for_generic_types {

  def main(args: Array[String]): Unit = {

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

    /***
     * higher-order methods
     *
     */

//    _ + _ // expands to `(a, b) => a + b`
//    foo(_) // expands to `(a) => foo(a)`
//    foo(_, b) // expands to `(a) => foo(a, b)`
//    _(foo) // expands to `(a) => a(foo)`


  }
}
