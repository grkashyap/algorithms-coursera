/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INITIAL_CAPACITY = 10;
    private Item[] randomQueue;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[INITIAL_CAPACITY];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (size == randomQueue.length) resize(randomQueue.length * 2);

        randomQueue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        Item tempItem = null;
        int randomIndex = getRandomIndex();
        if (randomIndex >= 0) {
            tempItem = randomQueue[randomIndex];
            --size;
            randomQueue[randomIndex] = null;
        }

        if (size > 0 && size == randomQueue.length / 4) resize(randomQueue.length / 2);

        return tempItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return randomQueue[getRandomIndex()];
    }

    private int getRandomIndex() {
        int randomIndex = -1;
        Item tempItem = null;

        while (tempItem == null) {
            randomIndex = StdRandom.uniformInt(size);
            tempItem = randomQueue[randomIndex];
        }

        return randomIndex;
    }

    private void resize(int newCapacity) {
        Item[] tempItems = (Item[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            if (randomQueue[i] != null) {
                tempItems[i] = randomQueue[i];
            }
        }

        randomQueue = tempItems;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }


    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        StdOut.println("Is the queue empty: " + rq.isEmpty());
        StdOut.println("Size of queue: " + rq.size());

        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        rq.enqueue("D");
        rq.enqueue("E");
        rq.enqueue("F");
        rq.enqueue("G");

        StdOut.println("===");
        rq.displayElements(rq);
        StdOut.println("===");

        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());

        StdOut.println(rq.sample());

        StdOut.println("===");
        rq.displayElements(rq);
        StdOut.println("===");

        StdOut.println("Is the queue empty: " + rq.isEmpty());
        StdOut.println("Size of queue: " + rq.size());

    }

    private void displayElements(RandomizedQueue<Item> randomizedQueue) {
        Iterator<Item> rqItr = randomizedQueue.iterator();

        while (rqItr.hasNext()) {
            Item thisItem = rqItr.next();
            StdOut.println(thisItem);
        }
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex != size;
        }

        @Override
        public Item next() {

            if (!hasNext()) throw new NoSuchElementException();
            currentIndex++;
            return getRandomItem();
        }

        private Item getRandomItem() {
            Item item = null;
            int randomIndex = -1;
            // get randomIndex until the currentItem is not null
            while (item == null) {
                randomIndex = StdRandom.uniformInt(size);
                item = randomQueue[randomIndex];
            }

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}