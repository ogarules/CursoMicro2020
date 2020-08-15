package com.example.demo.services;

import com.example.demo.models.Person;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyVeryCoolAspect {
    
    @Around("execution(* com.example.demo.services.*.*(com.example.demo.models.Person))")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        Person person = (Person)args[0];

        if(person.getName().equals("OSCAR"))
        {
            person.setAge(21);
        }

        Object result = joinPoint.proceed();
        return result;
    } 
}