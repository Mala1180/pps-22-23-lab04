package u04lab.polyglot.minesweeper

import org.junit.*
import org.junit.Assert.*
import u04lab.code.List
import u04lab.code.List.*
import u04lab.polyglot.minesweeper
import u04lab.polyglot.minesweeper.*

class MinesweeperTest:

  val size = 5
  val mines: List[(Int, Int)] = cons((0, 0), cons((3, 3), cons((4, 4), empty)))
  val logics: Logics = LogicsImpl2(size, 0, mines) //LogicsImpl2(7, 0, mines)

  @Test def testInitiallyNotWon(): Unit =
    assertFalse(logics.won)

  @Test def testHitMine(): Unit =
    assertEquals(java.util.Optional.empty(), logics.hit(0, 0))
    assertEquals(java.util.Optional.empty(), logics.hit(3, 3))
    assertEquals(java.util.Optional.empty(), logics.hit(4, 4))

  @Test def testHitEmptyCell(): Unit =
    assertEquals(java.util.Optional.of(1), logics.hit(1, 1))
    assertEquals(java.util.Optional.of(1), logics.hit(2, 2))
    assertEquals(java.util.Optional.of(2), logics.hit(3, 4))

  @Test def testGameWon(): Unit =
    for i <- 0 until size do
      for j <- 0 until size do
        if !contains(mines, (i, j)) then
          logics.hit(i, j)
    assertTrue(logics.won)
