package controllers

import javax.inject.Inject

import models.URLRequest
import play.api.Logger
import play.api.libs.json.{JsError, JsValue, Json}
import play.api.mvc._

import scala.collection.mutable.ArrayBuffer

class URLController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  val urlMap: ArrayBuffer[String] = ArrayBuffer.empty[String]

  def createUrl:Action[JsValue] = Action(parse.json) { implicit request =>
    val config = Json.fromJson[URLRequest](request.body)
    if (config.isError) {
      val error = JsError.toJson(config.asInstanceOf[JsError]).toString
      Logger.error(error)
      BadRequest(error)
    } else {
      try {
        val originalUrl: String = config.get.longUrl
        val message: String = s"Created new shortened url for $originalUrl with id ${urlMap.length}"
        urlMap.append(originalUrl)
        Logger.info(message)
        Created(message)
      } catch {
        case e: Exception =>
          Logger.error(e.getMessage)
          InternalServerError("Oops, something happened on the server")
      }
    }
  }

  def listUrls: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(urlMap))
  }

  def getUrl(shortenedUrlId: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(urlMap))
  }

  def deleteUrl(shortenedUrlId: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val message: String = s"Deleted shortened url $shortenedUrlId"
    urlMap.remove(shortenedUrlId)
    Logger.info(message)
    Ok(message)
  }
}