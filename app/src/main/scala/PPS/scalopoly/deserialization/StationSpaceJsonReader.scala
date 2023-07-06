package PPS.scalopoly.deserialization

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.StationSpace
import PPS.scalopoly.utils.JsonUtils
import com.google.gson.stream.JsonReader

import java.io.Reader
import scala.collection.mutable.ListBuffer

/** JsonReader for [[PPS.scalopoly.model.space.purchasable.StationSpace]] objects. It reads a json file and returns a
  * list of [[PPS.scalopoly.model.space.purchasable.StationSpace]].
  */
object StationSpaceJsonReader extends MyJsonReader[StationSpace]:
  override def read(reader: JsonReader): StationSpace =
    var name = ""
    var sellingPrice = 0
    var rents = List.empty[Int]
    var spaceGroup = SpaceGroup.ARANCIONE

    reader.beginObject()
    while (reader.hasNext)
      val currentName = reader.nextName()
      currentName match
        case "name"         => name = reader.nextString()
        case "sellingPrice" => sellingPrice = reader.nextInt()
        case "rents"        => rents = JsonUtils.readRents(reader)
        case "spaceGroup" =>
          spaceGroup = SpaceGroup.valueOf(reader.nextString())
        case _ => reader.skipValue()
    reader.endObject()
    StationSpace(name, sellingPrice, rents, spaceGroup)
