package ru.mirea.task7.Proxy;

public class Proxy implements Subject {
    private Subject subject;

    public Proxy() {
        subject = new RealSubject();
    }

    @Override
    public void request() {
        System.out.println("Operations before execution");
        subject.request();
        System.out.println("Operation after execution");
    }
}
