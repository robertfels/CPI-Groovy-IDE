package groovy

import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import com.sap.gateway.ip.core.customdev.util.Message

// Load Groovy Script
GroovyShell shell = new GroovyShell()
def script = shell.parse(new File("src/main/resources/script/example.groovy"))

// Initialize message with body, header and property

Message msg = new MessageImpl()
msg = script.testInit(msg)

// Execute script
println("Console:")
script.processData(msg)

// Display results of script in console
println("---")
println("Body:\r\n" + msg.getBody())

def displayMaps = { String mapName, Map map ->
    println mapName
    map.each { key, value -> println( key + " = " + value) }
}
println("---")
displayMaps("Headers:", msg.getHeaders())
println("---")
displayMaps("Properties:", msg.getProperties())