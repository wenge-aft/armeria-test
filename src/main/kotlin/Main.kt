import com.google.protobuf.util.JsonFormat
import com.linecorp.armeria.common.SessionProtocol
import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.protobuf.ProtobufResponseConverterFunction
import test.AdminOfferService

fun main(args: Array<String>) {
  println("Hello World!")

  // Try adding program arguments via Run/Debug configuration.
  // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
  println("Program arguments: ${args.joinToString()}")



  Server.builder()
    .port(8080, SessionProtocol.HTTP)
    .annotatedService()
    .responseConverters(ProtobufResponseConverterFunction(JsonFormat.printer().preservingProtoFieldNames()))
    .build(AdminOfferService())
    .build().start().join()

}