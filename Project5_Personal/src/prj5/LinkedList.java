package prj5;

import java.util.Comparator;
import java.util.Iterator;

/**
 * @author David Barto (depbarto)
 * @author Tom Laier (toml362)
 * @author Ryan Thompson (ryanjt5)
 * @version 11.28.18
 * 
 * @param <E>
 */
public class LinkedList<E> implements Iterable<E> {
    private Node<E> head;
    private int size;


    /**
     * Creates a new LinkedList with a null head and size 0
     */
    public LinkedList() {
        head = null;
        size = 0;
    }


    /**
     * adds a new node at the head with given data
     * 
     * @param newData
     *            given data
     * @return true if the node is added successfully
     */
    public boolean add(E newData) {
        if (newData == null) {
            return false;
        }
        Node<E> newHead = new Node<E>(newData, head);
        head = newHead;
        size++;
        return true;
    }


    /**
     * Gets the node at the given index
     * 
     * @param index
     *            given index
     * @return the node at the index
     */
    public E get(int index) {
        Iterator<E> thisIterator = this.iterator();
        E element = head.getData();
        for (int x = 0; x <= index; x++) {
            if (x == index) {
                element = thisIterator.next();
            }
            thisIterator.next();
        }
        return element;
    }


    /**
     * creates a new iterator
     * 
     * @return returns the iterator
     */
    public Iterator<E> iterator() {
        return new LinkedIterator(this);
    }


    /**
     * getter for the size of the list
     * 
     * @return the amount of elements in the list
     */
    public int size() {
        return size;
    }


    /**
     * 
     * 
     * @param compare
     *            thing to be sorted
     */
    public void sort(Comparator<? super E> compare) {
        // Intentionally left blank
    }


    /**
     * @author David Barto (depbarto)
     * @author Tom Laier (toml362)
     * @author Ryan Thompson (ryanjt5)
     * @version 11.28.18
     * 
     * @param <E>
     *            type of data the iterator is going through
     */
    private class LinkedIterator implements Iterator<E> {
        private Node<E> currentNode;


        /**
         * creates a new iterator for a given list
         * 
         * @param thisList
         *            the list that the iterator is used for
         */
        public LinkedIterator(LinkedList<E> thisList) {
            currentNode = thisList.head;
        }


        /**
         * whether there is a next element
         * 
         * @return true if there exits another element
         */
        @Override
        public boolean hasNext() {
            return currentNode.getNext() != null;
        }


        /**
         * gets the next element in the list
         * 
         * @return the next element
         */
        @Override
        public E next() {
            E tempData = currentNode.getData();
            if (hasNext()) {
                currentNode = currentNode.getNext();
            }
            return tempData;
        }

    }
}
