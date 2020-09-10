name := "camel-scaladsl"
version := "1.0"
scalaVersion := "2.11.8"
val camelVersion = "2.17.1"

libraryDependencies ++= Seq(
  // Компоненты для Camel
  "org.apache.camel" % "camel-core" % camelVersion,
  "org.apache.camel" % "camel-scala" % camelVersion,
  // Для каждого компонента Camel своя зависимость
  "org.apache.camel" % "camel-quartz" % camelVersion,
  "org.apache.camel" % "camel-spring-redis" % camelVersion,
  "org.apache.camel" % "camel-http" % camelVersion,
  "org.apache.camel" % "camel-jetty" % camelVersion,
  "org.apache.camel" % "camel-jms" % camelVersion,
  "org.apache.camel" % "camel-jdbc" % camelVersion,
  // Компоненты для логгирования
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  // Компонент для работы xml в скала
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5",
  // Драйвер БД H2
  "com.h2database" % "h2" % "1.4.192",
  "org.apache.commons" % "commons-dbcp2" % "2.1.1",
  // Драйвер для брокера activemq
  "org.apache.activemq" % "activemq-client" % "5.13.3"
)
