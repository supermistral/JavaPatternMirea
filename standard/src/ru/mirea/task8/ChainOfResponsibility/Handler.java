package ru.mirea.task8.ChainOfResponsibility;

public interface Handler {
    void handle(String request);
    Handler setNext(Handler handler);
}
