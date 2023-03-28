package u04lab.polyglot.minesweeper

import u04lab.code.Option.None
import u04lab.polyglot.OptionToOptional

import java.util.Optional

trait Logics:
  def hit(x: Int, y: Int): java.util.Optional[Integer]

  def won = false

case class LogicsImpl2() extends Logics:

  override def hit(x: Int, y: Int): java.util.Optional[Integer] =
    OptionToOptional(None())
