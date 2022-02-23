package ru.mirea.task10.programmers;

import org.springframework.stereotype.Component;

@Component("senior")
public class Senior implements Programmer {

    @Override
    public void doCoding() {
        System.out.println("I'm senior");
    }
}
