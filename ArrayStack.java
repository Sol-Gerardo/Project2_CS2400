package Chapter_6.atHome;
import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * A class of stacks whose entries are stored in an array.
 */
public final class ArrayStack<T> implements StackInterface<T> 
{
    private T[] stack; // Array of stack entries 
    private int topIndex; // Index of top entry
    private boolean integrityOk = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayStack()
    {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initialCapacity)
    {
        integrityOk = false;
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1; // Indicates an empty stack
        integrityOk = true;
    } // end constructor

    public void push(T newEntry)
    {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        ++topIndex;
    } // end push

    public T pop()
    {
        checkIntegrity();
        if(isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            T top = stack[topIndex];
            stack[topIndex] = null;
            --topIndex;

            return top;
        } // end if 
    } // end pop

    @Override
    public T peek() 
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return stack[topIndex]; 
        }   
    } // end peek

    public boolean isEmpty()
    {
        return topIndex < 0;
    } // end isEmpty

    public void clear()
    {
        while(!isEmpty())
        {
            pop();
        }
        topIndex = -1;
    }

    private void ensureCapacity()
    {
        if(topIndex == stack.length - 1)
        { // If array is full, double its size
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength); // Extends the length of the array and copies the original objects into new array
        } // end if
    } // end ensureCapacity

    // Throws an exception if the client requests a capacity that is too large
    public void checkCapacity(int capacity)
    {
        if(capacity > MAX_CAPACITY)
        {
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " 
                                          + "allowed maximum of " + MAX_CAPACITY);
        }
    } // end checkCapacity

    // Throws an exception if receiving object is not initialized 
    private void checkIntegrity()
    {
        if(!integrityOk)
        {
            throw new SecurityException("Array object is corrupt.");
        } // end if
    } // end checkIntegrity
} // end ArrayStack