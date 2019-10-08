import org.apache.flink.api.common.serialization.{SimpleStringEncoder, SimpleStringSchema}
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink
import org.apache.flink.streaming.connectors.gcp.pubsub.PubSubSource


object PubSubFlinkGCP {
  def main(args: Array[String]): Unit = {
    /**
      * Parameters
      * --project
      * --subscription
      * --output
      */
    val parameters = ParameterTool.fromArgs(args)

    //Streaming context
    val streamExecEnv = StreamExecutionEnvironment.getExecutionEnvironment

    //Source -> PubSub
    val pubsubSource = PubSubSource.newBuilder.
      withDeserializationSchema(new SimpleStringSchema)
      .withProjectName(parameters.get("project"))
      .withSubscriptionName(parameters.get("subscription"))
      .build()

    //Sink
    val sink: StreamingFileSink[String] = StreamingFileSink
      .forRowFormat(new Path(parameters.get("output")),
        new SimpleStringEncoder[String]("UTF-8"))
      .build()

    //Pipeline definition
    streamExecEnv
      .addSource(pubsubSource)
      .map(new Transformation)
      .addSink(sink)


    //execute

    streamExecEnv.execute("Flink Streaming Pipeline")
  }
}
