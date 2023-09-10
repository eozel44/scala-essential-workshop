object s1_basics {

  def main(args: Array[String]): Unit = {
/**
 'Expressions' are part of a program’s text that it evaluates to a value.
 A 'value' is information stored in the computer’s memory.
 In Scala all values are objects and 'Types' are restrictions on our programs that
 limit how we can manipulate objects.
*/
    val a = "hello scala" //type inference
    val b: String = "HELLO SCALA"

    assert(a=="hello scala")
    assert(b=="HELLO SCALA")
    assert("hi scala".toUpperCase == "HI SCALA")
/**
 Scala programs are evaluated as soon as they compile,
 which gives the appearance that there is only one stage.
 It is important to understand that compile- and run-time really are distinct,
 as it is this distinction that allows us to properly understand the difference between types and values.
*/
      //  val c = toUpperCase."Hello world!"  /// should be syntactically correct
      //  val d = 2.toUpperCase               /// should be type check
      //  val e = 2 / 0                       /// runtime


/** method call:
          anExpression.methodName(param1, ...) || anExpression methodName parameter
 */
    "Hello world!".take(2 + 3)

/** operators */
    val f = 43.-(3).+(2)
/** infix Operator Notation */
    val g = "the quick brown fox" split " "

    /* Literal expressions - primitive types
        Byte
        Char
        Short
        Int
        Long
        Float
        Double
        Boolean
        String
        Null // managed optional at scala
        Unit // equal javas void
    */
/**
 Object Literals - created our own objects
 When we write an object literal we use a declaration,
 which is a different kind of program to an expression.
 A declaration does not evaluate to a value. Instead it associates a name with a value.
 This name can then be used to refer to the value in other code.
*/
    object Test { //declaration object with name which is also expression
      val name = "Eren"  // field
      def hello(other: String): String = { // method
        name + " says hi to " + other
      }
    }
/**
 Once we have bound the name Test we can use it in expressions,
 where it evaluates to the object we have declared.
*/
    val h = Test.hello("Ahmet")
    assert(h == "Eren says hi to Ahmet" )
/**
 A conditional allows us to choose an expression to evaluate based on some condition.
 Scala’s if statement has the same syntax as Java’s. One important difference
 is that Scala’s conditional is an expression—it has a type and returns a value.
 Blocks are expressions that allow us to sequence computations together.
*/
    object Test2 {
      val blockExpression = if(1<2) {
        println("evaluaiton")
        "Eren"
      } else "Enes"
      def hello(other: String): String = {
        blockExpression + " says hi to " + other
      }
      def merhaba(other: String):String= ???
    }

//order of evaluation
    object argh {
      def a = {
        println("a")
        1
      }
      val b = {
        println("b")
        a + 2
      }
      def c = {
        println("c")
        a
        b + "c"
      }
    }

    argh.c + argh.b + argh.a

  }

}
