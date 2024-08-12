/*
 * NAME: Madeline Soares
 * PID: A17511156
 */

import java.util.AbstractList;

/**
 * TODO
 * @author TODO
 * @since TODO
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int size;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;

        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            prev = null;
            next = null;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        head = new Node(null);
        tail = new Node(null);

        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(element);
        newNode.setNext(tail);
        newNode.setPrev(tail.prev);
        tail.prev.setNext(newNode);
        tail.setPrev(newNode);
        size++;
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param index the index at which the specified element is to be inserted
     * @param element the element to be inserted
     * @throws NullPointerException if the data argument is null
     * @throws IndexOutOfBoundsException if the index is less than 0 or
     * greater than the size of the list
     */
    @Override
    public void add(int index, T element) {
        if(element == null)
        {
            throw new NullPointerException();
        }
        //Check for valid index
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        T returnData;
        Node temp = head.next;
        for(int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        Node newNode = new Node(element);
        newNode.setPrev(temp.prev);
        newNode.setNext(temp);
        temp.prev.setNext(newNode);
        temp.setPrev(newNode);
        size++;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * TODO: Javadoc comments
     */
    @Override
    public boolean contains(Object element) {
        if (element == null) {
            throw new NullPointerException();
        }
        T data = (T) element;

        Node current = head;
        while(current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index the index of the element to get
     * @return element at the specified index
     * @throws IndexOutOfBoundsException if the index is less than 0 or
     * greater than or equal to the size of the list
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        Node temp = head.next;
        for(int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        return temp.data;
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index the index of the Node to be returned
     * @return the Node at the specified index
     * @throws IndexOutOfBoundsException if the index is less than 0 or
     * greater than or equal to the size of the list
     */
    private Node getNth(int index) {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        Node temp = head.next;
        for(int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * Determine if the list empty
     *
     * @return true if MyLinkedList is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        if (head.next==tail) {
            return true;
        }
        return false;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index index of element to be removed
     * @return the data that was removed
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        Node temp = head.next;
        for(int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        temp.prev.setNext(temp.next);
        temp.next.setPrev(temp.prev);
        size--;
        return temp.data;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index location of element to set
     * @param element data to overwrite the element with
     * @return the data of the element at the given index location
     * @throws NullPointerException if the data is null
     * @throws IndexOutOfBoundsException if the index is less than 0 or
     * greater than or equal to the size of the list
     */
    @Override
    public T set(int index, T element) {
        if(element == null) {
            throw new NullPointerException();
        }
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T returnData;
        Node temp = head.next;
        for(int i = 0; i < index; i++) {
            temp = temp.next;
        }
        returnData = temp.data;
        temp.data = element;
        return returnData;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return int of number of elements stored in MyLinkedList
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * TODO: javadoc comments
     */
    @Override
    public String toString() {
        return null;
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Remove nodes whose index is a multiple of base
     *
     * TODO: javadoc comments
     */
    public void removeMultipleOf(int base) {
        // TODO: complete implementation       
    }

    /**
     * Swap the nodes between index [0, splitIndex] of two lists
     *
     * TODO: javadoc comments
     */
    public void swapSegment(DoublyLinkedList<T> other, int splitIndex) {
        // TODO: complete implementation
    }

}
