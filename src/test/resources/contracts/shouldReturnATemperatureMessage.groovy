import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return a temperature message"
    label "triggerTemperature"
    input {
        triggeredBy("triggerMessage()")
    }
    outputMessage {
        sentTo "temperature"
        body(name: "cantik")
    }
}