package ru.mirea.task8.ChainOfResponsibility;

public abstract class AbstractHandler implements Handler {
    private Handler nextHandler = null;

    @Override
    public void handle(String request) {
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }

    @Override
    public Handler setNext(Handler handler) {
        this.nextHandler = handler;
        return handler;
    }
}
