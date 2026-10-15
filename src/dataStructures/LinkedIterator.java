package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * Implementation of an Iterator for Linked List
 * Spacial complexity: 0(1) because it only stores the refence of the element to be returned.
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */

class LinkedIterator<E>  implements Iterator<E> {
    /**
     * First node of the list.
     */
    private final LinkedNode<E> first;
    /**
     * Node with the next element in the iteration.
     */
    LinkedNode<E> nextToReturn;

    /**
     * SinglyIterator constructor
     * @param first - Node with the first element of the iteration
     */
    public LinkedIterator(LinkedNode<E> first) {
        this.first=first;
        nextToReturn=first;
    }

    @Override
    /**
     * Returns true if next would return an element
     * Time complexity: O(1) because we only have to compare the variable.
     */
    public boolean hasNext() {
        return nextToReturn != null;
    }

    @Override
    /**
     * Return next element to be returned.
     * Time complexity: O(1) because we only have to assign new values to variables and return next element
     */
    public E next() throws NoSuchElementException {
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        E element = nextToReturn.getElement();
        nextToReturn = nextToReturn.getNext();
        return element;
    }

    @Override
    /**
     * Sets iterator to the beginning.
     * Time complexity: O(1) because we only have to change a variable.
     */
    public void rewind() {
        nextToReturn = first;
    }

}
