import endpoints.capital.CapitalHappyPathIT;
import endpoints.capital.CapitalUnhappyPathIT;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CapitalHappyPathIT.class,
        CapitalUnhappyPathIT.class
})
public class TestSuite {
}
