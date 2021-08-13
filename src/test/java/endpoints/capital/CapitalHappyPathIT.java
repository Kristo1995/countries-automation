package endpoints.capital;

import io.restassured.response.Response;
import org.junit.Test;
import util.IntegrationTests;
import util.TestUtil;

public class CapitalHappyPathIT extends IntegrationTests {

    public CapitalHappyPathIT() {
        super("endpoints/capital/happy-path/");
    }

    public void getAndAssertResponse(){
        Response response = TestUtil.getActualResponse(env);
        TestUtil.assertResponse(expectedResponse, response);
    }

    @Test
    public void shouldReturnCountryByCapital(){
        getAndAssertResponse();
    }
}
