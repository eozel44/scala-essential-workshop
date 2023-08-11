object s10_functors_monads {
/**
 * A type like F[A] with a map method is called a functor.
 * If a functor also has a flatMap method it is called a monad.
 * */
//Functor
  sealed trait LinkedList[A] {
    def fold[B](end: B, f: (A, B) => B): B =
      this match {
        case End() => end
        case Pair(head, tail) => f(head, tail.fold(end, f))
      }
     def map[B](f:A=>B):LinkedList[B] ={
       this match {
         case End() => End()
         case Pair(head, tail) => Pair(f(head),tail.map(f))
       }
     }
  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends
    LinkedList[A]
  final case class End[A]() extends LinkedList[A]

  //Monad
  sealed trait Maybe[A] {
    def flatMap[B](f: A => Maybe[B]): Maybe[B] = {
      this match {
        case Empty() => Empty[B]()
        case Full(value) => f(value)
      }
    }
    def map[B](f:A=>B):Maybe[B] ={
      this match{
        case Empty() => Empty[B]()
        case Full(value) => Full(f(value))
      }
    }
    //uqual map
    def map2[B](fn: A => B): Maybe[B] =
      flatMap[B](v => Full(fn(v)))

  }
  final case class Full[A](value: A) extends Maybe[A]
  final case class Empty[A]() extends Maybe[A]


  def main(args: Array[String]): Unit = {

    val example = Pair(1, Pair(2, Pair(3, End())))

    val sum = (x: Int, y:Int) => x + y
    assert(example.fold(0,sum) == 6)

    //map
    assert(example.map(x=>x*2) == Pair(2, Pair(4, Pair(6, End()))))
    assert(example.map(_ + 2) == Pair(3, Pair(4, Pair(5, End()))))



    val mightFail1: Maybe[Int] = Full(1)
    val mightFail2: Maybe[Int] = Full(2)


    //flatmap
    val double = mightFail1.map(_ * 2)
    val sumMaybe = mightFail1.flatMap{x => mightFail2.flatMap{ y=>
      Full(x+y)
     }
    }
    assert(sumMaybe==Full(3))
    assert(double==Full(2))

    //flatMap & map
    val result = mightFail1.flatMap{x => mightFail2.flatMap{ y=>
        Full(x + y)
      }
    }.map2(_ * 2)
    assert(result==Full(6))

    //???
    val mightFail3: Maybe[Int] = Empty()
    val sumMaybe3 = mightFail1.flatMap { x =>
      mightFail2.flatMap { y =>
        mightFail3.flatMap { z =>
          Full(x + y + z)
        }
      }
    }
    assert(sumMaybe3 == Empty())
  }

  /***
     We use map when we want to transform the value within the context to a new value,
     while keeping the context the same.
     We use flatMap when we want to transform the value and provide a new context.
   */
}
