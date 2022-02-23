package ru.mirea.task8.ChainOfResponsibility;

public class ConcreteHandler1 extends AbstractHandler {

    public void handle(String request) {
        if (request.equals("condition-a")) {
            System.out.println("Condition A - 1 handler");
        }
        super.handle(request);
    }
}
