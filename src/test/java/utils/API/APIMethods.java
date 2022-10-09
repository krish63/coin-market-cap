package utils.API;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Constants;


import static io.restassured.RestAssured.given;

public class APIMethods {
    private final Logger log = LoggerFactory.getLogger(APIMethods.class);

    String baseUrl = "";
    public APIMethods(){
        baseUrl= Constants.BASEURL;
    }

    public Response getMethod(String token, String endpoint) {
        String url = baseUrl + endpoint;
        String requestType = "GET";
        Response response;
        int retryCount = 0;
        //Retry for 3 times if get call fails
        do {
            response = getRequestSpecification(token, requestType, "").get(url);
            retryCount++;
        } while (response.getStatusCode() != 200 && retryCount < 3);
        checkResponseCode(requestType, url, response);
        log.info("GET API Call success\nEndpoint ="+url+"\nResponse = "+response.getBody().asString());

        return response;
    }

    public Response postMethod(String token, String endpoint, String requestBody) {
        String url = baseUrl + endpoint;
        String requestType = "POST";
        Response response = getRequestSpecification(token, requestType, requestBody).post(url);
        checkResponseCode(requestType, url, response);
        log.info("POST API Call success\nEndpoint ="+url+"\nResponse = "+response.getBody().asString());
        return response;
    }

    private RequestSpecification getRequestSpecification(String token, String requestType, String requestBody) {
        RequestSpecification requestSpecification;
        switch (requestType) {
            case "GET":
                requestSpecification = given().redirects().follow(false).contentType("application/json").header("X-CMC_PRO_API_KEY", token);
                break;
            case "DELETE":
            case "POST":
            case "PUT":
                requestSpecification = given().contentType("application/json").header("X-CMC_PRO_API_KEY", token).body(requestBody);
                break;
            default:
                throw new RuntimeException("RequestType " + requestType + " is not handled to create request specification");
        }
        return requestSpecification;
    }

    private void checkResponseCode(String requestType, String url, Response response) {
        if (!((response.getStatusCode() == 200) || (response.getStatusCode() == 302) || (response.getStatusCode() == 307))) {
            log.error(requestType + " API CALL FAILED -- " + ", Endpoint: " + url + ", Status Code: " + response.getStatusCode() + "\nResponse Headers: " + response.getHeaders().asList() + "\nResponseBody: " + response.getBody().asString());
            throw new RuntimeException(requestType + " API CALL FAILED -- " + ", Endpoint: " + url + ", Status Code: " + response.getStatusCode() + "\nResponse Headers: " + response.getHeaders().asList() + "\nResponseBody: " + response.getBody().asString());
        }
    }
}
