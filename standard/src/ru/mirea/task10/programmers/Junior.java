package ru.mirea.task10.programmers;

import org.springframework.stereotype.Component;

@Component("junior")
public class Junior implements Programmer {

    @Override
    public void doCoding() {
        System.out.println("I'm junior");
    }
}
