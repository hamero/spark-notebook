package notebook

import play.api._
import play.api.mvc._

import com.typesafe.config._

import notebook._

object AppUtils {
  import play.api.Play.current

  lazy val config               = NotebookConfig(current.configuration.getConfig("manager").get)
  lazy val nbm                  = new NotebookManager(config.projectName, config.notebooksDir)
  lazy val notebookServerConfig = current.configuration.getConfig("notebook-server").get.underlying
  lazy val clustersConf         = config.config.getConfig("clusters").get

  lazy val kernelSystem =  ActorSystem( "NotebookServer",
                                        notebookServerConfig,
                                        play.api.Play.classloader // this resolves the Play classloader problems w/ remoting
                                      )
}