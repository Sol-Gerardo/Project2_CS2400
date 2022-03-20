package Chapter_6.atHome;

public class EvaluatePostfixDemo 
{
    public static void main(String[] args)
    {
        String expression = "a*b/(c-a)+d*e";
        if(BalanceChecker.checkBalance(expression) == true)
        {        
            String postfix = ConvertToPostfix.convertToPostfix(expression); // Expression with no parenthesis
            int postfixValue = EvaluatePostfix.evaluatePostfix(postfix); // Get value from postfix expression
            
            System.out.println("After evaluating the expression " + expression 
            + " we get the following value: " + postfixValue); // print obtained value
        }
        else
        {
            System.out.println("The following expression " + expression + " is not a valid expression. ");
        }
    } // end main
} // end EvaluateToPostfix