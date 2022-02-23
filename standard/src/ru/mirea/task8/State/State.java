package ru.mirea.task8.State;

public interface State {
    void handle(Context context);
    void next(Context context);
}
