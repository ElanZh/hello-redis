package hello.elan.redis.biz.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * @author 张一然
 * @date 2020/5/15 11:55
 * 使用reids的zset实现定时器任务
 */
@Slf4j
@Component
@EnableScheduling
public class OrderTask {

    public static final String NEED_CHECK_ORDER = "NEED_CHECK_ORDER";
    public static final DateTimeFormatter SCORE_PATTERN = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private static StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        OrderTask.redisTemplate = redisTemplate;
    }

    public static Boolean pushOrder(String orderId) {
        return redisTemplate.opsForZSet().add(NEED_CHECK_ORDER, orderId, Double.parseDouble(LocalDateTime.now().format(SCORE_PATTERN)));
    }

    /**
     * 处理需要检查的订单
     * todo 业务逻辑写在这里
     *
     * @param orderId
     * @param time
     */
    public void timeoutOrder(String orderId, String time) {
        try {
            // 业务逻辑写在这里
            log.info("进入订单处理逻辑");
//            throw new RuntimeException("出错了！！！");
        } catch (Exception e) {
            log.error("处理订单业务失败，重新插入redis-ZSET" + e.getMessage());
            redisTemplate.opsForZSet().add(NEED_CHECK_ORDER, orderId, Double.parseDouble(time));
        }
    }

    // 每500ms执行一次
    @Scheduled(fixedRate = 500)
    public void scanOrder() {
        // 每次只从set中取第一个
        Set<ZSetOperations.TypedTuple<String>> orderInfo = redisTemplate.opsForZSet().rangeWithScores(NEED_CHECK_ORDER, 0, 0);
        if (!CollectionUtils.isEmpty(orderInfo)) {
            ZSetOperations.TypedTuple<String> order = orderInfo.iterator().next();
            String scoreTime = String.valueOf(order.getScore().longValue());
            LocalDateTime orderTime = LocalDateTime.parse(scoreTime, SCORE_PATTERN);
            if (Duration.between(orderTime, LocalDateTime.now()).toSeconds() > 30) {
                String orderId = order.getValue();
                log.info("获取到待处理订单" + orderId + "--" + scoreTime);
                Long remove = redisTemplate.opsForZSet().remove(NEED_CHECK_ORDER, orderId);
                // 删除成功了再处理业务
                if (remove != null) {
                    timeoutOrder(orderId, scoreTime);
                }
            }
        }
    }
}
