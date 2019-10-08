name := "pubsub-apacheflink"

version := "0.1"

scalaVersion := "2.11.12"
val flinkVersion = "1.9.0"
libraryDependencies += "org.apache.flink" %% "flink-streaming-scala" % flinkVersion
libraryDependencies += "org.apache.flink" %% "flink-connector-gcp-pubsub" % flinkVersion
libraryDependencies += "org.apache.flink" %% "flink-connector-filesystem" % flinkVersion

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}