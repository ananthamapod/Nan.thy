package models

import play.api.libs.json.{Json, OFormat}

case class URLRequest (
    longUrl: String)


object URLRequest {
  implicit val jsonFormat: OFormat[URLRequest] = Json.format[URLRequest]
}