package util;

import com.google.gson.Gson;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class TestUtil {

    public static Environment getEnvironment(String fullPath) {

        final Environment environment = new Environment();
        final List<Environment> environmentsToMerge = new ArrayList<>();
        final String[] pathLevels = fullPath.split("/");

        loopThroughPathAndAddEnvironments(environmentsToMerge, pathLevels);

        for (Environment e : environmentsToMerge) {
            environment.mergeIn(e);
        }

        return environment;
    }

    public static Response getActualResponse(Environment env) {
        return given().log().all().get(env.getBaseURI() + env.getCapital());
    }

    public static ExpectedResponse getExpectedResponse(String path) {

        final ExpectedResponse expectedResponse;

        final String expectedResponseFile = "expected_response.json";

        try {
            FileReader expectedResponseJSON = new FileReader(path + expectedResponseFile);

            expectedResponse = new Gson().fromJson(expectedResponseJSON, ExpectedResponse.class);
        }catch (IOException e){
            throw new RuntimeException("Error deserializing json", e);
        }

        return expectedResponse;
    }

    public static void assertResponse(ExpectedResponse expectedResponse, Response response) {

        int expectedStatusCode = expectedResponse.getStatusCode();

        response.then().log().all().statusCode(expectedStatusCode);

        String expectedBody = expectedResponse.getBody().toString();
        String responseBody = response.jsonPath().get("").toString();

        Assert.assertThat(responseBody, equalTo(expectedBody));
    }

    private static void loopThroughPathAndAddEnvironments(final List<Environment> environmentsToMerge, final String[] pathLevels){

        StringBuilder pathInUse = new StringBuilder();

        for (String pathLevel : pathLevels){

            pathInUse.append(pathLevel).append("/");

            final String envFile = "env.json";

            try {
                FileReader envJSON = new FileReader(pathInUse + envFile);

                environmentsToMerge.add(new Gson().fromJson(envJSON, Environment.class));

                log.debug(pathInUse + envFile + " -> found");
            }catch (IOException e){
                log.debug(pathInUse + envFile + " -> not found");
            }
        }
    }
}
