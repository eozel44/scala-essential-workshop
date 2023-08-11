object s11_collections {

  def main(args: Array[String]): Unit = {


    /*** A sequence is a collection of items with a defined and stable order
     *
     *
     */
    /*** accessing elements ***/
    val sequence = Seq(1, 2, 3)
    sequence.apply(0)
    // res0: Int = 1
    sequence(0) // sugared syntax
    // res1: Int = 1
    sequence(3)
    // java.lang.IndexOutOfBoundsException: 3

    sequence.head
    // res5: Int = 1
    sequence.tail
    // res6: Seq[Int] = List(2, 3)
    sequence.tail.head
    // res7: Int = 2
    sequence.headOption
    // res17: Option[Int] = Some(1)

    sequence.length
    // res19: Int = 3
/*** searching ***/
    sequence.contains(2)
    // res20: Boolean = true

    sequence.find(_ == 3)
    // res21: Option[Int] = Some(3)
    sequence.find(_ > 4)
    // res22: Option[Int] = None

    sequence.filter(_ > 1)
    // res23: Seq[Int] = List(2, 3)
/*** sort ***/
    sequence.sortWith(_ > _)
    // res24: Seq[Int] = List(3, 2, 1)
/*** appending/prepending ***/
    sequence.:+(4)
    // res25: Seq[Int] = List(1, 2, 3, 4)

    sequence :+ 4
    // res26: Seq[Int] = List(1, 2, 3, 4)

    0 +: sequence
    // res28: Seq[Int] = List(0, 1, 2, 3)
/*** concatenate sequences ***/
    sequence ++ Seq(4, 5, 6)
    // res29: Seq[Int] = List(1, 2, 3, 4, 5, 6)



  }
}
