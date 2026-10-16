package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Space Complexity of Structure:
 *  - Worse case: O(n) - Stores n nodes in dynamic memory.
 * Sorted linked list Implementation
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class SortedLinkedList<E> extends LinkedList<E> implements SortedList<E> {
    /**
     * Comparator of elements.
     */
    private final Comparator<E> comparator;
    /**
     * Time Complexity:
     *- Best Case: O(1) - Constant time initialization of structure attributes.
     *- Worst Case: O(1) - Constant time initialization.
     * Constructor of an empty sorted singly linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public SortedLinkedList(Comparator<E> comparator) {
        super();
        this.comparator = comparator;
    }

    /**
     * Time complexity:
     * Best case:0(1) - Accesses head directly in constant time.
     * Worse case:O(1) - Accesses head directly in constant time.
     * Returns the first element of the list.
     * @return first element in the list
     * @throws NoSuchElementException - if size() == 0
     */
    public E getMin( ) {

        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    /**
     * Time complexity:
     * Best case:O(1) - Accesses tail directly in constant time.
     * Worse case:O(1) - Accesses tail directly in constant time.
     * Returns the last element of the list.
     * @return last element in the list
     * @throws NoSuchElementException - if size() == 0
     */
    public E getMax( ) {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return tail.getElement();

    }
    /**
     * Time complexity:
     * Best case:O(1)-Target element is equal to or smaller than head.
     * Worse case:O(n) -Target element is larger than all elements or at the tail.
     * Returns the first occurrence of the element equals to the given element in the list.
     * @return element in the list or null
     */
    @Override
    public E get(E element) {
        LinkedNode<E> current = head;
        while (current != null){

            int comp = comparator.compare(current.getElement(),element);
                if(comp == 0) return current.getElement();
                if (comp > 0) return null;
                current = current.getNext();

        }

        return null;
    }
    /**
     * Returns true iff the element exists in the list.
     *Time complexity:
     *-Best case:O(1)-Target element is equal to head element.
     *-Worse case:O(n)-Target element is greater than all elements or at the end of the list.
     * @param element to be found
     * @return true iff the element exists in the list.
     */
    public boolean contains(E element) {
        LinkedNode<E> current = head;

        while (current != null){

            int comp = comparator.compare(current.getElement(),element);
            if (comp == 0) return true;
            if (comp > 0)  return false;

            current = current.getNext();
        }

        return false;
    }

    /**
     * Time complexity:
     *-Best case:O(1)- List is empty or new element is smaller than head.
     *-Worse case:O(n)-New element is greater than all existing elements, requiring full traversal.
     * Inserts the specified element at the list, according to the comparator order.
     * If there is an equal element, the new element is inserted after it.
     * @param element to be inserted
     */
    public void add(E element) {

        LinkedNode<E> newNode = new SinglyListNode<>(element,null);


        if(isEmpty()|| comparator.compare(head.getElement(),element) > 0){
            addFirstNode(newNode);
            return;
        }
        LinkedNode<E> prev = null;
        LinkedNode<E> current = head;
        while (current != null && comparator.compare(current.getElement(),element) <= 0 ){
            prev = current;
            current = current.getNext();
        }
        if (current == null) {
            addLastNode(newNode);
        }
        else{
            pairNode<E> pair = new pairNode<>(prev, current);
            addMiddleNode(pair, newNode);
          }
        }

    /**
     * Removes the first occurrence of the specified element from the list.
     * Time complexity:
     * -Best case:O(1)-Target element is at the head of the list.
     * -Worse case:O(n)-Target element is at the tail or not found after full traversal.
     * @param element
     * @return
     */
    @Override
    public E remove(E element) {
        LinkedNode<E> prev = null;
        LinkedNode<E> current = head;


        while (current != null && comparator.compare(current.getElement(), element) < 0) {
            prev = current;
            current = current.getNext();
        }

        if (current == null || comparator.compare(current.getElement(), element) > 0) {
            return null;
        }

        E removedElement = current.getElement();


        if (current == head) {
            return removeFirstNode();
        }

        pairNode<E> pair = new pairNode<>(prev, current);

        if (current == tail) {
            return removeLastNode(pair);
        }

        removeMiddleNode(pair);

        return removedElement;
    }

    /**
     * Helper method to insert an element at the end of the list during deserialization.
     * Time complexity:
     *Best case:O(1)-Directly appends node at tail in constant time.
     *Worse case:O(1)-Constant time insertion at tail
     * @param element
     */
    @Override
    void addElem(E element) {
        addLast(element);
    }

    /**
     * Time complexity:
     *-Best case:O(1)-Direct pointer updates in constant time.
     * -Worse case:O(1)-Direct pointer updates in constant time.
     * Inserts the element before node after.
     * Precondition: after is not the head of the list.
     * @param element - Element to be inserted
     * @param before - Node to be previous to the new node
     */
    void addBeforeNode(E element, LinkedNode<E> before){
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        LinkedNode<E> afterNode = before.getNext();
        before.setNext(newNode);
        newNode.setNext(afterNode);
        currentSize++;
    }
    /**
     * Time complexity:
     *Best case:O(1)-Inserts at head in constant time.
     *Worse case:O(1)-Inserts at head in constant time.
     * Inserts the element at the first position in the list.
     * @param element - Element to be inserted
     */
    void addFirst( E element ) {
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        super.addFirstNode(newNode);
    }

    /**
     *Time complexity:
     *Best case:O(1)-Inserts at tail in constant time.
     *Worse case:O(1)-Inserts at tail in constant time.
     * Inserts the element at the last position in the list.
     * @param element - Element to be inserted
     */
    void addLast( E element ) {
        LinkedNode<E> newNode = new SinglyListNode<>(element,null);
        super.addLastNode(newNode);
    }

    /**
     * Invariant check to verify whether the list elements remain sorted.
     * Time complexity:
     * Best case:O(1)-Returns true immediately if the list is empty or has size 1.
     * Worse case:O(n)-Iterates through all n elements to confirm sorted order.
     *
     * @return
     */
    private boolean invariant() {
        if(isEmpty())
            return true;
        Iterator<E> it = this.iterator();
        boolean ok = true;
        E elemP = it.next();
        while(ok && it.hasNext()){
            E elemN = it.next();
            ok = comparator.compare(elemP,elemN)<=0;
            elemP = elemN;
        }
        return ok;
    }

    /**
     *Writes non-transient attributes of the sorted list to an ObjectOutputStream.
     *  Time Complexity:
     * - Best Case: O(1) - Default object writing operates in constant time for default fields.
     * - Worst Case: O(1) - Constant time attribute output.
     * @param oos
     * @throws IOException
     */
    void writeData(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // write the normal attributes
    }

    /**
     *Reads non-transient attributes of the sorted list from an ObjectInputStream.
     * Time Complexity:
     *- Best Case: O(1) - Default object reading operates in constant time for default fields.
     *- Worst Case: O(1) - Constant time attribute input.
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void readData(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // read the normal attributes
    }
}
