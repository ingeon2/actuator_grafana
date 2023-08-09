package hello.order;

import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {

    void request();
    void cancel();
    AtomicInteger getStock();
}
