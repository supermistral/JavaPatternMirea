package ru.mirea.task8;

import ru.mirea.task8.ChainOfResponsibility.ConcreteHandler1;
import ru.mirea.task8.ChainOfResponsibility.ConcreteHandler2;
import ru.mirea.task8.ChainOfResponsibility.ConcreteHandler3;
import ru.mirea.task8.ChainOfResponsibility.Handler;
import ru.mirea.task8.State.ConcreteState1;
import ru.mirea.task8.State.Context;

public class Main {
    public static void main(String[] args) {
        testState();
        testCOR();
    }

    public static void testState() {
        Context context = new Context();
        context.setState(new ConcreteState1());

        context.request();
        context.nextState();
        context.request();
    }

    public static void testCOR() {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();

        handler1.setNext(handler2).setNext(handler3);

        handler1.handle("condition-a");
        handler1.handle("condition-b");
        handler1.handle("condition-c");
    }
}
