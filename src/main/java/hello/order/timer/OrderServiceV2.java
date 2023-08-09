package hello.order.timer;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Timed(value = "my.request") //actuactor timer 기능을 사용하기 위해 클래스 단위로 붙여주는 애너테이션, 당연히 매서드 단위도 된다!
//이렇게 클래스단위로 지정하면, 해당 클래스의 모든 public 매서드들에 적용된다.
@Slf4j
public class OrderServiceV2 implements OrderService {
    private AtomicInteger stock = new AtomicInteger(100);

    @Counted("my.request")
    @Override
    public void request() {
        log.info("요청");
        stock.decrementAndGet();
        sleep(500);
    }

    @Counted("my.request")
    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet();
        sleep(200);
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }


    //매서드마다 실행 시간 다른것을 @Timed로 체크해야 하므로
    //스레드를 강제로 지연시키기 위한 매서드 생성
    private static void sleep(int l) {
        try {
            Thread.sleep(l + new Random().nextInt(200));
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
