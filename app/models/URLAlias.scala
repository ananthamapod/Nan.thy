package models

import play.api.libs.json.{Json, OFormat}

case class URLAlias (
  aliasUrl: String,
  longUrl: String,
  var requests: Int,
  var hits: Int)

object URLAlias {
  implicit val jsonFormat: OFormat[URLAlias] = Json.format[URLAlias]
}