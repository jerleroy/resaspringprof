package com.sopra.resa.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //+component-scan sur package com.sopra.resa.util
           //et avec   <aop:aspectj-autoproxy />
		   //et avec dependency aspectjrt et aspectjweaver dans pom.xml
public class MyLogAspect {
	@Around("execution(* com.sopra.resa.service.*.*(..))")
	public Object doLog1(ProceedingJoinPoint pjp) throws Throwable {
		return doXxxLog(pjp);
	}
	
	@Around("execution(* com.sopra.resa.dao.*.*(..))")
	public Object doLog2(ProceedingJoinPoint pjp) throws Throwable {
		return doXxxLog(pjp);
	}
	
	public Object doXxxLog(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("<< trace == debut == " + pjp.getSignature().toLongString() + " <<");
		long td = System.nanoTime();
		
		Object objRes = pjp.proceed();
		
		long tf = System.nanoTime();
		System.out.println(
				">> trace == fin == " + pjp.getSignature().toShortString()
				+ " [" + (tf - td) / 1000000.0 + " ms] >>");
		
		return objRes;
	}
}