package ru.mirea.task7.Proxy;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("Request");
    }
}
