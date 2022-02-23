package ru.mirea.task3;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockArrayList<E> implements List<E> {
    private final List<E> list;
    private final Lock lock = new ReentrantLock();

    public LockArrayList() {
        list = new ArrayList<>();
    }

    public LockArrayList(List<E> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        lock.lock();
        Iterator iterator = list.iterator();
        lock.unlock();

        return iterator;
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(E e) {
        lock.lock();
        boolean bool = list.add(e);
        lock.unlock();

        return bool;
    }

    @Override
    public boolean remove(Object o) {
        lock.lock();
        boolean bool = list.remove(o);
        lock.unlock();

        return bool;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        lock.lock();
        boolean bool = list.addAll(c);
        lock.unlock();

        return bool;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        lock.lock();
        boolean bool = list.addAll(index, c);
        lock.unlock();

        return bool;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        lock.lock();
        boolean bool = list.removeAll(c);
        lock.unlock();

        return bool;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        lock.lock();
        boolean bool = list.retainAll(c);
        lock.unlock();

        return bool;
    }

    @Override
    public void clear() {
        lock.lock();
        list.clear();
        lock.unlock();
    }

    @Override
    public E get(int index) {
        lock.lock();
        E elem = list.get(index);
        lock.unlock();

        return elem;
    }

    @Override
    public E set(int index, E element) {
        lock.lock();
        E elem = list.set(index, element);
        lock.unlock();

        return elem;
    }

    @Override
    public void add(int index, E element) {
        lock.lock();
        list.add(index, element);
        lock.unlock();
    }

    @Override
    public E remove(int index) {
        lock.lock();
        E elem = list.remove(index);
        lock.unlock();

        return elem;
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        lock.lock();
        ListIterator listIterator = list.listIterator();
        lock.unlock();

        return listIterator;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        lock.lock();
        ListIterator listIterator = list.listIterator(index);
        lock.unlock();

        return listIterator;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }
}
