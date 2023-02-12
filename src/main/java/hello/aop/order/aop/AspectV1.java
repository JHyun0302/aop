package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 기본 @Aspect 프록시
 */
@Slf4j
@Aspect
public class AspectV1 {
    /**
     * @Around: pointcut
     * doLog(): advice
     */
    //hell.aop.order 패키지와 하위 패키지
    @Around("execution(* hello.aop.order..*(..))")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처(메서드의 모든 정보)
        return joinPoint.proceed();
    }
}
