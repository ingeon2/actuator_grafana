package hello.order.count;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV1 implements OrderService{

    private AtomicInteger stock = new AtomicInteger(100);

    @Counted("my.request") //★메트릭 이름 지정★
    @Override
    public void request() { //매서드 이름은 태그네임.
        log.info("요청");
        stock.decrementAndGet();
    }

    @Counted("my.request")
    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}

