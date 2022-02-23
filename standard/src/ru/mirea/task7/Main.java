package ru.mirea.task7;

import ru.mirea.task7.Composite.Component;
import ru.mirea.task7.Composite.FirstComponent;
import ru.mirea.task7.Composite.SecondComponent;
import ru.mirea.task7.Proxy.Proxy;
import ru.mirea.task7.Proxy.Subject;

public class Main {
    public static void main(String[] args) {
        testProxy();
        testComposite();
    }

    public static void testProxy() {
        Subject proxySubject = new Proxy();
        proxySubject.request();
    }

    public static void testComposite() {
        Component component = new FirstComponent(new SecondComponent(), new SecondComponent());
        component.operation();
    }
}
