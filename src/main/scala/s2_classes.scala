object s2_classes {

  def main(args: Array[String]): Unit = {

// Classes
// A class is a template for creating objects that have similar methods and fields.
// In Scala a class also defines a type, and objects created from a class all share the same type.
// Scala classes are all subclasses of java.lang.Object
    class Person {// class declaration not an expression
      val firstName = "Eren"
      val lastName = "Ozel"
      def name = firstName + " " + lastName
    }
    val eren = new Person
    println(eren)

/* Java Constructor
      public class Person {

        private String firstName;

        public Person(String firstName) {
          this.firstName = firstName;
        }
        public String setFirstName(String value) {
          this.firstName = value;
        }
        public String getFirstName() {
          return this.firstName;
        }}
*/

// Constructors
// We can prefix constructor parameters with the val keyword to have Scala define fields
    class Person2(firstName: String, lastName: String) {//(val firstName)
      def name = firstName + " " + lastName
    }
    val eren2 = new Person2("eren","ozel")
    println(eren2.name)
    //eren2.firstName="aa"
    //println(eren2.name)


// All Scala methods and constructors support keyword parameters and default parameter values.
    class Person3(firstName: String, lastName: String = "lastName") {
      def greet(firstName: String = "Some", lastName: String = "Body") =
        "Greetings, " + firstName + " " + lastName + "!"
    }
    val eren3 = new Person3("eren")
    val eren4 = new Person3("eren","ozel")
    println(eren3.greet("eren"))
    println(eren4.greet(lastName = "ozel"))

//scala type hierarchy
//Nothing is the type of throw expressions, and Null is the type of the value null.
// These special types are subtypes of everything else, which helps us assign types
// to throw and null while keeping other types in our code sane.
    def badness = throw new Exception("Error")
    // badness: Nothing
    def otherbadness = null
    // otherbadness: Null
    val bar = if(true) 123 else badness
    // bar: Int = 123
    val baz = if(false) "it worked" else otherbadness
    // baz: String = null

// Function application syntax
// With function application syntax, we have first class values that behave like computations.
// Unlike methods, objects can be passed around as data.

    class TaxCalculator(tax: Int) {
      def apply(amount: Int): Int = amount + tax
    }

    val tax = new TaxCalculator(18)
    tax.apply(100)
    tax(50) // shorthand for tax.apply(4)

// Companion Objects
// Sometimes we want to create a method that logically belongs to a class
// but is independent of any particular object.
// In Java we would use a static method for this,
// but Scala has a simpler solution that we’ve seen already: singleton objects.

// It is important to note that the companion object is not an instance of the class
// It is a singleton object with its own type:

   class Timestamp(val seconds: Long)

   object Timestamp {
      def apply(hours: Int, minutes: Int, seconds: Int): Timestamp =
        new Timestamp(hours*60*60 + minutes*60 + seconds)
    }

    Timestamp(1,1,1).seconds







  }

}
