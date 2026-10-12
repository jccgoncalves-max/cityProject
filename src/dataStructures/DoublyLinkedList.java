package dataStructures;
import dataStructures.exceptions.NoSuchElementException;


/**
 *  Doubly Linked List
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
public class DoublyLinkedList<E> extends SequenceLinkedList<E> implements TwoWayList<E> {

    /**
     * Constructor of an empty double linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public DoublyLinkedList( ) {
        super();
    }


    /**
     * Returns a two-way iterator of the elements in the list.
     *
     * @return Two-Way Iterator of the elements in the list
     */
    public TwoWayIterator<E> twoWayiterator() {
        return new TwoWayLinkedIterator<>(head, tail);
    }
    /**
     * Inserts the element at the first position in the list.
     * @param element - Element to be inserted
     */
    public void addFirst( E element ) {

        DoublyListNode<E> newNode = new DoublyListNode<>(element,null,(DoublyListNode<E>) head);
        if (!isEmpty()){
            ((DoublyListNode<E>) head).setPrevious(newNode);
        }else{
            tail = newNode;
        }
        head = newNode;
        currentSize++;
    }
    /**
     * Inserts the element at the last position in the list.
     * @param element - Element to be inserted
     */
    public void addLast( E element ) {
        DoublyListNode<E> newNode = new DoublyListNode<>(element,(DoublyListNode<E>) tail,null);
        if (!isEmpty()){
            tail.setNext(newNode);
        }else{
            head = newNode;
        }
        tail = newNode;
        currentSize++;
    }
    /**
     * Inserts the specified element at the specified position in the list.
     * Pre-condition: position ranges from 1 to currentSize-1.
     * @param position - middle position for insertion of element
     * @param element - element to be inserted at middle position
     */
    void addMiddle( int position, E element ) {
        DoublyListNode<E> prevNode = (DoublyListNode<E>)head;
        for (int i = 0; i < position - 1; i++) {
            prevNode = (DoublyListNode<E>) prevNode.getNext();
        }
        DoublyListNode<E> nextNode = (DoublyListNode<E>) prevNode.getNext();
        DoublyListNode<E> newNode = new DoublyListNode<>(element, prevNode, nextNode);

        prevNode.setNext(newNode);
        nextNode.setPrevious(newNode);
        currentSize++;
    }

    /**
     * Removes and returns the element at the first position in the list.
     * @return element removed from the first position of the list
     * @throws NoSuchElementException - if size() == 0
     */
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E element = head.getElement();
        head = head.getNext();
        if (head != null){
            ((DoublyListNode<E>)head).setPrevious(null);
        }else{
            tail = null;
        }
        currentSize --;
        return element;
    }

    /**
     * Removes the node from the specified position of the list.
     * Pre-condition: the position is neither 0 nor the currentSize of the list.
     * @param position - position of node to be removed
     * @return element removed from the first position of the list
     */
    E removeMiddle( int position ) {
        DoublyListNode<E> target;
        if (position < currentSize/2){
            target = (DoublyListNode<E>) head;
            for (int i = 0; i < position; i++){
                target = (DoublyListNode<E>) target.getNext();
            }
        }else{
            target = (DoublyListNode<E>) tail;
            for (int i = currentSize-1; i > position; i--){
                target = (DoublyListNode<E>) target.getPrevious();
            }
        }
        E element = target.getElement();

        DoublyListNode<E> prevNode = (DoublyListNode<E>) target.getPrevious();
        DoublyListNode<E> nextNode = (DoublyListNode<E>) target.getNext();

        prevNode.setNext(nextNode);
        nextNode.setPrevious(prevNode);

        currentSize --;
        return element;
    }

    pairNode<E> getNodes(int position){
        LinkedNode<E> prev = null;
        LinkedNode<E> current = (LinkedNode<E>)head;

        for (int i  = 0; i < position; i++){
            prev = (LinkedNode<E>) current;
            current = ((LinkedNode<E>) current).getNext();
        }

        return new pairNode<>(prev,current);
    }
}
