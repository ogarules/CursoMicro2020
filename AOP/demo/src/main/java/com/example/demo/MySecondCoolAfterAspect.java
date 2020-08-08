package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MySecondCoolAfterAspect {
    private Logger LOGGER = LoggerFactory.getLogger(MySecondCoolAfterAspect.class);

    @AfterReturning(value = "execution(* com.example.demo.services.*.*(..))", returning = "result")
    public void afterRunning(JoinPoint joinPoint, Object result){
        LOGGER.info("{} regresa con -> {}", joinPoint, result);
    }
}