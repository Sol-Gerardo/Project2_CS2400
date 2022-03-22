package Chapter_6.atHome;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AppTest
{
    @Test
    public void test()
    {
        assertEquals(33, EvaluatePostfix.evaluatePostfix("ab*ca-/de*+"));
    }
}