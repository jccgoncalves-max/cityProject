package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * Implementation of Two Way Iterator for DoublyLinkedList
 * Space Complexity: O(1) because it only stores references of the element to be returned.
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
class TwoWayLinkedIterator<E> extends LinkedIterator<E>
        implements TwoWayIterator<E> {
    /**
     * Node with the last element in the iteration.
     */
    private final LinkedNode<E> lastNode;
    /**
     * Node with the previous element in the iteration.
     */
    private LinkedNode<E> prevToReturn;

    /**
     * Two Way Linked Iterator constructor
     * @param first - Node with the first element of the iteration
     * @param last  - Node with the last element of the iteration
     */
    public TwoWayLinkedIterator(LinkedNode<E> first, LinkedNode<E> last) {
        super(first);
        prevToReturn=null;
        lastNode=last;
    }

    /**
     * Returns true if previous would return an element
     * Time complexity: O(1) because it only compares a variable
     * @return true if previous returns an element
     */
    @Override
    public boolean hasPrevious() {
        return prevToReturn != null;
    }

    /**
     * Return previous element to be returned.
     * Time complexity: O(1) because we only have to assign new values to variables and return previous element
     * @return previous element
     * @throws NoSuchElementException
     */
    @Override
    public E previous() throws NoSuchElementException {
        if (!hasPrevious()){
            throw new NoSuchElementException();
        }

        DoublyListNode<E> nodeToReturn = (DoublyListNode<E>) prevToReturn;
        E element = nodeToReturn.getElement();

        nextToReturn = nodeToReturn;
        prevToReturn = nodeToReturn.getPrevious();

        return element;
    }

    /**
     * Reverts the order of iteration.
     * Time complexity O(1) because it only changes variables.
     */
    @Override
    public void fullForward() {
        nextToReturn = null;
        prevToReturn = lastNode;
    }

    /**
     * Returns true if next would return an element
     * Time complexity: O(1) because we only have to compare the variable.
     * @return true if next returns an element
     */
    @Override
    public boolean hasNext() {
        return nextToReturn != null;
    }

    /**
     * Return next element to be returned.
     * Time complexity: O(1) because we only have to assign new values to variables and return next element
     * @return next element
     * @throws NoSuchElementException
     */
    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        LinkedNode<E> nodeToReturn = nextToReturn;
        E element = nodeToReturn.getElement();

        prevToReturn = nodeToReturn;
        nextToReturn = nodeToReturn.getNext();
        return element;
    }

    /**
     * Restarts the iterator
     * Time complexity: O(1) because it only changes variables
     */
    @Override
    public void rewind() {
        super.rewind();
        prevToReturn = null;

    }



}
