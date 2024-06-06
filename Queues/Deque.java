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
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        oldLast.next = last;
        last.prev = oldLast;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();

        Item item = first.item;
        first = first.next;
        first.prev = null;
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();

        Item item = last.item;
        last = last.prev;
        last.next = null;
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }


    public static void main(String[] args) {
        Deque deque = new Deque();

        StdOut.println("Is Deque empty: " + deque.isEmpty());
        StdOut.println("Size of Deque: " + deque.size());

        deque.addFirst("A");
        deque.addFirst("B");
        deque.addFirst("C");

        StdOut.println("Is Deque empty: " + deque.isEmpty());
        StdOut.println("Size of Deque: " + deque.size());

        deque.printElements(deque);

        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());

        StdOut.println("Is Deque empty: " + deque.isEmpty());
        StdOut.println("Size of Deque: " + deque.size());

        deque.printElements(deque);

    }

    private void printElements(Deque deque) {
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