package hello.elan.redis.biz.simple;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 张一然
 * @date 2019/12/22 下午4:15
 * @Package hello.elan.redis.biz.simple
 * @Description
 */
@Service
public class UserStringTemplate {
    /**
     * 配置了redis链接之后，可以直接使用spring提供的template
     */
    private final StringRedisTemplate redisTemplate;

    public UserStringTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void putString(String key , String value){
        // 设置过期时间5分钟
        redisTemplate.opsForValue().set(key, value, 5, TimeUnit.MINUTES);
        // 设置永久不过期
        redisTemplate.opsForValue().set(key, value, -1);
        // 刷新过期时间， redis的过期时间是设给key的
        redisTemplate.expire(key, 10, TimeUnit.MINUTES);
    }
}
