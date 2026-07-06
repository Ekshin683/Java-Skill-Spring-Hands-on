package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// Exercise 3 & 8 — Logging Aspect
@Aspect
@Component
public class LoggingAspect {

    // ----------------------------------------------------------------
    // Pointcut — intercepts all methods in com.library.service package
    // ----------------------------------------------------------------
    @Pointcut("execution(* com.library.service.*.*(..))")
    public void serviceLayerMethods() {}

    // ----------------------------------------------------------------
    // Exercise 8 — Before advice
    // Runs BEFORE each service method
    // ----------------------------------------------------------------
    @Before("serviceLayerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("======================================");
        System.out.println("[AOP - BEFORE] Method called: "
                + joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName());
        System.out.println("[AOP - BEFORE] Arguments: "
                + java.util.Arrays.toString(joinPoint.getArgs()));
    }

    // ----------------------------------------------------------------
    // Exercise 8 — After advice
    // Runs AFTER each service method (always, even on exception)
    // ----------------------------------------------------------------
    @After("serviceLayerMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[AOP - AFTER] Method completed: "
                + joinPoint.getSignature().getName());
        System.out.println("======================================");
    }

    // ----------------------------------------------------------------
    // AfterReturning — runs only on successful return
    // ----------------------------------------------------------------
    @AfterReturning(pointcut = "serviceLayerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[AOP - AFTER RETURNING] "
                + joinPoint.getSignature().getName()
                + " returned: " + result);
    }

    // ----------------------------------------------------------------
    // AfterThrowing — runs only when exception is thrown
    // ----------------------------------------------------------------
    @AfterThrowing(pointcut = "serviceLayerMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("[AOP - EXCEPTION] "
                + joinPoint.getSignature().getName()
                + " threw: " + error.getMessage());
    }

    // ----------------------------------------------------------------
    // Exercise 3 — Around advice: logs method EXECUTION TIME
    // This is the core of Exercise 3 — tracking execution times
    // ----------------------------------------------------------------
    @Around("serviceLayerMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        System.out.println("[AOP - AROUND] Starting: "
                + joinPoint.getSignature().getName());

        // Actually call the real method
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("[AOP - AROUND] "
                + joinPoint.getSignature().getName()
                + " executed in " + executionTime + " ms");

        return result;
    }
}