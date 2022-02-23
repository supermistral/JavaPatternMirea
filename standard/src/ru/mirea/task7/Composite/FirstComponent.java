package ru.mirea.task7.Composite;

import java.util.ArrayList;
import java.util.List;

public class FirstComponent implements Component {
    private List<Component> components;

    public FirstComponent(Component ...components) {
        this.components = new ArrayList<>();
        for (Component comp : components) {
            this.components.add(comp);
        }
    }

    @Override
    public void operation() {
        System.out.println("1 component -> operation");
        System.out.println("1 component -> children: " + components.toString());

        components.forEach(component -> component.operation());
    }

    @Override
    public void add(Component c) {
        components.add(c);
    }

    @Override
    public void remove(Component c) {
        components.remove(c);
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }
}
