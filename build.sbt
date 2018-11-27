lazy val V = _root_.scalafix.sbt.BuildInfo

ThisBuild / scalaVersion := V.scala212
addCompilerPlugin(scalafixSemanticdb)
ThisBuild / organization := "com.goyeau"

addCommandAlias("fmt", "; compile:scalafmt; test:scalafmt; scalafmtSbt")
addCommandAlias("fmtCheck", "; compile:scalafmtCheck; test:scalafmtCheck; scalafmtSbtCheck")

ThisBuild / licenses += "APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0")
ThisBuild / homepage := Option(url("https://github.com/joan38/scala-rules"))
ThisBuild / scmInfo := Option(
  ScmInfo(
    url("https://github.com/joan38/scala-rules"),
    "https://github.com/joan38/scala-rules.git"
  )
)
ThisBuild / developers += Developer(
  id = "joan38",
  name = "Joan Goyeau",
  email = "joan@goyeau.com",
  url = url("http://goyeau.com")
)
Global / releaseEarlyWith := SonatypePublisher
Global / releaseEarlyEnableLocalReleases := true

lazy val rules = project.settings(
  moduleName := "scala-rules",
  libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % V.scalafixVersion
)

lazy val input = project

lazy val output = project

lazy val tests = project
  .settings(
    libraryDependencies += ("ch.epfl.scala" % "scalafix-testkit" % V.scalafixVersion % Test).cross(CrossVersion.full),
    scalafixTestkitOutputSourceDirectories := sourceDirectories.in(output, Compile).value,
    scalafixTestkitInputSourceDirectories := sourceDirectories.in(input, Compile).value,
    scalafixTestkitInputClasspath := fullClasspath.in(input, Compile).value
  )
  .dependsOn(input, rules)
  .enablePlugins(ScalafixTestkitPlugin)
