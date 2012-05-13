package bootstrap.liftweb

import net.liftweb.common._
import net.liftweb.http._
import net.liftweb.mapper._
import net.liftweb.sitemap._
import net.liftweb.sitemap.Loc._
import net.liftweb.util._
import org.keithjarrett.discog.model._

class Boot {
  def boot {
    if (!DB.jndiJdbcConnAvailable_?) {
      val vendor = new StandardDBVendor(
        Props.get("db.driver") openOr "org.h2.Driver",
        Props.get("db.url") openOr "jdbc:h2:lift_proto.db;AUTO_SERVER=TRUE",
        Props.get("db.user"),
        Props.get("db.password"))

      LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

      DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    }

    Schemifier.schemify(true, Schemifier.infoF _, User, Ensemble, EnsemblePerson, Person)

    val pages = List(
      Menu.i("Home") / "index" >> User.AddUserMenusAfter,
      Menu(Loc("Static", Link(List("static"), true, "/static/index"), "Static Content"))
    ) ::: Ensemble.menus ::: Person.menus

    def sitemap = SiteMap(pages: _*)

    def sitemapMutators = User.sitemapMutator

    LiftRules.addToPackages("org.keithjarrett.discog")
    LiftRules.setSiteMapFunc(() => sitemapMutators(sitemap))
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts
    LiftRules.ajaxStart = Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    LiftRules.ajaxEnd = Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    LiftRules.loggedInTest = Full(() => User.loggedIn_?)
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))


    S.addAround(DB.buildLoanWrapper())
  }
}
