package dataStructures;

import dataStructures.exceptions.*;

/**
 *
 *  Sequence Linked List
 *  Space Complexity of Structure:
 *  *   - Best case: O(1) - Stores fixed references (head, tail, currentSize).
 *  *   - Worse case: O(n) - Storing n elements requires linear memory for node allocation.
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
abstract class SequenceLinkedList<E> extends LinkedList<E> implements List<E>{
    /**
     * Constructor of an empty singly linked list.
     * Time Complexity:
     *- Best Case: O(1) - Calls super() to initialize head, tail, and size to default values.
     *- Worst Case: O(1) - Constant time initialization.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public SequenceLinkedList(){
        super();
    }
    /**
     * Returns the first element of the list.
     *Time Complexity:
     *- Best Case: O(1) - accesses head node directly.
     *- Worst Case: O(1) - Performs a constant time reference lookup.
     * @return first element in the list
     * @throws NoSuchElementException - if size() == 0
     */
    public E getFirst() {
        if ( this.isEmpty() )
            throw new NoSuchElementException();
        return head.getElement();
    }
    /**
     * Returns the last element of the list.
     *Time Complexity:
     *- Best Case: O(1) -accesses tail node directly.
     *- Worst Case: O(1) - Constant time reference lookup.
     * @return last element in the list
     * @throws NoSuchElementException - if size() == 0
     */

    public E getLast() {
        if ( this.isEmpty() )
            throw new NoSuchElementException();
        return tail.getElement();
    }
    /**
     * Returns the element at the specified position in the list.
     * Range of valid positions: 0, ..., size()-1.
     * If the specified position is 0, it corresponds to getFirst.
     * If the specified position is size()-1, get corresponds to getLast.
     *Time Complexity:
     *- Best Case: O(1) - Target position is 0 (first) or currentSize - 1 (last).
     *- Worst Case: O(n) - Target position is in the middle.
     * @param position - position of the element to be returned
     * @return element at position
     * @throws InvalidPositionException if position is not valid in the list
     */

    public E get(int position) {
        if ( position < 0 || position >= currentSize )
            throw new InvalidPositionException();
        if (position == 0)
            return getFirst();
        if (position == currentSize-1)
            return getLast();
        return getNode(position).getElement();
    }

    /**
     * Return the node in the given position
     *Time Complexity:
     *- Best Case: O(1) - Position is 0.
     *- Worst Case: O(n) - Traverses nodes sequentially up to position n.
     * @param position
     * @return
     */
    LinkedNode<E> getNode(int position) {
        LinkedNode<E> node = head;
        for ( int i = 0; i < position; i++)
            node = node.getNext();
        return node;
    }
    /**
     * Returns the position of the specified element in the list
     * if the list contains the element.
     * Otherwise, returns -1.
     *Time Complexity:
     *- Best Case: O(1) - Target element is found at the head of the list.
     *- Worst Case: O(n) - Target element is at the tail or not present in the list of n elements.
     * @param element - element to be searched in the list
     * @return position of the element in the list (or -1)
     */
    public int indexOf(E element) {
        LinkedNode<E> node = head;
        int index = 0;
        while (node != null){
            if (node.getElement().equals(element)) {
                return index;
            }
            node = node.getNext();
            index++;
        }

        return -1;
    }

    /**
     * Inserts the specified element at the first position in the list.
     *
     * @param element to be inserted
     */
    public abstract void addFirst(E element);

    /**
     * Inserts the specified element at the last position in the list.
     *
     * @param element to be inserted
     */
    public abstract void addLast(E element) ;

    /**
     * Inserts the specified element at the specified position in the list.
     * Range of valid positions: 0, ..., size().
     * If the specified position is 0, add corresponds to addFirst.
     * If the specified position is size(), add corresponds to addLast.
     *
     *Time Complexity:
     *- Best Case: O(1) - Position is 0 (addFirst) or currentSize (addLast).
     *- Worst Case: O(n) - Insertion occurs at middle position, requiring traversal via addMiddle.
     * @param position - position where to insert the element
     * @param element  - element to be inserted
     * @throws InvalidPositionException - if position is not valid in the list
     */

    public void add(int position, E element) {
        if ( position < 0 || position > currentSize )
            throw new InvalidPositionException();
        if ( position == 0 )
            addFirst(element);
        else if ( position == currentSize )
            addLast(element);
        else
            addMiddle(position, element);
    }
    /**
     *
     * @param position
     * @param element
     */
    abstract void addMiddle(int position, E element);


    /**
     * Returns the previous node, and the node on this position
     * Time Complexity:
     *- Best Case: O(1) - Position is 1, retrieving node 0 and node 1.
     *- Worst Case: O(n) - Position is near the tail, requiring traversal up to position - 1.
     * @param position
     * @pre position > 0 && position < size()
     * @return the last node
     */
    pairNode<E> getNodes(int position){
        LinkedNode<E> prevNode = this.getNode(position - 1);
        return new pairNode<>(prevNode,prevNode.getNext());
    }
    /**
     * Removes and returns the element at the first position in the list.
     *Time Complexity:
     *- Best Case: O(1) - Delegates to super.removeFirstNode() in constant time.
     *- Worst Case: O(1) - Removes first element in constant time.
     * @return element removed from the first position of the list
     * @throws NoSuchElementException - if size() == 0
     */
    public E removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return super.removeFirstNode();
    }

    /**
     * Removes and returns the element at the last position in the list.
     ** Time Complexity:
     *- Best Case: O(1) - List has size 1 and delegates to removeFirst().
     *- Worst Case: O(n) - Requires traversal to find the node preceding tail.
     * @return element removed from the last position of the list
     * @throws NoSuchElementException - if size() == 0
     */
    public E removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        if ( size() == 1 )
            return removeFirst();
        pairNode<E> pair= this.getNodes(size() - 1);
        return super.removeLastNode(pair);
    }
    /**
     * Removes and returns the element at the specified position in the list.
     * Range of valid positions: 0, ..., size()-1.
     * If the specified position is 0, remove corresponds to removeFirst.
     * If the specified position is size()-1, remove corresponds to removeLast.
     *
     * Time Complexity:
     *- Best Case: O(1) - Position is 0 (removeFirst).
     *- Worst Case: O(n) - Position is in the middle or last.
     * @param position - position of the element to be removed
     * @return element removed at position
     * @throws InvalidPositionException - if position is not valid in the list
     */
    @Override
    public E remove(int position) {
        if ( position < 0 || position >= currentSize )
            throw new InvalidPositionException();
        if ( position == 0 )
            return removeFirst();
        if ( position == currentSize - 1 )
            return removeLast();
        return removeMiddle(position);
    }
    /**
     * Time Complexity:
     *- Best Case: O(1) - Target position is near the start.
     *- Worst Case: O(n) - Requires traversal to reach the middle position.
     * Remove in the middle of the list
     */
    E removeMiddle(int position) {
        return removeMiddleNode(position).node().getElement();
    }
    /**
     * Time Complexity:
     *- Best Case: O(1) - Target position is near the start.
     *- Worst Case: O(n) - Requires traversal to reach the middle position.
     * Return the previous and the node in the given position
     */
    pairNode<E> removeMiddleNode(int position) {
        pairNode<E> pair=getNodes(position);
        super.removeMiddleNode(pair);
        return pair;
    }

    /** Add element.
     * Time Complexity:
     *- Best Case: O(1) - Instantiates a new SinglyListNode and delegates to super.addLastNode().
     *- Worst Case: O(1) - Constant time insertion at tail.
     * @param element
     */
    void addElem(E element){
        LinkedNode<E> newNode = new SinglyListNode<>(element,(null));
        super.addLastNode(newNode);
    }
}
