package Chapter_6.atHome;

import java.util.EmptyStackException;

/**
 * A class of stacks whose entries are stored in a chain of nodes.
 */
public final class LinkedStack<T> implements StackInterface<T> 
{
    private Node topNode; // References the first node in the chain

    public LinkedStack()
    {
        topNode = null;
    } // end default constructor

    // <Implementations of the stack go here.>

    public void push(T newEntry)
    {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    } // end push

    public T peek()
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return topNode.getData();
        } // end if
    } // end peek

    public T pop()
    {
        T top = peek(); // Might throw EmptyStackException
        // Assertion: topNode != null
        topNode = topNode.getNextNode();
        
        return top;
    } // end pop

    @Override
    public boolean isEmpty() 
    {
        return topNode == null;
    } // end isEmpty

    @Override
    public void clear() 
    {
        topNode = null;
    } // end clear

    private class Node
    {
        private T    data; // Entry in stack
        private Node next; // Link to next node

        public Node(T dataPortion)
        {
           this(dataPortion, null);
        } // end constructor

        public Node(T dataPortion, Node nextNode)
        {
            data = dataPortion; // The data or object to be contained within the node 
            next = nextNode; // Links to the next node in the chain 

            // Note: we could also use the following
            // topNode = new Node(dataPortion, nextNode);
        } // end constructor

        private T getData()
        {
            return data;
        } // end getData

        private void setData(T newData)
        {
            data = newData;
        } // end setData

        private Node getNextNode()
        {
            return next;
        }

        private void setNextNode(Node nextNode)
        {
            next = nextNode; // set instance variable next to the next node in the chain 
        }
    } // end Node
} // end LinkedStack