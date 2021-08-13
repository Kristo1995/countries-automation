package endpoints.capital;

import io.restassured.response.Response;
import org.junit.Test;
import util.IntegrationTests;
import util.TestUtil;

public class CapitalUnhappyPathIT extends IntegrationTests {

    public CapitalUnhappyPathIT() {
        super("endpoints/capital/unhappy-path/");
    }

    public void getAndAssertResponse(){
        Response response = TestUtil.getActualResponse(env);
        TestUtil.assertResponse(expectedResponse, response);
    }

    @Test
    public void shouldReturn404WhenInvalidCapital(){
        getAndAssertResponse();
    }
}
