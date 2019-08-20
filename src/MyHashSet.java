import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyHashSet<T> implements Iterable<T> {
    private LinkedList<T>[] hashset;
    private int capacity = 16;
    private double loadFactor = 0.75;
    private int size;

    public MyHashSet() {
        hashset = new LinkedList[capacity];
    }

    public boolean add(T element) {
        if (size > capacity * loadFactor) {
            rebalance();
        }
        int index = getIndex(element);
        if (hashset[index] == null) {
            hashset[index] = new LinkedList<>();
        }
        if (hashset[index].contains(element)) {
            return false;
        }
        hashset[index].add(element);
        size++;
        return true;
    }

    private void rebalance() {
        capacity *= 2;
        LinkedList<T>[] newHashset = new LinkedList[capacity];
        for (int i = 0; i < hashset.length; i++) {
            if (hashset[i] != null) {
                for (T e : hashset[i]) {
                    int index = getIndex(e);
                    if (newHashset[index] == null) {
                        newHashset[index] = new LinkedList<>();
                    }
                    newHashset[index].add(e);
                }
            }
        }
        hashset = newHashset;
    }

    public boolean contains(T element) {
        int index = getIndex(element);
        if (hashset[index] == null) {
            return false;
        }
        return hashset[index].contains(element);
    }

    public boolean remove(T element) {
        int index = getIndex(element);
        if (hashset[index] == null) {
            return false;
        }
        boolean res = hashset[index].remove(element);
        if (res) {
            size--;
        }
        return res;
    }

    private int getIndex(T element) {
        int hashcode = element.hashCode();
        hashcode = hashcode > 0 ? hashcode : -hashcode;
        return hashcode % capacity;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
//        return new SecondIterator();
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int totalCount;
        private int arrayCounter;
        private int listCounter;

        @Override
        public boolean hasNext() {
            return totalCount < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            while (hashset[arrayCounter] == null ||
                    hashset[arrayCounter].size() == 0) {
                arrayCounter++;
            }

            T res = hashset[arrayCounter].get(listCounter);
            totalCount++;
            if (listCounter < hashset[arrayCounter].size() - 1) {
                listCounter++;
            } else {
                listCounter = 0;
                arrayCounter++;
            }
            return res;
        }
    }

    private class SecondIterator implements Iterator<T> {
        private int arrayCounter;
        private Iterator<T> currentIterator;

        @Override
        public boolean hasNext() {
            return arrayCounter < hashset.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (currentIterator == null || !currentIterator.hasNext()) {
                while (hashset[arrayCounter] == null ||
                        hashset[arrayCounter].size() == 0) {
                    arrayCounter++;
                }
                currentIterator = hashset[arrayCounter].iterator();
            }

            T res = currentIterator.next();
            if(!currentIterator.hasNext()){
                arrayCounter++;
            }

            return res;
        }
    }
}
