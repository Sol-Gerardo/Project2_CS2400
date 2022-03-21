package Chapter_6.atHome;
/**
 * This class will evaluate an expression and check
 * if the expression is balanced. If the expression is balanced
 * or valid, then the expression will be converted to a postfix 
 * expression. Once converted to a postfix expression the postfix
 * expression will then  be evaluated and return the proper result 
 * to the user.
 */
public class Calculator 
{
     /** 
     * Decides whether the parenthesis, brackets and braces
     * in a string occur in left/right pairs. 
     * @param expression a string to be checked.
     * @return True if the delimiters are paired correctly  
     */    
    public static boolean checkBalance(String expression)
    {
        // LINKED STACK
        StackInterface<Character> openDelimiterStack = new LinkedStack<>();
        int characterCount = expression.length();
        boolean isBalanced = true;
        int index = 0;
        char nextCharacter = ' ';
        
        while(isBalanced && (index < characterCount))
        {
            nextCharacter = expression.charAt(index);

            switch(nextCharacter)
            {
                case '(': case '[': case '{':
                    openDelimiterStack.push(nextCharacter);
                    break;
                case ')': case ']': case '}':
                    if(openDelimiterStack.isEmpty())
                    {
                        isBalanced = false;
                    }
                    else
                    {
                        char openDelimiter = openDelimiterStack.pop();
                        isBalanced = isPaired(openDelimiter, nextCharacter);
                    } // end if
                    break;
                default: break; // ignore unexpected characters 
            } // end switch
            ++index;
        } // end while

        if(!openDelimiterStack.isEmpty())
        {
            isBalanced = false;
        }
        return isBalanced;
    } // end checkBalance

    // Return true if the given characters, open and close, 
    // form a pair of parenthesis, brackets, or braces.
    private static boolean isPaired(char open, char close)
    {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') || 
               (open == '{' && close == '}');
    } // end isPaired

    // -----------------------------------(SECOND METHOD) -----------------------------------

  /**
     * Converts an infix expression to an equivalent postfix expression.
     * @param infix the string to be converted to a postfix expression.
     * @return the postfix expression to the client.
     */
    public static String convertToPostfix(String infix)
    {
        // LINKED STACK
        StackInterface<Character> operatorStack = new LinkedStack<>();
        String postfix = new String();
        int index = 0;
        char topOperator;

        while(index < infix.length())
        {
            char nextCharacter = ' ';

            if(infix.charAt(index) == ' ')
            {
                ++index;
                continue;
            }
            if(infix.charAt(index) != ' ') // if character does not equal a space 
            {
                nextCharacter = infix.charAt(index);
            }
            if(Character.isLetter(nextCharacter))
            {
                postfix += nextCharacter;
                ++index;
                continue;
            }
            switch(nextCharacter)
            {
                case '^': 
                    operatorStack.push(nextCharacter);
                    break;
                case '+': case '-': case '*': case '/':
                    // while the stack is not empty
                    // and precedence of nextCharacter <= 
                    // precedence of operator.Stack.peek()
                    while(!operatorStack.isEmpty() && (getPrecedence(nextCharacter) <= getPrecedence(operatorStack.peek()))) 
                    {
                        postfix += operatorStack.peek();
                        operatorStack.pop();
                    }
                    operatorStack.push(nextCharacter);
                    break;
                case '(': 
                    operatorStack.push(nextCharacter);
                    break;
                case ')': // Stack is not empty if infix expression is valid
                    topOperator = operatorStack.pop();
                    while(topOperator != '(')
                    {
                        // Append topOperator to postfix 
                        postfix += topOperator;
                        topOperator = operatorStack.pop();
                    }
                    break;
                default: break; // Ignore unexpected characters 
            } // end switch
            ++index;
        } // end while

        while(!operatorStack.isEmpty())
        {
            topOperator = operatorStack.pop();
            // Append topOperator to postfix
            postfix += topOperator;
        }
        return postfix;
    } // end convertToPostfix

    private static int getPrecedence(char operand)
    {
        switch(operand)
        {
            case '^':
                return 3;
            case '*': case '/':
                return 2;
            case '+': case '-':
                return 1;
        }

        return -1;
    } // end getPrecedence    
    // -----------------------------------(THIRD METHOD) -----------------------------------
    /**
     * This method will evaluate a postfix expression 
     * and return the expected value.
     * @param expression The postfix expression.
     * @return The value of the evaluated postfix expression.
     */
    public static int evaluatePostfix(String expression)
    {
        // Evaluates a postfix expression. 
        // RESIZABLEARRAYSTACK
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
}