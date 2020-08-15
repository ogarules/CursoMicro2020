package com.example.demo.services;

import com.example.demo.models.Person;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyThirdNoiceAroundAspect {
    private Logger LOGGER = LoggerFactory.getLogger(MyThirdNoiceAroundAspect.class);

    @Around("@annotation(com.example.demo.services.MyAround)")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Integer f = 0;
        long initTime = System.currentTimeMillis(); 
        LOGGER.info("Araund before");
        Object result = joinPoint.proceed();
        long finalTime = System.currentTimeMillis();
        long ejecutionTime = (finalTime - initTime) / 1000;

        LOGGER.info("Tiempo de ejecución: {} segundos", ejecutionTime);
        LOGGER.info("Araund after");

        return result;
    }
}