package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * Implementation of Two Way Iterator for DLList 
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
     * DoublyLLIterator constructor
     *
     * @param first - Node with the first element of the iteration
     * @param last  - Node with the last element of the iteration
     */
    public TwoWayLinkedIterator(LinkedNode<E> first, LinkedNode<E> last) {
        super(first);
        prevToReturn=null;
        lastNode=last;
    }

    @Override
    public boolean hasPrevious() {
        return prevToReturn != null;
    }

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

    @Override
    public void fullForward() {
        nextToReturn = null;
        prevToReturn = lastNode;
    }

    @Override
    public boolean hasNext() {
        return nextToReturn != null;
    }

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

    @Override
    public void rewind() {
        super.rewind();
        prevToReturn = null;

    }



}
