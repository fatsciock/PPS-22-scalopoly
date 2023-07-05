package PPS.scalopoly.deserialization

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.notPurchasable.{
  BlankSpace,
  ChanceSpace,
  CommunityChestSpace,
  IncomeTaxSpace,
  LuxuryTaxSpace,
  NotPurchasableSpace
}
import PPS.scalopoly.utils.JsonUtils
import com.google.gson.stream.JsonReader

import java.io.Reader
import scala.collection.mutable.ListBuffer

object NotPurchasableSpaceJsonReader extends MyJsonReader[NotPurchasableSpace]:
  override def read(reader: JsonReader): NotPurchasableSpace =
    var name = ""
    var spaceType = ""
    var spaceValue = 0

    reader.beginObject()
    while (reader.hasNext)
      val currentName = reader.nextName()
      currentName match
        case "name"       => name = reader.nextString()
        case "spaceType"  => spaceType = reader.nextString()
        case "spaceValue" => spaceValue = reader.nextInt()
        case _            => reader.skipValue()
    reader.endObject()

    spaceType match
      case "Chance"         => ChanceSpace(name, spaceValue)
      case "CommunityChest" => CommunityChestSpace(name, spaceValue)
      case "IncomeTax"      => IncomeTaxSpace(name, spaceValue)
      case "LuxuryTax"      => LuxuryTaxSpace(name, spaceValue)
      case _                => BlankSpace(name, spaceValue)
