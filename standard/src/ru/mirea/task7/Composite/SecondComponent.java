package ru.mirea.task7.Composite;

public class SecondComponent implements Component {
    @Override
    public void operation() {
        System.out.println("2 component -> operation");
    }

    @Override
    public void add(Component c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Component getChild(int index) {
        throw new UnsupportedOperationException();
    }
}
