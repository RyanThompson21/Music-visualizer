package prj5;

/***
 * @author David Barto (depbarto)
 * @author Tom Laier (toml362)
 * @author Ryan Thompson (ryanjt5)
 * @version 11.28.18
 * @param <T>
 *            type of data the nodes will store
 */
public class Node<T> {
    private T data;
    private Node<T> nextNode;


    /**
     * creates a new node with given data
     * 
     * @param inData
     *            data node will store
     * @param next
     *            where the node will be
     */
    public Node(T inData, Node<T> next) {
        data = inData;
        nextNode = next;
    }


    /**
     * Gives the node new data
     * 
     * @param data2
     *            new data
     */
    public void setData(T data2) {
        data = data2;
    }


    /**
     * gets the next node
     * 
     * @return the next node
     */
    public Node<T> getNext() {
        return nextNode;
    }


    /**
     * Places the node before a given node
     * 
     * @param newNext
     *            given node
     */
    public void setNext(Node<T> newNext) {
        nextNode = newNext;
    }


    /**
     * gets the data stored in the node
     * 
     * @return data in the node
     */
    public T getData() {
        return data;
    }


    /**
     * Determines whether this node is equal to a given object
     * two nodes are equal if they store the same data
     * 
     * @param obj
     *            object in
     *            question
     * @return true if the two are the same
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (obj.getClass() != this.getClass()) {
            return false;
        }
        else {
            return ((Node<T>)obj).getData().equals(this.getData());
        }
    }
}
