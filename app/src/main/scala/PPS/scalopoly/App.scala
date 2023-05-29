package PPS.scalopoly

import PPS.scalopoly.model.Game
import PPS.scalopoly.view.CLIUI

object App:
  def main(args: Array[String]): Unit =
    val view: CLIUI = new  CLIUI
    val game: Game = new Game
    view.showGameStart(game)
