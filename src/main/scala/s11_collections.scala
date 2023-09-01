object s11_collections {

  def main(args: Array[String]): Unit = {

    /*** A sequence is a collection of items with a defined and stable order
     * The default implementation of Seq is a List, which is a classic linked-list
     * Some Scala libraries work specifically with Lists rather than using more generic types like Seq
     */

/** accessing elements **/
    val sequence = Seq(1, 2, 3)
    assert(sequence.apply(0)==1)
    assert(sequence(0) == 1)// sugared syntax
    //sequence(3)  //java.lang.IndexOutOfBoundsException: 3
    assert(sequence.head==1)
    assert(sequence.tail == Seq(2,3))
    assert(sequence.tail.head==2)
    assert(sequence.headOption ==Some(1))
    assert(sequence.length==3)


    assert(sequence.contains(2)==true)
    assert(sequence.find(_ == 3)==Some(3))
    assert(sequence.find(_ > 4) == None)
    assert(sequence.filter(_ > 1) == List(2,3))
    assert(sequence.sortWith(_ > _)==List(3,2,1))
    assert(sequence :+ 4 == List(1, 2, 3, 4))
    assert(0 +: sequence  == List(0, 1, 2, 3))
    assert(sequence ++ Seq(4, 5, 6) == List(1, 2, 3, 4, 5, 6))
/**working with sequence*/
    assert(sequence.map(elt => elt * 2) == List(2,4,6))
    assert(sequence.map(_ * 2) == List(2,4,6))
    assert(Seq(1, 2, 3).flatMap(num => Seq(num, num * 10)) == List(1, 10, 2, 20, 3, 30))
/**
 * The default implementation of Seq is a List
 * */
    val list = 1 :: 2 :: 3 :: Nil
    assert(list == List(1, 2, 3))
    assert(4 :: 5 :: list ==List(4, 5, 1, 2, 3))
    assert(List(1, 2, 3) ::: List(4, 5, 6) == List(1, 2, 3, 4, 5, 6))

/**
 * For Comprehensions
 * Not Your Father’s For Loops
 *
   a.flatMap(x => b.flatMap(y => c.map(z => e)))
 *
 * equals
 *
   for {
          x <- a  //flatmap
          y <- b  //flatmap
          z <- c  //flatmap
      } yield e   //map
 *
 * */

    val data = Seq(Seq(1), Seq(2, 3), Seq(4, 5, 6))
    assert(data.flatMap(_.map(_ * 2)) == List(2, 4, 6, 8, 10, 12))

    val res0 = for {
          subseq <- data
          element <- subseq
      } yield element * 2

    assert(res0 == List(2, 4, 6, 8, 10, 12))






  }
}
