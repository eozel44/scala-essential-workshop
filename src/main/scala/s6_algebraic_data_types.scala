object s6_algebraic_data_types {

  def main(args: Array[String]): Unit = {

/***Product Type Pattern***/

// If A has a b (with type B) and a c (with type C) write

//     case class A(b: B, c: C)
//
//      trait A {
//           def b: B
//           def c: C
//      }

/***Sum Type Pattern***/

// If A is a B or C write sample:(sealed trait / final case pattern)

//      sealed trait A
//      final case class B() extends A
//      final case class C() extends A

/***Algebraic Data Types is any data that uses the Product or Sum pattern.***/

/***The Missing Patterns***/

// is-a and
// A is a B and C

//    trait B
//    trait C
//    trait A extends B with C

// has-a or
// A has a d of type D, where D is a B or C.

//    trait A {
//      def d: D
//    }
//    sealed trait D
//    final case class B() extends D
//    final case class C() extends D

//another sample; A is a D or E, and D has a B and E has a C.

//    sealed trait A
//    final case class D(b: B) extends A
//    final case class E(c: C) extends A

/***we have two patterns for building algebraic data types.***/

/***1-Structural Recursion using Polymorphism**/

    sealed trait Food
    case object Antelope extends Food
    case object TigerFood extends Food
    case object Licorice extends Food
    final case class CatFood(food: String) extends Food

//polymorphism

//    sealed trait Feline {
//      def dinner: Food
//
//    }
//    final case class Lion() extends Feline {
//      def dinner: Food =
//        Antelope
//    }
//    final case class Tiger() extends Feline {
//      def dinner: Food =
//        TigerFood
//    }
//    final case class Panther() extends Feline {
//      def dinner: Food =
//        Licorice
//    }
//    final case class Cat(favouriteFood: String) extends Feline {
//      def dinner: Food =
//        CatFood(favouriteFood)
//    }


    /***
    2-Structural Recursion using Pattern Matching
     */

    sealed trait Feline
    case class Lion() extends Feline
    case class Tiger() extends Feline
    case class Panther() extends Feline
    case class Cat(favouriteFood: String) extends Feline

//pattern matching in the base trait;

//    sealed trait Feline {
//      def dinner: Food =
//        this match {
//          case Lion() => Antelope
//          case Tiger() => TigerFood
//          case Panther() => Licorice
//          case Cat(favouriteFood) => CatFood(favouriteFood)
//        }
//    }

//pattern matching in an external object
    object Diner {
      def dinner(feline: Feline): Food =
        feline match {
          case Lion() => Antelope
          case Tiger() => TigerFood
          case Panther() => Licorice
          case Cat(food) => CatFood(food)
        }
    }

// The general rule is: if a method only depends on other fields and methods
// in a class it is a good candidate to be implemented inside the class.
// If the method depends on other data consider implementing it using pattern matching outside of the classes in question.
// If we want to have more than one implementation we should use pattern matching and implement it outside the classes.

    /***
          Add new method          Add new data
    OO    Change existing code     Existing code unchanged
    FP    Existing code unchanged  Change existing code

     ***/
  }



}
