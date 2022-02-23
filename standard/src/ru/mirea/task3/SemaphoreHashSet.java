package ru.mirea.task3;

import java.util.*;
import java.util.concurrent.Semaphore;

public class SemaphoreHashSet<E> implements Set<E> {
    private final Set<E> set;
    private final Semaphore semaphore;

    public SemaphoreHashSet() {
        set = new HashSet<>();
        semaphore = new Semaphore(1);
    }

    public SemaphoreHashSet(int permits) {
        set = Collections.synchronizedSet(new HashSet<>());
        semaphore = new Semaphore(permits);
    }

    public SemaphoreHashSet(Set<E> set, int permits) {
        this.set = set;
        semaphore = new Semaphore(permits);
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    public boolean add(E e) {
        try {
            semaphore.acquire();
            boolean bool = false;
            try {
                bool = set.add(e);
                return bool;
            } finally {
                if (!bool)
                    semaphore.release();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean remove(Object o) {
        boolean bool = set.remove(o);

        if (bool)
            semaphore.release();

        return bool;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }

    public String toString() {
        return set.toString();
    }
}
