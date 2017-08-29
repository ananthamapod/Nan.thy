package util

import com.typesafe.config. {Config, ConfigFactory}
import scala.collection.JavaConverters._
import javax.inject._

@Singleton
class NanthyConfig {
  val config: Config = ConfigFactory.load("application.conf")

  val port: String = "http://localhost" //config.getString("nanthy.port")
  val localAddress: String = "port" //config.getString("nanthy.localAddress")

  override def toString: String = {
    s"************************************\n" +
      s"port:     $port\n" +
      s"localAddress:     $localAddress\n" +
      s"************************************"
  }
}