object s5a_traits {

  def main(args: Array[String]): Unit = {

// Product Type Pattern

// If A has a b (with type B) and a c (with type C) write
//    trait A {
//         def b: B
//         def c: C
//    }

// Sum Type Pattern

// If A is a B or C write
//    sealed trait A
//    final case class B() extends A
//    final case class C() extends A

//Algebraic Data Types
//An algebraic data type is any data that uses the Product or Sum pattern.

//The Missing Patterns

// is-a and
// A is a B and C

//    trait B
//    trait C
//    trait A extends B with C

// has-a or
// A has a B or C.
// A has a d of type D, where D is a B or C.

//    trait A {
//      def d: D
//    }
//    sealed trait D
//    final case class B() extends D
//    final case class C() extends D

  }
}
