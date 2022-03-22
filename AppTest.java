package Chapter_6.atHome;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AppTest
{
    @Test
    public void test()
    {
        assertEquals(true, BalanceChecker.checkBalance("a*b/(c-a)+d*e"));
        assertEquals("ab*ca-/de*+", ConvertToPostfix.convertToPostfix("a*b/(c-a)+d*e"));
        assertEquals(33, EvaluatePostfix.evaluatePostfix("ab*ca-/de*+"));
    }
}