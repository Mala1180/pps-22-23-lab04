package u04lab.polyglot.minesweeper

import u04lab.code.Option.None
import u04lab.polyglot.OptionToOptional

import java.util.Optional
import u04lab.code.Option.None
import u04lab.code.Option.Some
import u04lab.code.List
import u04lab.code.List.*

import scala.util.Random

trait Logics:
  def hit(x: Int, y: Int): java.util.Optional[Integer]

  def won: Boolean

class LogicsImpl2(var size: Int, var mines: Int) extends Logics:

  private var minesSet: List[(Int, Int)] = empty
  private var selected: List[(Int, Int)] = empty
  private val gridSize = size

  if (mines != 0)
    val random: Random = scala.util.Random()
    while (length(minesSet) != mines)
      minesSet = append(minesSet, Cons((random.nextInt(gridSize), random.nextInt(gridSize)), empty))
    println(minesSet)

  /**
   * Secondary constructor in order to perform test better
   */
  def this(size: Int, mines: Int, minesList: List[(Int, Int)]) =
    this(size, mines)
    minesSet = minesList
    println(minesSet)


  override def hit(x: Int, y: Int): java.util.Optional[Integer] =
    if (contains(minesSet, (x, y)))
      return OptionToOptional(None())
    selected = append(selected, Cons((x, y), empty))
    OptionToOptional(Some(neighbours(x, y)))

  override def won: Boolean =
    length(this.selected) + length(this.minesSet) == this.gridSize * this.gridSize

  private def neighbours(x: Int, y: Int): Int =
    import u04lab.code.List.filter
    import u04lab.code.Stream.*
    length(filter(flatMap(toList(take(iterate(x - 1)(_ + 1))(3)))(xx => toList(map(take(iterate(y - 1)(_ + 1))(3))(yy => (xx, yy)))))(p => contains(minesSet, p)))
