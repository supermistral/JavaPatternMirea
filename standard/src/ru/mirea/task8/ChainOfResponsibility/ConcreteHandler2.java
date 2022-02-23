package ru.mirea.task8.ChainOfResponsibility;

public class ConcreteHandler2 extends AbstractHandler {

    public void handle(String request) {
        if (request.equals("condition-b")) {
            System.out.println("Condition B - 2 handler");
        }
        super.handle(request);
    }
}
