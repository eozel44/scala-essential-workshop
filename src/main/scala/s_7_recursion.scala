object s_7_recursion {

  def main(args: Array[String]): Unit = {

//This data structure is known as a singly-linked list.
    sealed trait IntList
    final case object End extends IntList
    final case class Pair(head: Int, tail: IntList) extends IntList

    val d = End
    val c = Pair(3, d)
    val b = Pair(2, c)
    val a = Pair(1, b)

    println(a)

    def sum(list: IntList): Int =
      list match {
        case End => 0
        case Pair(head, tail) => head + sum(tail)
      }

     println(sum(a))

//Tail Recursion
//You may be concerned that recursive calls will consume excessive stack space.
//Scala can apply an optimisation, called tail recursion,
//to many recursive functions to stop them consuming stack space.

    import scala.annotation.tailrec

//Scala only optimises tail calls where the caller calls itself.
def method1: Int = 1
def tailCall: Int = method1 //tailCall immediately returns the result of calling method1
def notATailCall: Int = method1 + 2 //notATailCall does not immediatley return it adds an number to the result of the call.

// Any non-tail recursion function can be transformed into a tail recursive version
// by adding an accumulator as we have done with sum above
    @tailrec
    def sumTailRec(acc: Int=0, list: IntList): Int =
      list match {
        case End => acc
        case Pair(head, tail) => sumTailRec(acc+head,tail)
      }
    println(sumTailRec(0,a))

//factorial sample

def facto(i: BigInt): BigInt = if (i <= 1) 1 else i * facto(i - 1)

    def factoTail(i: BigInt): BigInt = {
      @tailrec
      def loop(acc: BigInt, a: BigInt): BigInt =
        if (a <= 1) acc else loop(a * acc, a - 1)

      loop(1, i)
    }

//extend trait
val list = Pair(1, Pair(2, Pair(3, End)))
    //    assert(list.length == 3)
    //    assert(list.tail.length == 2)
    //    assert(End.length == 0)

    //solution add method to trait
    //    def length: Int =
    //      this match {
    //        case End => 0
    //        case Pair(head, tail) => 1 + tail.length
    //      }
  }
}
