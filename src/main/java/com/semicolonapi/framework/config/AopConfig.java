package com.semicolonapi.framework.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfig {
    private static final Logger log = LoggerFactory.getLogger(AopConfig.class);

    @Pointcut("execution(* *..*Controller.*(..))")
    public void approach() { }

    @Around("approach()") // 포인트컷을 메서드로 만들어 사용
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[call controller] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed(); // 실제 타깃 호출
    }

}
