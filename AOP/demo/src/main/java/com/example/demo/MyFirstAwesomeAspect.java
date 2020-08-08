package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyFirstAwesomeAspect {
    private Logger LOGGER = LoggerFactory.getLogger(MyFirstAwesomeAspect.class);
    
    @Before("execution(* com.example.demo.dao.*.*(..))")
    public void before(JoinPoint joinPoint){
        LOGGER.info("My awasome aspect before!!!");
        LOGGER.info("-> {}", joinPoint);
        LOGGER.info("-> args: {}", joinPoint.getArgs());
        LOGGER.info("->   at: {}", joinPoint.getSourceLocation());
    }
}