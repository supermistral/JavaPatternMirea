package ru.mirea.task8.State;

public class Context {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void request() {
        state.handle(this);
    }

    public void nextState() {
        state.next(this);
    }
}
