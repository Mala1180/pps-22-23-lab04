package u04lab.polyglot

import org.junit.*
import org.junit.Assert.*

import scala.collection.immutable.List.*

class MinesweeperTest:

  val lst: List[Int] = Cons(10, Cons(20, Cons(30, Nil())))

  @Test def testDrop(): Unit =
    assertEquals(Cons(20, Cons(30, Nil())), drop(lst, 1))
    assertEquals(Cons(30, Nil()), drop(lst, 2))
    assertEquals(Nil(), drop(lst, 5))

