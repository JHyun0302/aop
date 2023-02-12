package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * pointcut 따로 지정 -> 모듈화 가능
 */
@Slf4j
@Aspect
public class AspectV2 {

    /**
     * @Around: pointcut
     * doLog(): advice
     */
    //hell.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))") //pointcut expression
    private void allOrder() {
    } //pointcut signature: 메서드 이름 + 파라미터

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처(메서드의 모든 정보)
        return joinPoint.proceed();
    }
}
