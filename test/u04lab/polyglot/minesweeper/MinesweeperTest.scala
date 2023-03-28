package u04lab.polyglot.minesweeper

import org.junit.*
import org.junit.Assert.*
import org.junit.jupiter.api.BeforeEach
import u04lab.code.List
import u04lab.code.List.*
import u04lab.polyglot.minesweeper
import u04lab.polyglot.minesweeper.*

class MinesweeperTest:


  val logics: Logics = LogicsImpl2()


  @Test def testFail(): Unit =
    assertEquals(java.util.Optional.empty(), logics.hit(0,0))

