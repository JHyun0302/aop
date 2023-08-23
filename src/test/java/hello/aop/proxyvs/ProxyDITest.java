package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * 의존관계 주입
 * JDK 동적 프록시
 */
@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //JDK 동적 프록시, DI 예외 발생(JDK 동적 프록시는 인터페이스로 프록시 만들어서 Impl 모름)
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) //CGLIB 프록시, 성공(CGLIB는 구체클래스로 프록시 만들어서 Impl 이든 인터페이스든 모두 DI 가능)
@SpringBootTest
@Import(ProxyDIAspect.class)
public class ProxyDITest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");

    }
}
