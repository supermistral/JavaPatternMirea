package ru.mirea.task8.ChainOfResponsibility;

public class ConcreteHandler3 extends AbstractHandler {

    public void handle(String request) {
        if (request.equals("condition-c")) {
            System.out.println("Condition C - 3 handler");
        }
        super.handle(request);
    }
}
