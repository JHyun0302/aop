package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    //hell.aop.order 패키지와 하위 패키지

    @Pointcut("execution(* hello.aop.order..*(..))") //pointcut expression
    public void allOrder() {
    } //pointcut signature: 메서드 이름 + 파라미터

    //클래스 이름 패턴이 *Service - 비지니스 로직 = service, 비지니스 로직 성공 -> 커밋, 실패 -> 롤백
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {
    }
}
