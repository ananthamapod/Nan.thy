package models

import play.api.libs.json.{Json, OFormat}

case class URLAlias (
  aliasUrl: String,
  longUrl: String)


object URLAlias {
  implicit val jsonFormat: OFormat[URLAlias] = Json.format[URLAlias]
}