import Dependencies._

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    organization := "juliano",
    name := "pdt-client",
    version := "0.0.1",
    scalaVersion := "2.13.1",
    maxErrors := 3,
    libraryDependencies ++= Seq(
        Libraries.zio,
        Libraries.zioStreams,
        Libraries.zioInteropCats,
        Libraries.http4sDsl,
        Libraries.http4sClient,
        Libraries.http4sCirce,
        Libraries.circeCore,
        Libraries.circeGeneric,
        Libraries.circeParser,
        Libraries.logback,

        Libraries.zioTestSbt
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

scalacOptions --= Seq(
    "-Xfatal-warnings"
)

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("chk", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
