package me.suhyuk.springboot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogRunningAspect { // 반드시 Aspect 클래스의 이름이 달라야 static, dynamic 모두 동작합니다

    private static Logger logger = LoggerFactory.getLogger(LogRunningAspect.class);

    @Around("@annotation(LogRunningTime)")
    public Object logRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object ret = joinPoint.proceed();
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
        return ret;
    }
}
