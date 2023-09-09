object s_8_generic_types {

  def main(args: Array[String]): Unit = {
    /***
     * Generics
     *
     * Generic types allow us to abstract over types
     * The syntax [A] is called a type parameter.
     */

    final case class Box[A](value: A)
    Box(2)
    // res0: Box[Int] = Box(2)
    Box("hi") // if we omit the type parameter, scala will infer its value
    // res2: Box[String] = Box(hi)

    def boxMethod[A](value: A): A = value
    boxMethod[String]("foo")
    boxMethod(1)


    //check linkedlist definition below

    val example = Pair(1, Pair(2, Pair(3, End())))
    val exampleStr = Pair("eren", Pair("hello", Pair("scala", End())))

    assert(example.length == 3)
    assert(example.tail.length == 2)
    assert(End().length == 0)
    assert(exampleStr.length == 3)


    assert(example.contains(3) == true)
    assert(example.contains(4) == false)
    assert(End().contains(0) == false)
    assert(exampleStr.contains("eren") == true)


    assert(example.get(0) == 1)
    assert(example.get(1) == 2)
    assert(example.get(2) == 3)
    assert( try{
                example.get(3)
                false
               }catch{ case e:Exception => true})

    assert(example(0) == Success(1))
    assert(example(1) == Success(2))
    assert(example(2) == Success(3))
    assert(example(3) == Failure("Index out of bounds"))

  }

  /***
   * Generic Algebraic Data Types
   *
   * --Invariant Generic Sum Type Pattern
   *
   * If A of type T is a B or C write

          sealed trait A[T]
          final case class B[T]() extends A[T]
          final case class C[T]() extends A[T]
   *
   */
      sealed trait Result[A]
      case class Success[A](result: A) extends Result[A]
      case class Failure[A](reason: String) extends Result[A]

  sealed trait LinkedList[A] {
    def length: Int =
      this match {
        case Pair(hd, tl) => 1 + tl.length
        case End() => 0
      }

    def contains(item: A): Boolean =
      this match {
        case Pair(hd, tl) =>
          if(hd == item)
            true
          else
            tl.contains(item)
        case End() => false
      }

    def get(index: Int): A =
      this match {
        case Pair(hd, tl) =>
          if(index == 0)
            hd
          else
            tl.get(index - 1)
        case End() =>
          throw new Exception("Index out of bounds")
      }

          def apply(index: Int): Result[A] =
            this match {
              case Pair(hd, tl) =>
                if(index == 0)
                  Success(hd)
                else
                  tl(index - 1)
              case End() =>
                Failure("Index out of bounds")
            }
  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
  final case class End[A]() extends LinkedList[A]




}
