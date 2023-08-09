package hello.order.timer;

import hello.order.OrderService;
import hello.order.count.OrderServiceV1;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;

public class OrderConfigV2 {

    @Bean
    OrderService orderService() {
        return new OrderServiceV2();
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) { //Timed AOP 기능을 위해 주입하는 Bean!
        return new TimedAspect(registry);
    }
}
