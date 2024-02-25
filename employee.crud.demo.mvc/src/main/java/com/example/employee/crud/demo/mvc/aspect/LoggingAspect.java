package com.example.employee.crud.demo.mvc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution (* com.example.employee.crud.demo.mvc.controller.*.*(..))")
    private void forControllerPackage(){};
    @Pointcut("execution (* com.example.employee.crud.demo.mvc.service.*.*(..))")
    private void forServicePackage(){};
    @Pointcut("execution (* com.example.employee.crud.demo.mvc.dao.*.*(..))")
    private void forDaoPackage(){};
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void loggingPackages(){};
    @Before("loggingPackages()")
    public void loggingBeforeExecution(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("==========> in @Before: " + methodName);
        Object[] object = joinPoint.getArgs();
        for(Object o : object){
            logger.info("==========> in @Before: args: " + o);
        }
    }
    @AfterReturning(
            pointcut = "loggingPackages()",
            returning = "result"
    )
    public void loggingAfterSuccessfulExecution(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("==========> in @After: " + methodName);
        logger.info("==========> in @After: result is " + result);
    }

}





















