/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size;

    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldFirst = first;

        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst != null) {
            oldFirst.prev = first;
        }

        if (first.next == null && first.prev == null) {
            // this is the first element in deque. both last and first are same.
            last = first;
        }

        // last will not change when adding at the front

        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;

        // set the previous last's next element to current last
        if (oldLast != null && oldLast.next == null) {
            oldLast.next = last;
        }

        if (last.prev == null) {
            // this is the last element in deque. both last and first are same.
            first = last;
        }

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = first.item;

        first = first.next;
        if (first != null) {
            first.prev = null;
        }

        if (size == 1) {
            last = first = null;
        }

        --size;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = last.item;
        last = last.prev;
        if (last != null) {
            // last will be null if this is the only element
            last.next = null;
        }

        if (size == 1) {
            last = first = null;
        }

        --size;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }


    public static void main(String[] args) {
        /*Deque<String> deque = new Deque<>();

        StdOut.println("Is Deque empty: " + deque.isEmpty());
        StdOut.println("Size of Deque: " + deque.size());

        // deque.addLast("Z");

        deque.addFirst("A");
        deque.removeLast();
        deque.addFirst("B");
        deque.addFirst("C");

        deque.addLast("D");
        deque.addLast("E");
        deque.addLast("F");

        StdOut.println("Is Deque empty: " + deque.isEmpty());
        StdOut.println("Size of Deque: " + deque.size());

        deque.printElements(deque);

        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());

        StdOut.println("Is Deque empty: " + deque.isEmpty());
        StdOut.println("Size of Deque: " + deque.size());

        deque.printElements(deque);*/

        Deque<Integer> deque = new Deque<>();
        deque.isEmpty();
        deque.isEmpty();
        deque.addFirst(3);
        deque.removeLast();
        deque.isEmpty();
        deque.isEmpty();
        deque.addFirst(7);
        StdOut.print(deque.removeLast());

    }

    private void printElements(Deque<Item> deque) {
        Iterator<Item> dequeIterator = deque.iterator();

        while (dequeIterator.hasNext()) {
            Item currentItem = dequeIterator.next();
            StdOut.println(currentItem);
        }
    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class DequeueIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            // if the Deque is empty, throw an error
            if (!hasNext()) throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}