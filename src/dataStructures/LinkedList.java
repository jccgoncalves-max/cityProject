package dataStructures;

import java.io.*;

/**
 * Linked List
 * Spacial Complexity: 0(n) because it stores n elements in memory
 * Includes description of general methods to be implemented by linked lists.
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
abstract class LinkedList<E> implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    /**
     *  Node at the head of the list.
     */
    transient LinkedNode<E> head;
    /**
     * Node at the tail of the list.
     */
    transient LinkedNode<E> tail;
    /**
     * Number of elements in the list.
     */
    transient int currentSize;
    /**
     * Constructor of an empty singly linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public LinkedList(){
        head=null;
        tail=null;
        currentSize=0;
    }
    /**
     * Returns true iff the list contains no elements.
     * Time Complexity: O(1) because i only need to check current size.
     * @return true if the list is empty
     */
    public boolean isEmpty() {

        return currentSize == 0;
    }
    /**
     * Returns the number of elements in the list.
     * Time Complexity: O(1) because i only have to return a variable.
     * @return number of elements in the list
     */
    public int size() {

        return currentSize;
    }

    /**
     * Returns an iterator of the elements in the list (in a proper sequence).
     * Time Complexity: O(1) because creating and returning an iterator is constant.
     * @return Iterator of the elements in the list
     */
    public Iterator<E> iterator() {
        return new LinkedIterator<>(head);
    }

    /**
     * Insert a node on the head of list
     * Time complexity: O(1) because we only have to assign a different value to references.
     * @param newNode node to be added
     */
    void addFirstNode(LinkedNode<E> newNode){

        newNode.setNext(head);
        head = newNode;
        if (isEmpty()){
            tail = newNode;
        }
        currentSize++;

    }
    /**
     * Insert a node on the tail of list
     * Time complexity: O(1) because we only have to assign a different value to references.
     * @param newNode Node to be added
     */
    void addLastNode(LinkedNode<E> newNode){
        if(isEmpty()){
            head = newNode;

        }else {
            tail.setNext(newNode);
        }
        tail = newNode;
        currentSize++;
    }
    /**
     * Record with two nodes (prev, node)
     * Time Complexity: O(1) because it only returns a pair of nodes.
     * @param prev Previous node
     * @param node Current node
     */
    record pairNode<E>(LinkedNode<E> prev, LinkedNode<E> node){}

    /**
     * Insert node (newNode) between pair.previous() and pair.node()
     * Time complexity: O(1) because we only have to assign a different value to references.
     * @pre: pair.previous()!=null && pair.node()!=null
     * @param newNode
     */
    void addMiddleNode(pairNode<E> pair,LinkedNode<E> newNode){

        newNode.setNext(pair.node());

        pair.prev().setNext(newNode);
        currentSize++;
    }
    /**
     * Removes the first node in the list.
     * Time complexity: O(1) because we only have to remove an element and return it.
     * @pre: !isEmpty()
     * @return removed element
     */
    E removeFirstNode(){
        E element = head.getElement();
        head = head.getNext();
        currentSize --;
        if (isEmpty()){
            tail= null;
        }
        return element;
    }

    /**
     * remove the last node (pair.node()) of the list
     * Time complexity: O(1) because we only have to remove an element and return it.
     * @return removed element
     */
    E removeLastNode(pairNode<E> pair){

        E element = pair.node().getElement();
        tail = pair.prev();
        tail.setNext(null);
        currentSize--;
        return element;
    }
    /**
     * remove the node pair.node()
     * Time complexity: O(1) because we only have to remove an element and return it.
     * @pre: pair.previous()!=null && pair.node()!=null
     * @param pair pair of nodes
     */
    void removeMiddleNode(pairNode<E> pair) {

        pair.prev().setNext(pair.node().getNext());

        currentSize--;
    }

    /**
     * return pair of nodes of an element
     * Time complexity best case: O(1) because the pair might be in position 1
     * Time complexity worst case: O(n) because the pair might be in the last position and have to go over n nodes.
     * @param element
     * @return pair with the previous node and the element node, Or null if no element
     */
    pairNode<E>  nodeOf(E element){

        LinkedNode<E> prev = null;
        LinkedNode<E> node = head;
        while (node != null) {
            if (node.getElement().equals(element)) {
                return new pairNode<>(prev, node);
            }
            prev = node;
            node = node.getNext();
        }

        return null;
    }

    /**
     * Returns the first node
     * Time complexity: O(1) because we only need to return a node.
     * @return first node
     */
    LinkedNode<E> getFirstNode(){
        return head;
    }

    /**
     * Returns the last node
     * Time complexity: O(1) because we only need to return a node.
     * @return Last node
     */
    LinkedNode<E> getLastNode(){
        return tail;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        writeData(oos);
        oos.writeInt(currentSize);
        LinkedNode<E> node = head;
        while(node!=null){
            oos.writeObject(node.getElement());
            node = node.getNext();
        }
    }


    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        readData(ois);
        int size = ois.readInt();
        for(int i =0; i<size;i++){
            @SuppressWarnings("unchecked")
            E element = (E) ois.readObject();
            addElem(element);
        }
    }


    void writeData(ObjectOutputStream out) throws IOException{
    }

    void readData(ObjectInputStream in) throws IOException, ClassNotFoundException {
    }

    abstract void addElem(E element);
}
