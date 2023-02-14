package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 내부 메서드 호출시 프록시 적용 안됨! (자기 자신 호출)
 */
@Slf4j
@Component
public class CallServiceV0 {
    public void external() {
        log.info("call external");
        internal(); //내부 메서드 호출(this.internal())
    }

    public void internal() {
        log.info("call internal");
    }
}
