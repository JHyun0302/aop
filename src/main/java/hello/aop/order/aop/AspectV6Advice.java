package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Around: joinPoint.proceed(); 필수!!
 * 어드바이스의 첫 번째 파라미터는 ProceedingJoinPoint를 사용해야 한다.
 * proceed() 통해 대상을 실행한다. (여러번 실행 가능)
 * @Before, @AfterReturning, @AfterThrowing, @After; 나머지는 없어도 됨!
 */

@Slf4j
@Aspect
public class AspectV6Advice {
    //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service => OrderService만 적용
    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    /**
     * @Before() joinPoint.proceed(); 실행 직전까지 로직만 있으면 된다.
     * 그 뒤는 알아서 실행해줌
     */

    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    /**
     * @AfterReturning returning = "result"와 파라미터의 Object result가 매칭되서 return값 찍을 수 있음.
     * 하지만 result값을 return 하기 전에 조작하는 것 불가능.
     */

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    //returning: return되는 값 이름(파라미터와 이름 매칭)
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    /**
     * @AfterThrowing throwing = "ex"와 파라미터의 Exception ex가 매칭되서 log 찍고 throw 가능
     */

    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    //throwing: 파라미터와 이름 매칭
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", joinPoint.getSignature(), ex.getMessage());
    }

    /**
     * @After 메서드 실행이 종료되면 실행된다.(finally와 비슷)
     * 정상 및 예외 반환 조건을 모두 처리한다.
     */
    @After(value = "hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
