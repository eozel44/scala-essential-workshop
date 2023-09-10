object s12_implicits {

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


    /**
     * Implicit Priority
     *
     *The compiler looks for type class instances (implicit values) in two places:
        1. the local scope
        2. the companion objects of types involved in the method call.
      Implicits found in the local scope take precedence over those found in companion objects.
     * */

    final case class Rational(numerator: Int, denominator: Int)
    object Rational {
      implicit val ordering = Ordering.fromLessThan[Rational]((x, y) =>
        (x.numerator.toDouble / x.denominator.toDouble) <
          (y.numerator.toDouble / y.denominator.toDouble)
      )
    }

    object Example{
      def example() = {
        assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted
          ==
          List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))
      }
    }

    object ExamplePriorty {
      implicit val higherPriorityImplicit = Ordering.fromLessThan[Rational]((x, y) =>
        (x.numerator.toDouble / x.denominator.toDouble) >
          (y.numerator.toDouble / y.denominator.toDouble)
      )
      def example() =
        assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted
          ==
          List(Rational(3, 4), Rational(1, 2), Rational(1, 3)))
    }

    Example.example()
    ExamplePriorty.example()

    /**
     * Implicit parameters
     *
     * If we call a method and do not explicitly supply its implicit parameter list,
       the compiler will search for implicit values of the correct types to complete the parameter list for us.
     * */

    final case class Person(name: String, email: String)
    trait HtmlWriter[A] {
      def write(in: A): String
    }

    object PersonWriter extends HtmlWriter[Person] {
      def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
    }

    PersonWriter.write(Person("John", "john@example.com"))

    implicit object ObfuscatedPersonWriter extends HtmlWriter[Person] {
      def write(person: Person) = s"${person.name} (${person.email.replaceAll("@", " at ")})"
    }

    ObfuscatedPersonWriter.write(Person("John", "john@example.com"))


    object HtmlUtil {
      def htmlify[A](data: A)(implicit writer: HtmlWriter[A]): String = {
        writer.write(data)}
    }

    HtmlUtil.htmlify(Person("John", "john@example.com"))(PersonWriter)
    HtmlUtil.htmlify(Person("John", "john@example.com"))

    /** Another sample */

    trait Equal[A] {
      def equal(v1: A, v2: A): Boolean
    }
    object Eq {
      def apply[A](v1: A, v2: A)(implicit equal: Equal[A]): Boolean =
        equal.equal(v1, v2)
    }
    object NameAndEmailImplicit {
      implicit object NameEmailEqual extends Equal[Person] {
        def equal(v1: Person, v2: Person): Boolean =
          v1.email == v2.email && v1.name == v2.name
      }
    }
    object EmailImplicit {
      implicit object EmailEqual extends Equal[Person] {
        def equal(v1: Person, v2: Person): Boolean =
          v1.email == v2.email
      }
    }

    object Examples {
      def byNameAndEmail = {
        import NameAndEmailImplicit._
        Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))
      }

      def byEmail = {
        import EmailImplicit._
        Eq(Person("Noel", "noel@example.com"), Person("Dave", "noel@example.com"))
      }
    }





  }
}
