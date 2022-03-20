package Chapter_6.atHome;

public class EvaluatePostfix
{
    /**
     * This method will evaluate a postfix expression 
     * and return the expected value.
     * @param expression The postfix expression.
     * @return The value of the evaluated postfix expression.
     */
    public static int evaluatePostfix(String expression)
    {
        // Evaluates a postfix expression. 
        StackInterface<Integer> valueStack = new ResizableArrayStack<>();
        String postfix = expression;
        int index = 0;

        while (index < postfix.length())
        {
            char nextCharacter = ' ';

            if(postfix.charAt(index) == ' ')
            {
                ++index;
                continue;
            }
            if(postfix.charAt(index) != ' ') // if character does not equal a space 
            {
                nextCharacter = postfix.charAt(index);
            }
            if(Character.isLetter(nextCharacter))
            {
                int value = 0;
                value = variableToValue(nextCharacter);
                valueStack.push(value);
                ++index;
                continue;
            }
            // The result of the operation in nextCharacter and its operands 
            // operandOne and operandTwo
            int result = 0;
            int operandOne = 0;
            int operandTwo = 0;
            switch (nextCharacter)
            {
                
                case '+': 
                    operandTwo = valueStack.pop();
                    operandOne = valueStack.pop();
                    result += operandOne + operandTwo;
                    break;
                case '-' : 
                    operandTwo = valueStack.pop();
                    operandOne = valueStack.pop();
                    result += operandOne - operandTwo;
                    break;
                case '*' : 
                    operandTwo = valueStack.pop();
                    operandOne = valueStack.pop();
                    result += operandOne * operandTwo;
                    break;
                case '/' : 
                    operandTwo = valueStack.pop();
                    operandOne = valueStack.pop();
                    result += operandOne / operandTwo;
                    break;
                case '^' :
                    operandTwo = valueStack.pop();
                    operandOne = valueStack.pop();
                    result += Math.pow(operandOne, operandTwo);
                    break;
                default: break; // Ignore unexpected characters 
            }
            valueStack.push(result);
            ++index;
        } // end while 
        return valueStack.peek();
    } // end evaluateToPostfix 

    public static int variableToValue(char character)
    {
        switch(character)
        {
            case 'a':
                return 2;
            case 'b':
                return 3;
            case 'c':
                return 4;
            case 'd':
                return 5;
            case 'e':  
                return 6;
        }
        return -1;
    } // end variableToValue
} // end EvaluateToPostfix