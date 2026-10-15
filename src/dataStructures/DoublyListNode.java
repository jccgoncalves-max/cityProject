package dataStructures;
import java.io.Serializable;

/**
     * Double List Node Implementation
     * Spacial Complexity: O(1) because it only stores references about itself(the node).
     * @author AED  Team
     * @version 1.0
     * @param <E> Generic Element
     * 
     */
class DoublyListNode<E> extends SinglyListNode<E> implements Serializable {
        /**
         * (Pointer to) the previous node.
         */
        private LinkedNode<E> previous;
        /**
         * 
         * @param theElement - The element to be contained in the node
         * @param thePrevious - the previous node
         * @param theNext - the next node
         */
        public DoublyListNode(E theElement, DoublyListNode<E> thePrevious,
                              DoublyListNode<E> theNext ) {
            super(theElement,theNext);
            this.previous=thePrevious;
        }
        /**
         * 
         * @param theElement to be contained in the node
         */
        public DoublyListNode(E theElement ) {
            super(theElement, null);
            this.previous = null;
        }
        /**
         * Time Complexity: O(1) because it only returns the value of a variable.
         * @return the previous node
         */
        public LinkedNode<E> getPrevious() {
            return previous;
        }
        /**
         * Time Complexity: O(1) because it only changes the value of a variable.
         * @param newPrevious - node to replace the current previous node
         */
        public void setPrevious( LinkedNode<E> newPrevious ) {
            this.previous=newPrevious;
        }
    }
