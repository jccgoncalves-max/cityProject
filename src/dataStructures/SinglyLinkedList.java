package dataStructures;

/**
 * Implementation of Singly Linked List
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
public class SinglyLinkedList<E> extends SequenceLinkedList<E> {

    public SinglyLinkedList( ) {
        super();
    }

    /**
     * Inserts the specified element at the first position in the list.
     *
     * @param element to be inserted
     */
    @Override
    public void addFirst(E element) {

        LinkedNode<E> newNode = new SinglyListNode<>(element,(SinglyListNode<E>) head);
        super.addFirstNode(newNode);
    }

    /**
     * Inserts the specified element at the last position in the list.
     *
     * @param element to be inserted
     */
    @Override
    public void addLast(E element) {
       LinkedNode<E> newNode = new SinglyListNode<>(element,null);
       super.addLastNode(newNode);
    }

    void addMiddle(int position, E element) {

        pairNode<E> pair = super.getNodes(position);
        LinkedNode<E> newNode = new SinglyListNode<>(element,(SinglyListNode<E>)pair.node());
        super.addMiddleNode(pair,newNode);
    }
}
