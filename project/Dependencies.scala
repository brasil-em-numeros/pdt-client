import sbt._

object Dependencies {

  object Versions {
    val zio            = "1.0.0-RC18-2"
    val zioInteropCats = "2.0.0.0-RC12"
    val http4s         = "0.21.1"
    val circe          = "0.13.0"

    val logback        = "1.2.3"
  }

  object Libraries {
    def circe(artifact: String): ModuleID = "io.circe" %% artifact % Versions.circe
    def http4s(artifact: String): ModuleID = "org.http4s" %% artifact % Versions.http4s
    def zioM(artifact: String): ModuleID = "dev.zio" %% artifact % Versions.zio

    val zio = zioM("zio")
    val zioStreams = zioM("zio-streams")
    val zioInteropCats= "dev.zio" %% "zio-interop-cats" % Versions.zioInteropCats

    val http4sDsl = http4s("http4s-dsl")
    val http4sClient = http4s("http4s-blaze-client")
    val http4sCirce = http4s("http4s-circe")

    val circeCore = circe("circe-core")
    val circeGeneric = circe("circe-generic")
    val circeParser = circe("circe-parser")

    val logback = "ch.qos.logback" % "logback-classic" % Versions.logback

    val zioTest = zioM("zio-test") % "test"
    val zioTestSbt = zioM("zio-test-sbt") % "test"
  }
}
