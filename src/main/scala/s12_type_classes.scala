object s12_type_classes {

  def main(args: Array[String]): Unit = {

    /**
     * Type Classes
     *
     * A type class is like a trait, defining an interface. However, with type classes we can:
          • plug in different implementations of an interface for a given class
          • implement an interface without modifying existing code.
     * */

    import scala.math.Ordering

    implicit val minOrdering = Ordering.fromLessThan[Int](_ < _)
    val maxOrdering = Ordering.fromLessThan[Int](_ > _)

    assert(List(3,4,2).sorted(minOrdering) == List(2,3,4))

    assert(List(3,4,2).sorted(maxOrdering) == List(4,3,2))

    /**
    It can be inconvenient to continually pass the type class instance to a method
    when we want to repeatedly use the same instance.

    Scala provides a convenience, called an implicit value, that allows us to get the compiler to pass the
    type class instance for us.

    Note: multiple implicit values are not allowed in same scope.
    */
    assert(List(3,4,2).sorted == List(2,3,4))
  }
}
