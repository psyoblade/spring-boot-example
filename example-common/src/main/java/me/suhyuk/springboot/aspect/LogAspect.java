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
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object ret = joinPoint.proceed();
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
        return ret;
    }
}
