import org.apache.flink.api.common.functions.MapFunction

class Transformation extends MapFunction[String, String] {
  def map(input: String): String = {
    input.toUpperCase()
  }

}
