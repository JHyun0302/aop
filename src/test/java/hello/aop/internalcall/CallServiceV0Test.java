package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {
    @Autowired
    CallServiceV0 callServiceV0;

    /**
     * internal()을 호출 시 aop가 적용되지 않음. (프록시를 거치지 않아서 advise 호출 안됨!)
     * 이유: AOP 프록시 external() 호출 -> 실제 external() 호출 -> this.internal() 인해 실제 internal() 호출
     */
    @Test
    void external() {
        callServiceV0.external();
    }


    /**
     * external() 거치지 않고 internal() 호출 시 aop 적용됨
     */
    @Test
    void internal() {
        callServiceV0.internal();
    }

}