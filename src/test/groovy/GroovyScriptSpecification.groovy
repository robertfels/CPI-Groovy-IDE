package groovy

import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import com.sap.gateway.ip.core.customdev.util.Message
import spock.lang.Shared

class GroovyScriptSpecification extends spock.lang.Specification {

    @Shared GroovyShell shell = new GroovyShell()
    @Shared Script script
    private Message msg

    def setupSpec() {
        // Load Groovy Script
        script = shell.parse(new File("src/main/resources/script/example.groovy"))
    }

    def setup() {
        this.msg = new MessageImpl()
    }

    def "message header"() {

        given: "the message body, header and property is provided with initial value"
        this.msg.setBody(new File("src/test/groovy/cases/case-1-source.msg").getText('UTF-8'))
        this.msg.setHeader("oldHeader", "MyGroovyHeader")
        this.msg.setProperty("oldProperty", "MyGroovyProperty")

        when: "we execute the Groovy script"
        script.processData(this.msg)

        then: "the body, headers and properties are correctly populated or modified"
        this.msg.getHeaders()["oldHeader"] == "MyGroovyHeadermodified"
        this.msg.getHeaders()["newHeader"] == "newHeader"
    }

    def "message properties"() {

        given: "the message body, header and property is provided with initial value"
        this.msg.setBody(new File("src/test/groovy/cases/case-1-source.msg").getText('UTF-8'))
        this.msg.setHeader("oldHeader", "MyGroovyHeader")
        this.msg.setProperty("oldProperty", "MyGroovyProperty")

        when: "we execute the Groovy script"
        script.processData(this.msg)

        then: "the body, headers and properties are correctly populated or modified"
        this.msg.getProperties()["oldProperty"] == "MyGroovyPropertymodified"
        this.msg.getProperties()["newProperty"] == "newProperty"
    }

    def "message body"() {

        given: "the message body, header and property is provided with initial value"
        this.msg.setBody(new File("src/test/groovy/cases/case-1-source.msg").getText('UTF-8'))
        this.msg.setHeader("oldHeader", "MyGroovyHeader")
        this.msg.setProperty("oldProperty", "MyGroovyProperty")

        when: "we execute the Groovy script"
        script.processData(this.msg)

        then: "the body, headers and properties are correctly populated or modified"
        this.msg.getBody() == new File("src/test/groovy/cases/case-1-result.msg").getText('UTF-8')
    }
}