package Chapter_6.atHome;

public class ConvertToPostfixDemo 
{
    public static void main(String[] args)
    {
        String expression = "a*b/(c-a)+d*e";
        String postfix = ConvertToPostfix.convertToPostfix(expression);

        System.out.println("The infix expression converted to postfix is: " + postfix);
    }
}