object s5_traits {

  def main(args: Array[String]): Unit = {

//Traits
// Classes provide us with a way to abstract over objects that have similar properties
// Traits provide us abstraction over classes, allowing us to write code that
// works with objects of different classes.

    import java.util.Date
//    case class Anonymous(id: String, createdAt: Date = new Date())
//    case class User(
//                     id: String,
//                     email: String,
//                     createdAt: Date = new Date()
//                   )

// A trait cannot have a constructor
// Traits can define abstract methods
    trait Visitor {
      def id: String // Unique id assigned to each user
      def createdAt: Date // Date this user first visited the site
      // How long has this visitor been around?
      def age: Long = new Date().getTime - createdAt.getTime
    }
    case class Anonymous(
                          id: String,
                          createdAt: Date = new Date()
                        ) extends Visitor
    case class User(
                     id: String,
                     email: String,
                     createdAt: Date = new Date()
                   ) extends Visitor

    def older(v1: Visitor, v2: Visitor): Boolean =
      v1.createdAt.before(v2.createdAt)

    val result = older(Anonymous("1"), User("2", "test@example.com"))

//Sealed Trait Pattern
// Sealed traits and final (case) classes allow us to control extensibility of types

//    sealed trait Visitor { /* ... */ }
//    final case class User(/* ... */) extends Visitor
//    final case class anonymous(/* ... */) extends Visitor


  }
}
