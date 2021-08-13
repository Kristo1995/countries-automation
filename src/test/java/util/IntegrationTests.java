package util;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

public abstract class IntegrationTests {

    protected final String partialPath;
    protected Environment env;
    protected ExpectedResponse expectedResponse;
    protected String testCase;
    protected String fullPath;

    @Rule
    public TestName testName = new TestName();

    public IntegrationTests(String partialPath){
        this.partialPath = partialPath;
    }

    @Before
    public void beforeTest() {
        testCase = testName.getMethodName();
        fullPath = "src/test/resources/" + partialPath + testCase + "/";
        env = TestUtil.getEnvironment(fullPath);
        expectedResponse = TestUtil.getExpectedResponse(fullPath);
    }
}
