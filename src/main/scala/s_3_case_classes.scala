object s_3_case_classes {

  def main(args: Array[String]): Unit = {

//Case Classes
// Case classes are an exceptionally useful shorthand for defining a class,
// a companion object, and a lot of sensible defaults in one go.
// They are ideal for creating lightweight data-holding classes with the minimum of hassle.

    case class Person(firstName: String, lastName: String) {
      def name = firstName + " " + lastName
    }

    val eren = new Person("Eren", "Ozel")
//Features

// A field for each constructor argument—we don’t even need to write val in our constructor definition.
    val fn = eren.firstName
// A default toString method that prints a sensible constructor-like representation of the class.
    eren.toString
//Sensible equals, and hashCode methods that operate on the field values in the object.
    val eren2 = Person("Eren", "Ozel") //no need new keyword
    println(eren == eren2)
//A copy method that creates a new object with the same field values as the current one
    val eren3 = eren.copy()
    val eren4 = eren.copy(lastName = "Oz") //keyword parameters

// Case classes implement two traits: java.io.Serializable and scala.Product.

  }

}
