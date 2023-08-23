package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * 대안2: 지연 조회 (ObjectProvider(Provider), ApplicationContext)
 * ApplicationContext - 기능이 너무 많음
 * ObjectProvider(Provider) - 기능의 제한을 줌
 */
@Slf4j
@Component
public class CallServiceV2 {
    //    private final ApplicationContext applicationContext; //기능이 너무 많음!
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    public void external() {
        log.info("call external");
//        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal(); //외부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
