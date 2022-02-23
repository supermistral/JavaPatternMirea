package ru.mirea.task10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mirea.task10.programmers.Programmer;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

        if (args.length > 0) {
            Programmer programmer = (Programmer)context.getBean(args[0]);
            programmer.doCoding();
        }
    }
}
