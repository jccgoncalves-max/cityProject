package dataStructures;
import java.io.Serial;
import java.io.Serializable;

/**
 * Singly List Node Implementation
 * Space Complexity: O(1) because it only stores information about itself(next,element)
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
class SinglyListNode<E> implements LinkedNode<E> {

    /**
     * Element stored in the node.
     */
    private E element;

    /**
     * (Pointer to) the next node.
     */
    private LinkedNode<E> next;

    /**
     * Singly list constructor
     * @param theElement - The element to be contained in the node
     * @param theNext - the next node
     */
    public SinglyListNode( E theElement, SinglyListNode<E> theNext ) {
        element = theElement;
        next = theNext;
    }

    /**
     * Singly list constructor
     * @param theElement to be contained in the node
     */
    public SinglyListNode( E theElement ) {
        this(theElement, null);
    }

    /**
     * Returns elements stored in the node
     * Time complexity: O(1) because it only returns the value of a variable.
     * @return the element contained in the node
     */
    public E getElement( ) {
        return element;
    }

    /**
     * Returns the next node
     * Time Complexity: O(1) because it only returns the value of a variable
     * @return the next node
     */
    public LinkedNode<E> getNext( ) {
        return next;
    }

    /**
     * Sets a new element in the node
     * Time complexity: O(1) because it only assigns a new element to a variable
     * @param newElement - New element to replace the current element
     */
    public void setElement( E newElement ) {
        element = newElement;
    }

    /**
     * Sets a new next node
     * Time complexity: O(1) because it only assigns a new next to a variable
     * @param newNext - node to replace the next node
     */
    public void setNext( LinkedNode<E> newNext ) {
        next = newNext;
    }
}
