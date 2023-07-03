package PPS.scalopoly.model.space

import PPS.scalopoly.model.Player

import scala.util.Random

class IncomeTaxSpace(
    name: String,
    action: (Player, Int) => Player
) extends NotPurchasableSpace(name, action)

object IncomeTaxSpace:

  private val INCOME_TAX_PERC = 10
  private def action(player: Player, position: Int): Player =
    player.pay(player.ownedProperties.map(_.sellingPrice / INCOME_TAX_PERC).sum)
  def apply(
      name: String
  ): IncomeTaxSpace = new IncomeTaxSpace(name, action)
