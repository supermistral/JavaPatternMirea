package ru.mirea.task8.State;

public class ConcreteState1 implements State {

    @Override
    public void handle(Context context) {
        System.out.println("1 state -> handle");
    }

    @Override
    public void next(Context context) {
        context.setState(new ConcreteState2());
    }
}
