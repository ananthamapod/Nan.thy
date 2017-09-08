package controllers

import java.security.MessageDigest
import javax.inject.Inject

import models.{URLAlias, URLRequest}
import play.api.Logger
import play.api.libs.json.{JsError, JsValue, Json}
import play.api.mvc.{Action, _}
import util.NanthyConfig

class URLController @Inject()(cc: ControllerComponents, appConfig: NanthyConfig) extends AbstractController(cc) {
  var urlMap: Map[String, URLAlias] = Map.empty

  def createUrl:Action[JsValue] = Action(parse.json) { implicit request =>
    val config = Json.fromJson[URLRequest](request.body)
    if (config.isError) {
      val error = JsError.toJson(config.asInstanceOf[JsError]).toString
      Logger.error(error)
      BadRequest(error)
    } else {
      try {
        val originalUrl: String = config.get.longUrl
        val id: String = sha256(config.get.longUrl)
        val aliasUrl: String = s"${appConfig.localAddress}:${appConfig.port}/$id"
        var responseObject: URLAlias = null
        if (urlMap.contains(id)) {
          val message: String = s"Shortened url for $originalUrl at $aliasUrl requested"
          responseObject = urlMap(id)
          responseObject.requests += 1
          Logger.info(message)
        } else {
          val message: String = s"Created new shortened url for $originalUrl at $aliasUrl"
          responseObject = new URLAlias(aliasUrl, originalUrl, 1, 0)
          urlMap += (id -> responseObject)
          Logger.info(message)
        }
        Created(Json.toJson(responseObject))
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

  def getUrl(shortenedUrlId: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(urlMap(shortenedUrlId)))
  }

  def deleteUrl(shortenedUrlId: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val message: String = s"Deleted shortened url $shortenedUrlId"
    urlMap -= shortenedUrlId
    Logger.info(message)
    Ok(message)
  }

  def remapUrl(shortenedUrlId: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val urlAlias: URLAlias = urlMap(shortenedUrlId)
    val redirectUrl: String = urlAlias.aliasUrl
    urlAlias.hits += 1
    Logger.info(s"Hit $redirectUrl")
    Redirect(redirectUrl)
  }

  private def sha256(string: String): String = {
    val md: MessageDigest = MessageDigest.getInstance("SHA-256")
    md.digest(string.getBytes).map(0xFF & _).foldLeft("") {_ + _}
  }
}