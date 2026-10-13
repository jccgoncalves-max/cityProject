package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
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
     * Constructor of an empty sorted singly linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public SortedLinkedList(Comparator<E> comparator) {
        super();
        this.comparator = comparator;
    }

    /**
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
     *
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

    @Override
    void addElem(E element) {
        addLast(element);
    }

    /**
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
     * Inserts the element at the first position in the list.
     * @param element - Element to be inserted
     */
    void addFirst( E element ) {
        LinkedNode<E> newNode = new SinglyListNode<>(element);
        super.addFirstNode(newNode);
    }

    /**
     * Inserts the element at the last position in the list.
     * @param element - Element to be inserted
     */
    void addLast( E element ) {
        LinkedNode<E> newNode = new SinglyListNode<>(element,null);
        super.addLastNode(newNode);
    }

    /**
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


    void writeData(ObjectOutputStream oos) throws IOException {//Utilizado para escrever uma variável específica ou informação de uma subclasse
        oos.defaultWriteObject(); // write the normal attributes
    }
    void readData(ObjectInputStream ois) throws IOException, ClassNotFoundException {//Utilizado para ler uma variável específica  ou  informação da uma subclasse
        ois.defaultReadObject(); // read the normal attributes
    }
}
