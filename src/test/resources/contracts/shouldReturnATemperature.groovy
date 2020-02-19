import org.springframework.cloud.contract.spec.Contract

def unitFormatRegex = "([CKRF]{1})";

Contract.make {
    description "should return a temperature"
    request {
        urlPath(
                value(
                        consumer("/temperature"),
                        producer("/temperature")
                )
        ) {
            queryParameters {
                parameter 'type': value(consumer(regex(unitFormatRegex)))
            }
        }
        method GET()
    }
    response {
        status 201
        body(
                temprature: $(client(1.0), server(anyNumber())),
                unitName: $(client("C"), server(anyNonEmptyString()))
        )
    }
}