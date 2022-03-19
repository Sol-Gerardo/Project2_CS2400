package Chapter_6.atHome;

/**
 * This class will obtain an infix expression
 * and convert it to its respected postfix expression.
 * Once the conversion is complete we return the postfix 
 * expression to the client using the convertToPostfix
 * static method.
 */
public class ConvertToPostfix 
{
    /**
     * Converts an infix expression to an equivalent postfix expression.
     * @param infix the string to be converted to a postfix expression.
     * @return the postfix expression to the client.
     */
    public static String convertToPostfix(String infix)
    {
        StackInterface<Character> operatorStack = new LinkedStack<>();
        String postfix = new String();
        int index = 0;
        char topOperator;

        while(index < infix.length() - 1)
        {
            char nextCharacter;

            if(infix.charAt(index) != ' ') // if character does not equal a space 
            {
                nextCharacter = infix.charAt(index);
            }
            else
            {
                continue;
            }
            if(Character.isLetter(nextCharacter))
            {
                postfix += nextCharacter;
                break;
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
                    while(!operatorStack.isEmpty() && (nextCharacter <= operatorStack.peek())) 
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
        } // end while

        while(!operatorStack.isEmpty())
        {
            topOperator = operatorStack.pop();
            // Append topOperator to postfix
            postfix += topOperator;
        }
        return postfix;
    } // end convertToPostfix
} // end ConvertToPostfix