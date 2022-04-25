package ru.mirea.task22;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("within(ru.mirea.task22.services.*)")
    public void allServiceMethods() {

    }

    @Around("allServiceMethods()")
    public void logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;
        log.info(joinPoint.getSignature() + " completed in " + executionTime + "ms");
    }
}
