package hello.elan.redis.biz.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 张一然
 * @date 2020/5/15 10:28
 */
@RestController
@RequestMapping("order")
public class OrderCtrl {

    @GetMapping("create")
    public String create() {
        String orderId = UUID.randomUUID().toString();
        OrderTask.pushOrder(orderId);
        return orderId;
    }
}
