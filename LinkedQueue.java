import java.util.Iterator;
import java.util.NoSuchElementException;
import javafx.collections.ModifiableObservableListBase;

/**
* @author Gabriel Wang
* @version 1.5
* @param <E> generic data type
* @since 11/26/18
**/
public class LinkedQueue<E> extends ModifiableObservableListBase<E>
    implements Iterable<E>, SimpleQueue<E> {

    private int size = 0;
    private LinkedQueueNode<E> head = null;

    /**
    * Returns the data at the indexed node
    * @param index integer
    * @return E generic data type
    * @exception IndexOutOfBoundsException not a valid index
    **/
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        LinkedQueueNode<E> temp = head;
        for (int i = 0; i < index && temp.getNext() != null; i++) {
            temp = temp.getNext();
        }
        return temp.getData();
    }

    /**
    * Adds a new node with data at the specified index
    * @param index integer
    * @param element generic data in node
    * @exception IndexOutOfBoundsException not a valid index
    **/
    @Override
    public void doAdd(int index, E element) {

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (head == null) {
            head = new LinkedQueueNode(element);
        }

        LinkedQueueNode<E> temp = head;
        LinkedQueueNode<E> placeholder;

        for (int i = 0; i < index - 1 && temp.getNext() != null; i++) {
            temp = temp.getNext();
        }
        temp.setNext(new LinkedQueueNode<E>(element));
        size++;
    }

    /**
    * Removes the node at the index and returns the data
    * @param index integer
    * @return E generic data from removed node
    * @exception IndexOutOfBoundsException not a valid index
    **/
    @Override
    public E doRemove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        LinkedQueueNode<E> removed;

        if (index == 0) {
            removed = head;
            head = head.getNext();
            size--;
        } else {
            LinkedQueueNode<E> temp = head;
            for (int i = 0; i < index - 1 && temp.getNext() != null; i++) {
                temp = temp.getNext();
            }
            removed = temp.getNext();
            temp.setNext(removed.getNext());
            size--;
        }
        return removed.getData();
    }
    /**
    * Functionality not included in assignment
    * @param index integer
    * @param element generic data type
    * @exception UnsupportedOperationException not in assignment
    **/
    @Override
    public E doSet(int index, E element) {
        throw new UnsupportedOperationException();
    }
    /**
    * Adds a new node to the end
    * @param element generic data type
    **/
    @Override
    public void enqueue(E element) {
        super.add(size, element);
    }
    /**
    * Removes the first node and returns the data
    * @return E generic data type
    **/
    @Override
    public E dequeue() {
        if (size == 0) {
            return null;
        }
        return super.remove(0);
    }
    /**
    * Returns the size of the linked list
    * @return size integer
    **/
    @Override
    public int size() {
        return size;
    }
    /**
    * Sets the linked list to null and size to zero
    **/
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            head = null;
        }
        size = 0;
    }
    /**
    * Checks if list is empty
    * @return true or false if size is zero
    **/
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    /**
    * Returns iterator for the linked queue
    * @return LinkedQueueIterator
    **/
    @Override
    public Iterator<E> iterator() {
        return new LinkedQueueIterator();
    }
    /**
    * Private inner class for new iterator
    * @author Gabriel Wang
    * @version 1.5
    * @since 11/26/18
    **/
    private class LinkedQueueIterator<E> implements Iterator<Integer> {
        private int cursor;

        /**
        * No args constructor for the iterator
        **/
        public LinkedQueueIterator() {
            this.cursor = 0;
        }
        /**
        * Checks if there is another element in list
        * @return true or false is there is another element
        **/
        @Override
        public boolean hasNext() {
            return this.cursor < LinkedQueue.this.size;
        }
        /**
        * Returns the next index in the list
        * @return integer
        * @exception NoSuchElementException no more elements in list
        **/
        @Override
        public Integer next() {
            if (this.hasNext()) {
                int current = cursor;
                cursor++;
                return current;
            }
            throw new NoSuchElementException();
        }
    }
}









