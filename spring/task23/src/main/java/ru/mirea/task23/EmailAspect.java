package ru.mirea.task23;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mirea.task23.services.EmailService;

//@Aspect
//@Component
public class EmailAspect {

    @Autowired
    private EmailService emailService;

    @Pointcut("execution(* ru.mirea.task23.services.*.create*(..))")
    public void allCreateMethods() {

    }

    @After("allCreateMethods()")
    public void sendObjectInfoThroughEmail(JoinPoint joinPoint) {
        Object o = joinPoint.getArgs()[0];

        emailService.sendObjectInfoMail("mediamart37@gmail.com", o);
    }
}
