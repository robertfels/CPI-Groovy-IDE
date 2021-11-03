import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import com.sap.gateway.ip.core.customdev.util.Message

// Test Constructor - Run "SimpleTest"
def testInit(msg) {
    msg.setBody(new String("Hello Groovy World"))
    msg.setHeader("oldHeader", "MyGroovyHeader")
    msg.setProperty("oldProperty", "MyGroovyProperty")
    return msg
}

//Default Groovy CPI Function
Message processData(Message message) {
    //Body
    def body = message.getBody()

    message.setBody(body)
    //Headers
    def map = message.getHeaders()
    def value = map.get("oldHeader")
    message.setHeader("oldHeader", value + "modified")
    message.setHeader("newHeader", "newHeader")
    //Properties
    map = message.getProperties()
    value = map.get("oldProperty")
    message.setProperty("oldProperty", value + "modified")
    message.setProperty("newProperty", "newProperty")
    return message
}